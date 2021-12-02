package com.olx.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertise;
import com.olx.service.AdvertisementService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/advertise")
public class AdvertisementController {

	@Autowired
	AdvertisementService advertisementService;

	/*
	 * private static Map<Integer, Advertise> advertiseMap = new HashMap<Integer,
	 * Advertise>(); // private static int lastAdvertiseId = 3; // static { //
	 * advertiseMap.put(1, new Advertise(1, "Laptop Sale", "intel core 3", 20000));
	 * // advertiseMap.put(2, new Advertise(2, "Furniture",
	 * "Good furniture in better price",89000)); // advertiseMap.put(3, new
	 * Advertise(3, "MobileSale", "Oppo, Mi, Vivo, Samsung", 50000)); // }
	 * 
	 * 
	 * @PostMapping(value = "/advertise", consumes =
	 * MediaType.APPLICATION_JSON_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Advertise
	 * createNewAdvertise(@RequestBody Advertise advertise) { // lastAdvertiseId++;
	 * // advertise.setCategoryId(lastAdvertiseId); //
	 * advertiseMap.put(lastAdvertiseId, advertise); // return advertise; return
	 * null; }
	 * 
	 * 
	 * @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
	 * produces = MediaType.APPLICATION_JSON_VALUE) public Advertise
	 * updateAdvertiseById(@PathVariable("id") int advertiseId, @RequestBody
	 * Advertise advertise) {
	 * 
	 * Advertise existingAdvertise = advertiseMap.get(advertiseId);
	 * existingAdvertise.setPrice(advertise.getPrice());
	 * existingAdvertise.setTitle(advertise.getTitle());
	 * existingAdvertise.setDescription(advertise.getDescription()); return
	 * existingAdvertise; }
	 * 
	 * @GetMapping(value = "/user/advertise", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Collection<Advertise>
	 * getAllAdvertise(){ return advertiseMap.values(); }
	 * 
	 * @GetMapping(value = "/user/advertise/{advertiseId} ", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Advertise
	 * getAdvertiseById(@RequestParam("advertiseId") String advertiseId){
	 * 
	 * return advertiseMap.get(advertiseId);
	 * 
	 * }
	 * 
	 * @DeleteMapping(value = "/user/advertise/{advertiseId} ", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Boolean
	 * deleteAdvertiseById(@RequestParam("advertiseId") String advertiseId){
	 * 
	 * advertiseMap.remove(advertiseId); return true;
	 * 
	 * }
	 * 
	 * @GetMapping(value = "/search/{searchText}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public List<Advertise>
	 * getMatchAdvertise(@RequestParam("searchText") String searchText){
	 * 
	 * return null;
	 * 
	 * }
	 * 
	 * @GetMapping(value = "/{advertiseId}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Advertise
	 * getAdvertiseDetails(@RequestParam("advertiseId") String advertiseId){
	 * 
	 * return null;
	 * 
	 * }
	 */

	// 07
	@ApiOperation(value = "Create New Advertise")
	@PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Advertise> createNewAdvertise(@RequestHeader("auth-token") String authToken,
			@RequestBody Advertise advertise) {

		return new ResponseEntity<Advertise>(advertisementService.createNewAdvertise(authToken, advertise),
				HttpStatus.OK);
	}

	// 08
	@ApiOperation(value = "Update Existing Advertise")
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Advertise> updateAdvertiseById(@RequestHeader("auth-token") String authToken,
			@PathVariable("id") int advertiseId, @RequestBody Advertise advertise) {

		return new ResponseEntity<Advertise>(
				advertisementService.updateAdvertiseById(advertiseId, authToken, advertise), HttpStatus.OK);
	}

	// 09
	@ApiOperation(value = "Get All Advertise")
	@GetMapping(value = "/user/advertise", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Advertise>> getAllAdvertise(@RequestHeader("auth-token") String authToken) {
		return new ResponseEntity<List<Advertise>>(advertisementService.getAllAdvertiseByLoggedinUser(authToken),
				HttpStatus.OK);

	}

	// 10
	@ApiOperation(value = "Get Advertise by Id")
	@GetMapping(value = "/user/advertisebyid", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Advertise> getAdvertiseById(@RequestHeader("auth-token") String authToken,
			@RequestParam("advertiseId") int advertiseId) {

		return new ResponseEntity<Advertise>(advertisementService.getAdvertiseById(advertiseId, authToken),
				HttpStatus.OK);

	}

	// 11
	@ApiOperation(value = "Delete Existing Advertise by Id")
	@DeleteMapping(value = "/user/advertise/delete", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> deleteAdvertiseById(@RequestHeader("auth-token") String authToken,
			@RequestParam("advertiseId") int advertiseId) {

		return new ResponseEntity<Boolean>(advertisementService.removeAdvertiseById(advertiseId, authToken),
				HttpStatus.OK);
	}

	// 12
	@ApiOperation(value = "Search Advertise by Filter Criteria")
	@GetMapping(value = "/search/filtercriteria", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Advertise> searchAdvertiseByFilterCriteria(
			@RequestParam(value = "searchText", required = false) String serachText,
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "postedBy", required = false) String postedBy,
			@RequestParam(value = "dateCondition", required = false) String dateCondition,
			@RequestParam(value = "onDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
			@RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam(value = "sortedBy", required = false) String sortedBy,
			@RequestParam(value = "startIndex", required = false) Integer startIndex,
			@RequestParam(value = "records", required = false) Integer records) {

		return advertisementService.searchAdvertiseByFilterCriteria(serachText, categoryId, postedBy, dateCondition,
				onDate, fromDate, toDate, sortedBy, startIndex, records);
	}

	// 13
	@ApiOperation(value = "Get All Advertise by Search Text")
	@GetMapping(value = "/search", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Advertise>> getAllAdvertiseBySearchText(@RequestParam("searchText") String searchText) {
		return new ResponseEntity<List<Advertise>>(advertisementService.getAllAdvertiseBySearchText(searchText),
				HttpStatus.OK);

	}

	// 14
	@ApiOperation(value = "Get Advertise Details by Id")
	@GetMapping(value = "/{advertiseId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Advertise> getAdvertiseDetails(@RequestHeader("auth-token") String authToken,
			@PathVariable("advertiseId") int advertiseId) {

		return new ResponseEntity<Advertise>(advertisementService.getAdvertiseDetails(advertiseId, authToken),
				HttpStatus.OK);

	}

}
