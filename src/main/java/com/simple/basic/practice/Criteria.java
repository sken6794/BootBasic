package com.simple.basic.practice;

import lombok.Data;

@Data
public class Criteria {
	private int pageNum;
	private int amount;
	
	//검색에 필요한 키워드를 선언
	private String searchName;
	private String searchContent;
	private String searchPrice;
	private String startDate;
	private String endDate;
	
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	
}
