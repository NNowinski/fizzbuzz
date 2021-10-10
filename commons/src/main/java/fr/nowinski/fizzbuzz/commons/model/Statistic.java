package fr.nowinski.fizzbuzz.commons.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Statistic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 758120552964978270L;

	/**
	 * initialize Statistic with paramters
	 * 
	 * @param int1  first int
	 * @param int2  second int
	 * @param str1  first string
	 * @param str2  second string
	 * @param limit limit of list
	 */
	public Statistic(Integer int1, Integer int2, String str1, String str2, Integer limit) {
		this.id = new StatisticId(int1, int2, str1, str2, limit);
	}

	/**
	 * initialize statistic with id
	 * 
	 * @param id id of statistic
	 */
	public Statistic(StatisticId id) {
		this.id = id;
	}

	@EmbeddedId
	private StatisticId id;

	private int count = 0;

	public Statistic addCount() {
		this.count++;
		return this;
	}

}
