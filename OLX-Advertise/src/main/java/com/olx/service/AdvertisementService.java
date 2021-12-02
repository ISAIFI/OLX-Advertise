package com.olx.service;

import java.time.LocalDate;
import java.util.List;

import com.olx.dto.Advertise;

public interface AdvertisementService {

	public Advertise createNewAdvertise(String authToken, Advertise advertise);// 7

	public Advertise updateAdvertiseById(int advertiseId, String authToken, Advertise advertise);// 8

	public List<Advertise> getAllAdvertiseByLoggedinUser(String authToken);// 9

	public Advertise getAdvertiseById(int advertiseId, String authToken);// 10

	public Boolean removeAdvertiseById(int advertiseId, String authToken);// 11

	public List<Advertise> searchAdvertiseByFilterCriteria(String searchText, Integer categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			Integer startIndex, Integer records);// 12

	public List<Advertise> getAllAdvertiseBySearchText(String searchText);// 13

	public Advertise getAdvertiseDetails(int advertiseId, String authToken);// 14

}
