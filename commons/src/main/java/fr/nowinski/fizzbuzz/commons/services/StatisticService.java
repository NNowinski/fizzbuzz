package fr.nowinski.fizzbuzz.commons.services;

import java.util.List;

import fr.nowinski.fizzbuzz.commons.dto.StatisticDto;

/**
 * 
 * Service for statistics
 *
 */
public interface StatisticService {

	/**
	 * The most frequent request has been
	 * 
	 * @return statisticsdto
	 */
	public List<StatisticDto> getStatistics();
}
