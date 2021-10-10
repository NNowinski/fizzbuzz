package fr.nowinski.fizzbuzz.commons.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class StatisticId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3037705380350389210L;
	private String str1;
	private String str2;
	@Column(name = "number_1")
	private Integer int1;
	@Column(name = "number_2")
	private Integer int2;
	private Integer limitOfList;

	/**
	 * initialize StatisticId
	 * 
	 * @param int1        first int
	 * @param int2        second int
	 * @param str1        first string
	 * @param str2        second string
	 * @param limitOfList limit of list
	 */
	public StatisticId(Integer int1, Integer int2, String str1, String str2, Integer limitOfList) {
		this.str1 = str1;
		this.str2 = str2;
		this.int1 = int1;
		this.int2 = int2;
		this.limitOfList = limitOfList;
	}

}