package fr.nowinski.fizzbuzz.commons.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.nowinski.fizzbuzz.commons.dao.StatisticRepository;
import fr.nowinski.fizzbuzz.commons.dto.Page;
import fr.nowinski.fizzbuzz.commons.model.Statistic;

@Service
public class FizzServiceImpl implements FizzService {

	@Autowired
	private StatisticRepository statisticRepository;

	@Override
	public List<String> getFizzBuzzList(final Page page) {
		// sauvegarde de la requete pour les stats avec +1
		this.statisticRepository.save(this.statisticRepository.findById(page.toStatistic().getId())
				.orElseGet(() -> page.toStatistic()).addCount());

		final List<String> list = new ArrayList<>();
		for (int i = 1; i <= page.getLimit(); i++) {
			if (i % (page.getInt1() * page.getInt2()) == 0) {
				list.add(page.getStr1() + page.getStr2());
			} else if (i % page.getInt1() == 0) {
				list.add(page.getStr1());
			} else if (i % page.getInt2() == 0) {
				list.add(page.getStr2());
			} else {
				list.add(String.valueOf(i));
			}

		}
		return list;
	}

	@Override
	public List<Statistic> getStatistic() {
		return this.statisticRepository.getMaxQuaryUse();
	}

}
