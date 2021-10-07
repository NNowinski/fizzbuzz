package fr.nowinski.fizzbuzz.commons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.nowinski.fizzbuzz.commons.dto.StatisticDto;
import fr.nowinski.fizzbuzz.commons.model.Statistic;
import fr.nowinski.fizzbuzz.commons.model.StatisticId;

public interface StatisticRepository extends CrudRepository<Statistic, StatisticId> {

	@Query("SELECT new StatisticDto(s) FROM Statistic s WHERE s.count = (SELECT MAX(s2.count) FROM Statistic s2)")
	List<StatisticDto> getMaxQuaryUse();

}
