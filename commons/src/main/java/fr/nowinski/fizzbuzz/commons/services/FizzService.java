package fr.nowinski.fizzbuzz.commons.services;

import java.util.List;

import fr.nowinski.fizzbuzz.commons.dto.Page;
import fr.nowinski.fizzbuzz.commons.model.Statistic;

public interface FizzService {

	public List<String> getFizzBuzzList(final Page page);

	public List<Statistic> getStatistic();

}
