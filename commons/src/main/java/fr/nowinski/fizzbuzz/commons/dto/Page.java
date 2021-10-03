package fr.nowinski.fizzbuzz.commons.dto;

import fr.nowinski.fizzbuzz.commons.model.Statistic;

public class Page {

	private String str1;
	private String str2;
	private Integer int1;
	private Integer int2;
	private Integer limit;

	public Page() {
	}

	public Page(Integer int1, Integer int2, String str1, String str2, Integer limit) {
		this.int1 = int1;
		this.int2 = int2;
		this.str1 = str1;
		this.str2 = str2;
		this.limit = limit;
	}

	public String getStr1() {
		return this.str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return this.str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public Integer getInt1() {
		return this.int1;
	}

	public void setInt1(Integer int1) {
		this.int1 = int1;
	}

	public Integer getInt2() {
		return this.int2;
	}

	public void setInt2(Integer int2) {
		this.int2 = int2;
	}

	public Integer getLimit() {
		return this.limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public boolean validatePage() {
		return this.str1 == null && this.str2 == null && this.limit == null && this.int1 == null && this.int2 == null;
	}

	public Statistic toStatistic() {
		return new Statistic(this.int1, this.int2, this.str1, this.str2, this.limit);
	}

}
