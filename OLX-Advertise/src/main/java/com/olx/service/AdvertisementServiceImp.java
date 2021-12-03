package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertise;
import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.dto.UserDto;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.InvalidUserException;
import com.olx.repo.AdvertiseRepo;

@Service
public class AdvertisementServiceImp implements AdvertisementService {

	@Autowired
	MasterDataDelegate masterDataDelegate;
	@Autowired
	LoginDelegate loginDelegate;
	@Autowired
	AdvertiseRepo advertiseRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	EntityManager entityManager;

	@Override
	public Advertise createNewAdvertise(String authToken, Advertise advertise) {

		if (!loginDelegate.isTokenValid(authToken)) {
			throw new InvalidUserException(authToken);
		}

		UserDto userDto = loginDelegate.getUserDetails(authToken);

		advertise.setUsername(userDto.getUsername());
		if (userDto.isActive()) {
			advertise.setActive(1);
		} else {
			advertise.setActive(0);
		}

		advertise.setPostedBy(userDto.getFirstName());
		advertise.setCreatedDate(LocalDate.now());
		advertise.setModifiedDate(LocalDate.now());

		Category category = masterDataDelegate.getCategoryDetails(advertise.getCategoryId());
		Status status = masterDataDelegate.getStatusDetails(advertise.getCategoryId());

		advertise.setCategory(category.getCategory());
		advertise.setStatus(status.getStatus());

		// business logic to create new advertise
		AdvertiseEntity advertiseEntity = getAdvertiseEntityFromDto(advertise);
		advertiseEntity = advertiseRepo.save(advertiseEntity);
		return getAdvertiseDtoFromEntity(advertiseEntity);
	}

	private Advertise getAdvertiseDtoFromEntity(AdvertiseEntity advertiseEntity) {

		Advertise advertise = this.modelMapper.map(advertiseEntity, Advertise.class);
		return advertise;
	}

	private AdvertiseEntity getAdvertiseEntityFromDto(Advertise advertise) {

		AdvertiseEntity advertiseEntity = this.modelMapper.map(advertise, AdvertiseEntity.class);
		return advertiseEntity;
	}

	private List<Advertise> getAdvertiseDtoListFromEntityList(List<AdvertiseEntity> advertiseEntityList) {
		// TODO Auto-generated method stub
		List<Advertise> advertiseList = new ArrayList<>();

		for (AdvertiseEntity advertiseEntity : advertiseEntityList) {

			advertiseList.add(getAdvertiseDtoFromEntity(advertiseEntity));
		}
		return advertiseList;
	}

	@Override
	public Advertise updateAdvertiseById(int advertiseId, String authToken, Advertise advertise) {

		if (!loginDelegate.isTokenValid(authToken)) {
			throw new InvalidUserException("Invalid User");
		}

		Category category = masterDataDelegate.getCategoryDetails(advertise.getCategoryId());
		Status status = masterDataDelegate.getStatusDetails(advertise.getCategoryId());

		Optional<AdvertiseEntity> advertiseOptional = advertiseRepo.findById(advertiseId);
		if (advertiseOptional.isPresent()) {
			AdvertiseEntity advertiseEntity = advertiseOptional.get();
			advertiseEntity.setTitle(advertise.getTitle());
			advertiseEntity.setDescription(advertise.getDescription());
			advertiseEntity.setPrice(advertise.getPrice());
			advertiseEntity.setCategory(category.getCategory());
			advertiseEntity.setStatus(status.getStatus());
			advertiseEntity.setModifiedDate(LocalDate.now());
			advertiseRepo.save(advertiseEntity);

			Advertise fromEntity = getAdvertiseDtoFromEntity(advertiseEntity);
			fromEntity.setCategoryId(category.getId());
			return fromEntity;
		} else {
			throw new InvalidAdvertiseIdException("Invalid Advertise Id");
		}
	}

	@Override
	public List<Advertise> getAllAdvertiseByLoggedinUser(String authToken) {

		if (!loginDelegate.isTokenValid(authToken)) {
			throw new InvalidUserException(authToken);
		}

		UserDto userDto = loginDelegate.getUserDetails(authToken);
		List<AdvertiseEntity> listAdvertiseEntity = advertiseRepo.findByPostedBy(userDto.getFirstName());
		return getAdvertiseDtoListFromEntityList(listAdvertiseEntity);
	}

