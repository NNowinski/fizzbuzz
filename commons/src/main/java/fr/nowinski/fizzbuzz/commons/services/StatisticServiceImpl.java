package fr.nowinski.fizzbuzz.commons.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.nowinski.fizzbuzz.commons.dao.StatisticRepository;
import fr.nowinski.fizzbuzz.commons.dto.StatisticDto;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private StatisticRepository statisticRepository;

	@Override
	public List<StatisticDto> getStatistics() {
		return this.statisticRepository.getMaxQueryUse();
	}

}
