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
import fr.nowinski.fizzbuzz.commons.services.FizzBuzzService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FizzBuzzControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private FizzBuzzService fizzBuzzService;

	@Captor
	private ArgumentCaptor<PageDto> pageCaptor;

	private String baseUrl;

	@BeforeEach
	public void setUp() {
		baseUrl = String.format("http://localhost:%d/", port);
	}

	@Test
	void test_fizzbuzz() {
		final List<String> fizzbuzz = Arrays.asList("1", "2");
		Mockito.when(fizzBuzzService.getFizzBuzzList(Mockito.any(PageDto.class))).thenReturn(fizzbuzz);

		ResponseEntity<List<String>> response = restTemplate.exchange(
				baseUrl + "fizzbuzz?int1=4&int2=5&str1=fizz&str2=toto&limit=102", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<String>>() {
				});

		Mockito.verify(fizzBuzzService).getFizzBuzzList(pageCaptor.capture());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(4, pageCaptor.getValue().getInt1());
		Assertions.assertEquals("toto", pageCaptor.getValue().getStr2());
		Assertions.assertEquals(102, pageCaptor.getValue().getLimit());
	}
}
