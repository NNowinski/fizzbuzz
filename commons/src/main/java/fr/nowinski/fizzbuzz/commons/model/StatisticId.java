package fr.nowinski.fizzbuzz.commons.model;

import java.io.Serializable;

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
	private Integer int1;
	private Integer int2;
	private Integer limit;

	public StatisticId(Integer int1, Integer int2, String str1, String str2, Integer limit) {
		this.str1 = str1;
		this.str2 = str2;
		this.int1 = int1;
		this.int2 = int2;
		this.limit = limit;
	}

}