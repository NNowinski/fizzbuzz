package fr.nowinski.fizzbuzz.commons.dto;

import java.io.Serializable;

import fr.nowinski.fizzbuzz.commons.model.Statistic;
import fr.nowinski.fizzbuzz.commons.model.StatisticId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatisticDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5270379502487914250L;

	private String str1;
	private String str2;
	private Integer int1;
	private Integer int2;
	private Integer limit;
	private Integer numberOfHits;

	public StatisticDto(Statistic statistic) {
		final StatisticId id = statistic.getId();
		this.str1 = id.getStr1();
		this.str2 = id.getStr2();
		this.limit = id.getLimit();
		this.int1 = id.getInt1();
		this.int2 = id.getInt2();
		this.numberOfHits = statistic.getCount();
	}

}
