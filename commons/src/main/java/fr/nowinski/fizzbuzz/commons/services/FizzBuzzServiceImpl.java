package fr.nowinski.fizzbuzz.commons.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.nowinski.fizzbuzz.commons.dao.StatisticRepository;
import fr.nowinski.fizzbuzz.commons.dto.PageDto;

@Service
public class FizzBuzzServiceImpl implements FizzBuzzService {

	@Autowired
	private StatisticRepository statisticRepository;

	@Override
	public List<Object> getFizzBuzzList(final PageDto page) {
		// save stats
		this.statisticRepository.save(
				this.statisticRepository.findById(page.toStatistic().getId()).orElseGet(page::toStatistic).addCount());

		final List<Object> list = new ArrayList<>();
		for (int i = 1; i <= page.getLimit(); i++) {
			final boolean isDivisibleByInt1 = i % page.getInt1() == 0;
			final boolean isDivisibleByInt2 = i % page.getInt2() == 0;
			if (isDivisibleByInt1 && isDivisibleByInt2) {
				list.add(page.getStr1() + page.getStr2());
			} else if (isDivisibleByInt1) {
				list.add(page.getStr1());
			} else if (isDivisibleByInt2) {
				list.add(page.getStr2());
			} else {
				list.add(i);
			}

		}
		return list;
	}

}
