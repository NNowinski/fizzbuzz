package fr.nowinski.fizzbuzz.rest.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.nowinski.fizzbuzz.commons.dto.PageDto;
import fr.nowinski.fizzbuzz.commons.dto.StatisticDto;
import fr.nowinski.fizzbuzz.commons.services.StatisticService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StatisticControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private StatisticService statisticService;

	@Captor
	private ArgumentCaptor<PageDto> pageCaptor;

	private String baseUrl;

	@BeforeEach
	public void setUp() {
		baseUrl = String.format("http://localhost:%d/", port);
	}

	@Test
	void test_statistics() {
		final StatisticDto stat = new StatisticDto();
		stat.setStr1("toto");
		stat.setNumberOfHits(5);
		final List<StatisticDto> stats = Arrays.asList(stat);
		Mockito.when(statisticService.getStatistics()).thenReturn(stats);

		ResponseEntity<List<StatisticDto>> response = restTemplate.exchange(baseUrl + "statistics", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<StatisticDto>>() {
				});

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(stats.size(), response.getBody().size());
		Assertions.assertEquals(stats.get(0).getStr1(), response.getBody().get(0).getStr1());
		Assertions.assertEquals(stats.get(0).getNumberOfHits(), response.getBody().get(0).getNumberOfHits());
	}

}
