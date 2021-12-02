package com.olx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.olx.entity.AdvertiseEntity;

@Service
public interface AdvertiseRepo extends JpaRepository<AdvertiseEntity, Integer> {

	// Find Advertise by posted_by
	List<AdvertiseEntity> findByPostedBy(String postedBy);
	
	@Query(value = "SELECT ade FROM AdvertiseEntity ade WHERE ade.title LIKE %:advertiseTitle% OR ade.description LIKE %:advertiseTitle% OR ade.category LIKE %:advertiseTitle%")
	List<AdvertiseEntity> findByTitleOrDescriptionOrCategorySimilar(@Param("advertiseTitle") String title);
	
//	@Query(value = "SELECT ade FROM AdvertiseEntity ade WHERE ade.title LIKE %:advertiseTitle%")
//	List<AdvertiseEntity> findByTitleSimilar(@Param("advertiseTitle") String title);
//	
//	@Query(value = "SELECT ade FROM AdvertiseEntity ade WHERE ade.description LIKE %:advertiseDescription%")
//	List<AdvertiseEntity> findByDescriptionSimilar(@Param("advertiseDescription") String description);
//	
//	@Query(value = "SELECT ade FROM AdvertiseEntity ade WHERE ade.category LIKE %:advertiseCategory%")
//	List<AdvertiseEntity> findByCategorySimilar(@Param("advertiseCategory") String category);
	
}
