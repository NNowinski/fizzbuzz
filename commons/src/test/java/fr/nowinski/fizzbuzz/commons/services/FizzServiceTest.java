package fr.nowinski.fizzbuzz.commons.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.nowinski.fizzbuzz.commons.dao.StatisticRepository;
import fr.nowinski.fizzbuzz.commons.dto.Page;
import fr.nowinski.fizzbuzz.commons.model.Statistic;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FizzServiceTest {

	@Autowired
	private FizzService fizzService;

	@Autowired
	private StatisticRepository statisticRepository;

	@BeforeEach
	public void clearBdd() {
		this.statisticRepository.deleteAll();
	}

	@Test
	public void testGetFizzBuzzList() {
		final Page page = new Page(1, 2, "toto", "tata", 100);

		final List<String> fizzbuzz = this.fizzService.getFizzBuzzList(page);

		Assertions.assertNotNull(fizzbuzz);
		Assertions.assertEquals(page.getLimit(), fizzbuzz.size());
		Assertions.assertEquals(50, Collections.frequency(fizzbuzz, page.getStr1()));
		Assertions.assertEquals(0, Collections.frequency(fizzbuzz, page.getStr2()));
		Assertions.assertEquals(50, Collections.frequency(fizzbuzz, page.getStr1() + page.getStr2()));
		final Statistic stat = statisticRepository.findById(page.toStatistic().getId()).orElseThrow();
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(1, stat.getCount());
	}

	@Test
	public void testCountFizzBuzzList() {
		final Page page = new Page(1, 2, "toto", "tata", 100);
		final Page page2 = new Page(1, 5, "toto", "tata", 100);
		final Page page3 = new Page(1, 9, "toto", "tata", 100);

		this.fizzService.getFizzBuzzList(page);
		this.fizzService.getFizzBuzzList(page);
		this.fizzService.getFizzBuzzList(page);
		this.fizzService.getFizzBuzzList(page2);

		final Statistic stat = statisticRepository.findById(page.toStatistic().getId()).orElseThrow();
		final Statistic stat2 = statisticRepository.findById(page2.toStatistic().getId()).orElseThrow();
		final Optional<Statistic> stat3 = statisticRepository.findById(page3.toStatistic().getId());
		Assertions.assertNotNull(stat);
		Assertions.assertNotNull(stat2);
		Assertions.assertEquals(3, stat.getCount());
		Assertions.assertEquals(1, stat2.getCount());
		Assertions.assertTrue(stat3.isEmpty());
	}

}
