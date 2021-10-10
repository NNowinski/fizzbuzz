package fr.nowinski.fizzbuzz.commons.services;

import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.nowinski.fizzbuzz.commons.dao.StatisticRepository;
import fr.nowinski.fizzbuzz.commons.dto.PageDto;
import fr.nowinski.fizzbuzz.commons.model.Statistic;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StatisticServiceTest {
	@Autowired
	private FizzBuzzService fizzBuzzService;

	@Autowired
	private StatisticRepository statisticRepository;

	@BeforeEach
	public void clearBdd() {
		this.statisticRepository.deleteAll();
	}

	private final static Random r = new Random();

	@RepeatedTest(10)
	void test_count_fizzbuzzlist() {
		final PageDto page = new PageDto(1, 2, "toto", "tata", 100);
		final PageDto page2 = new PageDto(1, 5, "toto", "tata", 100);
		final PageDto page3 = new PageDto(1, 9, "toto", "tata", 100);
		final int limit = r.nextInt(99) + 1;
		final int limit2 = r.nextInt(99) + 1;

		for (int i = 0; i < limit; i++) {
			this.fizzBuzzService.getFizzBuzzList(page);
		}
		for (int i = 0; i < limit2; i++) {
			this.fizzBuzzService.getFizzBuzzList(page2);
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

}
