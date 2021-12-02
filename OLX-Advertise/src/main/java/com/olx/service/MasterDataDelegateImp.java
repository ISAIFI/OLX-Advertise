package com.olx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.olx.dto.Category;
import com.olx.dto.Status;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MasterDataDelegateImp implements MasterDataDelegate{

	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Override
	@CircuitBreaker(name = "CATEGORY-CIRCUIT-BREAKER", fallbackMethod = "fallbackGetAllCategories")
	public List<Map> getAllCategories() {
		// To call master data micro serviceusing RestTemplate
		//List list = restTemplate.getForObject("http://localhost:9001/olx/masterdata/advertise/category", List.class);
		//List list = restTemplate.getForObject("http://masterdata-service/olx/masterdata/advertise/category", List.class);
		List list = restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/category", List.class);

		return list;
	}
	
	public List<Map> fallbackGetAllCategories(Throwable throwable){
	
		System.out.print("Masterdata Service Fail : "+throwable);
		return null;
	}
	
	@Override
	@CircuitBreaker(name = "CATEGORY-DETAILS-CIRCUIT-BREAKER", fallbackMethod = "fallbackGetCategoryDetail")
	public Category getCategoryDetails(int categoryId) {
	
		Category category = restTemplate.getForObject("http://API-GATEWAY/olx/masterdata//advertise/category/"+categoryId, Category.class);
		return category;
	}
	
	public Category fallbackGetCategoryDetail(Throwable throwable){
	
		System.out.print("Category Details Service Fail : "+throwable);
		return null;
	}

	@Override
	@CircuitBreaker(name = "STATUS-DETAILS-CIRCUIT-BREAKER", fallbackMethod = "fallbackGetStatusDetail")
	public Status getStatusDetails(int statusId) {
		Status status = restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/status/"+statusId, Status.class);
		return status;
	}
	
	public Status fallbackGetStatusDetail(int statusId, Throwable throwable){
		
		System.out.print("Status Details Service Fail : "+throwable);
		return null;
	}

}
