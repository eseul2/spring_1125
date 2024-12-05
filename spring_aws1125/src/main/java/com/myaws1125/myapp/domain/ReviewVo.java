package com.myaws1125.myapp.domain;

import java.util.Arrays;
import java.util.List;

public class ReviewVo {
	
	
	
	private String delyn;
    private double latitude; // 위도
    private double longitude; // 경도
	private int review_id;
	private int midx;
	private String review_contents;
	private String bakery_name;
	private String parking_info;
	private String area;
	private String bakery_phone;
	private String operating_hours;
	private String menu_info;
	private String filename;
	private List<String> uploadedFilenames; // 다중 파일 이름 저장
	private String address;
	private String modifyday;
	
	

	// ReviewVo에서 파일 이름을 분리해서 리스트로 반환하는 메서드를 추가
	public List<String> getFileNames() {
	    return Arrays.asList(this.filename.split(","));
	}
	
	public String getModifyday() {
		return modifyday;
	}

	public void setModifyday(String modifyday) {
		this.modifyday = modifyday;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getUploadedFilenames() {
	    return uploadedFilenames;
	}
	public void setUploadedFilenames(List<String> uploadedFilenames) {
	    this.uploadedFilenames = uploadedFilenames;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public String getReview_contents() {
		return review_contents;
	}
	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}
	public String getBakery_name() {
		return bakery_name;
	}
	public void setBakery_name(String bakery_name) {
		this.bakery_name = bakery_name;
	}
	public String getParking_info() {
		return parking_info;
	}
	public void setParking_info(String parking_info) {
		this.parking_info = parking_info;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBakery_phone() {
		return bakery_phone;
	}
	public void setBakery_phone(String bakery_phone) {
		this.bakery_phone = bakery_phone;
	}
	public String getOperating_hours() {
		return operating_hours;
	}
	public void setOperating_hours(String operating_hours) {
		this.operating_hours = operating_hours;
	}
	public String getMenu_info() {
		return menu_info;
	}
	public void setMenu_info(String menu_info) {
		this.menu_info = menu_info;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	

}
