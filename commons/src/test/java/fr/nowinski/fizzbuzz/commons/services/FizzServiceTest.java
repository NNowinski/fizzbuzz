package fr.nowinski.fizzbuzz.commons.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.nowinski.fizzbuzz.commons.dao.StatisticRepository;
import fr.nowinski.fizzbuzz.commons.dto.PageDto;
import fr.nowinski.fizzbuzz.commons.model.Statistic;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class FizzServiceTest {

	@Autowired
	private FizzService fizzService;

	@Autowired
	private StatisticRepository statisticRepository;

	@BeforeEach
	public void clearBdd() {
		this.statisticRepository.deleteAll();
	}

	private final static Random r = new Random();

	private final Logger logger = LoggerFactory.getLogger(FizzServiceTest.class);

	@RepeatedTest(1000)
	void testGetFizzBuzzList() {
		final int int1 = r.nextInt(999) + 1;
		final int int2 = r.nextInt(99) + 1;
		final String str1 = "toto";
		final String str2 = "tata";
		final int limit = r.nextInt(int1 * int2);
		final int q = limit / lcm(int1, int2);
		final int q1 = limit / int1 - q;
		final int q2 = limit / int2 - q;
		final PageDto page = new PageDto(int1, int2, str1, str2, limit);

		final List<String> fizzbuzz = this.fizzService.getFizzBuzzList(page);
		logger.info("int1 {}, int2 {}, limit {}", int1, int2, limit);

		Assertions.assertNotNull(fizzbuzz);
		Assertions.assertEquals(page.getLimit(), fizzbuzz.size());
		Assertions.assertEquals(q1, Collections.frequency(fizzbuzz, page.getStr1()));
		Assertions.assertEquals(q2, Collections.frequency(fizzbuzz, page.getStr2()));
		Assertions.assertEquals(q, Collections.frequency(fizzbuzz, page.getStr1() + page.getStr2()));
		final Statistic stat = statisticRepository.findById(page.toStatistic().getId()).orElseThrow();
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(1, stat.getCount());
	}

	@RepeatedTest(10)
	void testCountFizzBuzzList() {
		final PageDto page = new PageDto(1, 2, "toto", "tata", 100);
		final PageDto page2 = new PageDto(1, 5, "toto", "tata", 100);
		final PageDto page3 = new PageDto(1, 9, "toto", "tata", 100);
		final int limit = r.nextInt(99) + 1;
		final int limit2 = r.nextInt(99) + 1;

		for (int i = 0; i < limit; i++) {
			this.fizzService.getFizzBuzzList(page);
		}
		for (int i = 0; i < limit2; i++) {
			this.fizzService.getFizzBuzzList(page2);
		}

		final Statistic stat = statisticRepository.findById(page.toStatistic().getId()).orElseThrow();
		final Statistic stat2 = statisticRepository.findById(page2.toStatistic().getId()).orElseThrow();
		final Optional<Statistic> stat3 = statisticRepository.findById(page3.toStatistic().getId());
		Assertions.assertNotNull(stat);
		Assertions.assertNotNull(stat2);
		Assertions.assertEquals(limit, stat.getCount());
		Assertions.assertEquals(limit2, stat2.getCount());
		Assertions.assertTrue(stat3.isEmpty());
	}

	private int lcm(int number1, int number2) {
		if (number1 == 0 || number2 == 0) {
			return 0;
		}
		int absNumber1 = Math.abs(number1);
		int absNumber2 = Math.abs(number2);
		int absHigherNumber = Math.max(absNumber1, absNumber2);
		int absLowerNumber = Math.min(absNumber1, absNumber2);
		int lcm = absHigherNumber;
		while (lcm % absLowerNumber != 0) {
			lcm += absHigherNumber;
		}
		return lcm;
	}
}
