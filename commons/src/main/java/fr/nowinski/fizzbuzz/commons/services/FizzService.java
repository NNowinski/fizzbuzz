package fr.nowinski.fizzbuzz.commons.services;

import java.util.List;

import fr.nowinski.fizzbuzz.commons.dto.PageDto;
import fr.nowinski.fizzbuzz.commons.dto.StatisticDto;

public interface FizzService {

	public List<String> getFizzBuzzList(final PageDto page);

	public List<StatisticDto> getStatistic();

}
