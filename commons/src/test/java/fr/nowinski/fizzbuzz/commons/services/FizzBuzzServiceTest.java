package fr.nowinski.fizzbuzz.commons.services;

import java.util.Collections;
import java.util.List;
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
class FizzBuzzServiceTest {

	@Autowired
	private FizzBuzzService fizzBuzzService;

	@Autowired
	private StatisticRepository statisticRepository;

	@BeforeEach
	public void clearBdd() {
		this.statisticRepository.deleteAll();
	}

	private final static Random r = new Random();

	private final Logger logger = LoggerFactory.getLogger(FizzBuzzServiceTest.class);

	@RepeatedTest(100)
	void test_get_fizzbuzzlist() {
		// generate random number for int1
		final int int1 = r.nextInt(999) + 1;
		// generate random number for int2
		final int int2 = r.nextInt(99) + 1;
		final String str1 = "toto";
		final String str2 = "tata";
		// generate random number for limit
		final int limit = r.nextInt(int1 * int2) + Math.min(int1, int2);
		final int lcm = lcm(int1, int2);
		// count number divisible by int1 and int2
		final int q = limit / lcm;
		// count number divisble by int1 less int1 and int2
		final int q1 = limit / int1 - q;
		// count number divisble by int2 less int1 and int2
		final int q2 = limit / int2 - q;
		final PageDto page = new PageDto(int1, int2, str1, str2, limit);

		final List<String> fizzbuzz = this.fizzBuzzService.getFizzBuzzList(page);
		logger.info("int1 {}, int2 {}, limit {}", int1, int2, limit);

		Assertions.assertNotNull(fizzbuzz);
		Assertions.assertEquals(page.getLimit(), fizzbuzz.size());
		// check number of toto
		Assertions.assertEquals(q1, Collections.frequency(fizzbuzz, page.getStr1()));
		if (lcm - 1 != int1 - 1 && int1 <= limit) {
			Assertions.assertEquals(page.getStr1(), fizzbuzz.get(int1 - 1));
		}
		// check number of tata
		Assertions.assertEquals(q2, Collections.frequency(fizzbuzz, page.getStr2()));
		if (lcm - 1 != int2 - 1 && int1 - 1 != int2 - 1 && int2 <= limit) {
			Assertions.assertEquals(page.getStr2(), fizzbuzz.get(int2 - 1));
		}
		// check number of tototata
		Assertions.assertEquals(q, Collections.frequency(fizzbuzz, page.getStr1() + page.getStr2()));
		if (q > 0) {
			Assertions.assertEquals(page.getStr1() + page.getStr2(), fizzbuzz.get(lcm - 1));
		}
		final Statistic stat = statisticRepository.findById(page.toStatistic().getId()).orElseThrow();
		Assertions.assertNotNull(stat);
		// check that the stats have been save
		Assertions.assertEquals(1, stat.getCount());
	}

	/**
	 * determine the least common multiple between two numbers
	 * 
	 * @param number1
	 * @param number2
	 * @return least common multiple
	 */
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
