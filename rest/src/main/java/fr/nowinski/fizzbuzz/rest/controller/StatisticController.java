package fr.nowinski.fizzbuzz.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.nowinski.fizzbuzz.commons.dto.StatisticDto;
import fr.nowinski.fizzbuzz.commons.services.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * 
 * Controller for statistic info
 *
 */
@RestController
public class StatisticController {

	@Autowired
	private StatisticService statisticService;

	@Operation(summary = "Get the parameters corresponding to the most used request, as well as the number of hits for this request")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The most frequent request has been", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StatisticDto.class)) }) })
	@GetMapping("/statistics")
	@ResponseStatus(HttpStatus.OK)
	public List<StatisticDto> getStatistic() {
		return this.statisticService.getStatistics();
	}

}
