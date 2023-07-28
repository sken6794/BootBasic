package com.simple.basic.practice;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PageVO {
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int pageNum;
	private int amount;
	private int total;
	
	private int realEnd;
	private List<Integer> pageList;
	
	private Criteria cri; //페이징 기준

	public PageVO(int total, Criteria cri) {
		super();
		this.total = total;
		this.cri = cri;
		this.pageNum = cri.getPageNum();
		this.amount = cri.getAmount();
		
		this.endPage = (int)(Math.ceil(this.pageNum/10.0)) *10;
		this.startPage = endPage - 10 +1;
		this.realEnd = (int)Math.ceil(total/(double)this.amount);
		
		if(this.endPage > realEnd) this.endPage = realEnd;
		
		this.prev = this.startPage > 1;
		
		this.next = realEnd>this.endPage;
		this.pageList = IntStream.rangeClosed(this.startPage, this.endPage).boxed().collect(Collectors.toList());
	}
	
	
}
