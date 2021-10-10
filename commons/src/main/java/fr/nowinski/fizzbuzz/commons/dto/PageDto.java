package fr.nowinski.fizzbuzz.commons.dto;

import java.io.Serializable;

import fr.nowinski.fizzbuzz.commons.model.Statistic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5095734026139267245L;
	private String str1;
	private String str2;
	private Integer int1;
	private Integer int2;
	private Integer limit;

	public PageDto(Integer int1, Integer int2, String str1, String str2, Integer limit) {
		this.int1 = int1;
		this.int2 = int2;
		this.str1 = str1;
		this.str2 = str2;
		this.limit = limit;
	}

	/**
	 * to convert a pagedto to statistic
	 * 
	 * @return statistic
	 */
	public Statistic toStatistic() {
		return new Statistic(this.int1, this.int2, this.str1, this.str2, this.limit);
	}

}