	@Override
	public Advertise getAdvertiseById(int advertiseId, String authToken) {

		if (!loginDelegate.isTokenValid(authToken)) {
			throw new InvalidUserException(authToken);
		}

		Optional<AdvertiseEntity> advertiseOptional = advertiseRepo.findById(advertiseId);
		if (advertiseOptional.isPresent()) {
			AdvertiseEntity advertiseEntity = advertiseOptional.get();
			Advertise fromEntity = getAdvertiseDtoFromEntity(advertiseEntity);
			fromEntity.setCategoryId(advertiseId);
			return fromEntity;
		} else {
			throw new InvalidAdvertiseIdException("Invalid Advertise Id");
		}
	}

	@Override
	public Boolean removeAdvertiseById(int advertiseId, String authToken) {

		if (!loginDelegate.isTokenValid(authToken)) {
			throw new InvalidUserException(authToken);
		}

		Optional<AdvertiseEntity> advertiseOptional = advertiseRepo.findById(advertiseId);
		if (advertiseOptional.isPresent()) {
			advertiseRepo.deleteById(advertiseId);
			return true;
		}
		return false;
	}

	@Override
	public List<Advertise> searchAdvertiseByFilterCriteria(String searchText, Integer categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			Integer startIndex, Integer records) {
		// call MasterData service getAllCategories() - RestTemplate
		// List<Map> catgories = masterDataDelegate.getAllCategories();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> rootEntity = criteriaQuery.from(AdvertiseEntity.class);

		Predicate titlePredicate = criteriaBuilder.like(rootEntity.get("title"), searchText);
		Predicate categoryIdPredicate = criteriaBuilder.equal(rootEntity.get("categoryId"), categoryId);
		Predicate postedByPredicate = criteriaBuilder.equal(rootEntity.get("postedBy"), postedBy);
		Predicate onDatePredicate = criteriaBuilder.equal(rootEntity.get("createdDate"), onDate);
		Predicate toDatePredicate = criteriaBuilder.equal(rootEntity.get("createdDate"), toDate);
		Predicate fromDatePredicate = criteriaBuilder.equal(rootEntity.get("createdDate"), fromDate);
		Predicate lessThanPredicate = criteriaBuilder.lessThan(rootEntity.get("createdDate"), fromDate);
		Predicate geaterThanPredicate = criteriaBuilder.greaterThan(rootEntity.get("createdDate"), fromDate);
		Order sortedOrder = criteriaBuilder.desc(rootEntity.get("createdDate"));

		Predicate finalPredecate = null;
		criteriaBuilder.or(titlePredicate, categoryIdPredicate, postedByPredicate);

		if (searchText != null) {

			if (categoryId != null) {

				if (postedBy != null) {

					if (dateCondition != null) {

						switch (dateCondition) {

						case "between":
							finalPredecate = criteriaBuilder.or(titlePredicate, categoryIdPredicate, postedByPredicate,
									fromDatePredicate, toDatePredicate);
							break;
						case "lessthan":
							finalPredecate = criteriaBuilder.or(titlePredicate, categoryIdPredicate, postedByPredicate,
									lessThanPredicate);
							break;
						case "greatethan":
							finalPredecate = criteriaBuilder.or(titlePredicate, categoryIdPredicate, postedByPredicate,
									geaterThanPredicate);
							break;
						case "equals":
							finalPredecate = criteriaBuilder.or(titlePredicate, categoryIdPredicate, postedByPredicate,
									onDatePredicate);
							break;
							default:
								break;
						}
						
						if(sortedBy != null) {
							criteriaQuery.orderBy(sortedOrder);
						}
					} 

				} else {

				}
				

			} else {

			}

		} else {

		}

		criteriaQuery.where(finalPredecate);
		TypedQuery<AdvertiseEntity> query = entityManager.createQuery(criteriaQuery);
		List<AdvertiseEntity> advertiseEntities = query.getResultList();
		return getAdvertiseDtoListFromEntityList(advertiseEntities);
	}

	@Override
	public List<Advertise> getAllAdvertiseBySearchText(String searchText) {

		List<AdvertiseEntity> similarEntityList = advertiseRepo.findByTitleOrDescriptionOrCategorySimilar(searchText);
		return getAdvertiseDtoListFromEntityList(similarEntityList);
	}

	@Override
	public Advertise getAdvertiseDetails(int advertiseId, String authToken) {
		if (!loginDelegate.isTokenValid(authToken)) {
			throw new InvalidUserException(authToken);
		}

		Optional<AdvertiseEntity> advertiseOptional = advertiseRepo.findById(advertiseId);
		if (advertiseOptional.isPresent()) {
			AdvertiseEntity advertiseEntity = advertiseOptional.get();
			Advertise fromEntity = getAdvertiseDtoFromEntity(advertiseEntity);
			fromEntity.setCategoryId(advertiseId);
			return fromEntity;
		} else {
			throw new InvalidAdvertiseIdException("Invalid Advertise Id");
		}
	}

}
