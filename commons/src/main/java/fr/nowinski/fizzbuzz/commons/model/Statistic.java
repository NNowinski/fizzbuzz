package fr.nowinski.fizzbuzz.commons.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Statistic {

	public Statistic() {

	}

	/**
	 * Constructeur pour initaliser une statistique
	 * 
	 * @param int1  premier int
	 * @param int2  deuxieme int
	 * @param str1  premier string à remplacer
	 * @param str2  second string à remplacer
	 * @param limit à retourner
	 */
	public Statistic(Integer int1, Integer int2, String str1, String str2, Integer limit) {
		this.id = new StatisticId(int1, int2, str1, str2, limit);
	}

	public Statistic(StatisticId id) {
		this.id = id;
	}

	@EmbeddedId
	private StatisticId id;

	private int count = 0;

	public StatisticId getId() {
		return this.id;
	}

	public void setId(StatisticId id) {
		this.id = id;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Statistic addCount() {
		this.count++;
		return this;
	}

}
