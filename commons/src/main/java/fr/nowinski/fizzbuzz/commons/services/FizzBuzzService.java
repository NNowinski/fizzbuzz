package fr.nowinski.fizzbuzz.commons.services;

import java.util.List;

import fr.nowinski.fizzbuzz.commons.dto.PageDto;

/**
 * 
 * Service for fizzbuzz list
 *
 */
public interface FizzBuzzService {

	/**
	 * list of strings with numbers from 1 to limit, where: all multiples of int1
	 * are replaced by str1, all multiples of int2 are replaced by str2, all
	 * multiples of int1 and int2 are replaced by str1str2
	 * 
	 * @param page
	 * @return list of strings
	 */
	public List<String> getFizzBuzzList(final PageDto page);

}
