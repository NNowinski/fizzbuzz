package fr.nowinski.fizzbuzz.commons.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.nowinski.fizzbuzz.commons.dto.StatisticDto;
import fr.nowinski.fizzbuzz.commons.model.Statistic;
import fr.nowinski.fizzbuzz.commons.model.id.StatisticId;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class StatisticRepositoryTest {

	@Autowired
	private StatisticRepository statisticRepository;

	@BeforeEach
	public void clearBdd() {
		this.statisticRepository.deleteAll();
	}

	@Test
	@Order(1)
	void test_save_statistic() {
		final StatisticId id = new StatisticId(1, 2, "toto", "tata", 5);

		this.statisticRepository.save(new Statistic(id));

		final Statistic stat = this.statisticRepository.findById(id).orElseThrow();
		Assertions.assertEquals(1, stat.getId().getInt1());
		Assertions.assertEquals("toto", stat.getId().getStr1());
		Assertions.assertEquals(0, stat.getCount());
	}

	@Test
	void test_find_statistics() {
		final StatisticId id = new StatisticId(1, 2, "toto", "tata", 5);
		this.statisticRepository.save(new Statistic(id).addCount().addCount().addCount());
		this.statisticRepository.save(new Statistic(new StatisticId(1, 4, "toto", "tata", 5)).addCount().addCount());
		this.statisticRepository.save(new Statistic(new StatisticId(1, 2, "toto", "titi", 1)));

		List<StatisticDto> stats = this.statisticRepository.getMaxQueryUse();

		Assertions.assertNotNull(stats);
		Assertions.assertEquals(1, stats.size());
		Assertions.assertEquals(id.getInt2(), stats.get(0).getInt2());
		Assertions.assertEquals(id.getLimitOfList(), stats.get(0).getLimit());
		Assertions.assertEquals(3, stats.get(0).getNumberOfHits());
	}

	@Test
	void test_find_statistics_2() {
		final StatisticId id = new StatisticId(1, 2, "toto", "tata", 5);
		final StatisticId id2 = new StatisticId(1, 4, "toto", "tata", 5);
		this.statisticRepository.save(new Statistic(id).addCount().addCount().addCount());
		this.statisticRepository.save(new Statistic(id2).addCount().addCount().addCount());
		this.statisticRepository.save(new Statistic(new StatisticId(1, 2, "toto", "titi", 1)));

		List<StatisticDto> stats = this.statisticRepository.getMaxQueryUse();

		Assertions.assertNotNull(stats);
		Assertions.assertEquals(2, stats.size());
		Assertions.assertEquals(id.getInt2(), stats.get(0).getInt2());
		Assertions.assertEquals(id2.getInt2(), stats.get(1).getInt2());
		Assertions.assertEquals(3, stats.get(0).getNumberOfHits());
		Assertions.assertEquals(3, stats.get(1).getNumberOfHits());
	}
}
