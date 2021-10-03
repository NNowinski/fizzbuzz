package fr.nowinski.fizzbuzz.commons.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.nowinski.fizzbuzz.commons.model.Statistic;
import fr.nowinski.fizzbuzz.commons.model.StatisticId;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StatisticRepositoryTest {

	@Autowired
	private StatisticRepository statisticRepository;

	@BeforeEach
	public void clearBdd() {
		this.statisticRepository.deleteAll();
	}

	@Test
	public void saveStatistic() {
		final StatisticId id = new StatisticId(1, 2, "toto", "tata", 5);

		this.statisticRepository.save(new Statistic(id));

		Statistic stat = this.statisticRepository.findById(id).orElseThrow();
		Assertions.assertEquals(1, stat.getId().getInt1());
		Assertions.assertEquals("toto", stat.getId().getStr1());
		Assertions.assertEquals(1, stat.getCount());
	}
}
