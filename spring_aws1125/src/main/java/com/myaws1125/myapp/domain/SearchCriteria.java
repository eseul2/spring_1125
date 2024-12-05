package com.myaws1125.myapp.domain;

public class SearchCriteria extends Criteria  {
	
	
	private String searchType; 	
	private String keyword;		
	private String area;     // 추가된 필드: 지역
	
	
	
	
	
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

	
}
