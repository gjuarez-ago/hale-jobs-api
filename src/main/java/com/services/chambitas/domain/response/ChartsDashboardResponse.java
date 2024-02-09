package com.services.chambitas.domain.response;

import java.util.List;

import lombok.Data;

@Data
public class ChartsDashboardResponse {
	
	private List<String> rangeAmounts;

	private List<String> subcategories;

	private List<Integer> offersByMonth;
	
	private List<Integer> postulatesByMonth;

}
