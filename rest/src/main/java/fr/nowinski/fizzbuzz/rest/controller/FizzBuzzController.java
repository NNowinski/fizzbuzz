package fr.nowinski.fizzbuzz.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.nowinski.fizzbuzz.commons.dto.PageDto;
import fr.nowinski.fizzbuzz.commons.services.FizzBuzzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * 
 * Controller for generate list
 *
 */
@RestController
public class FizzBuzzController {

	@Autowired
	private FizzBuzzService fizzService;

	@Operation(summary = "Get a list of strings with numbers from 1 to limit, where: all multiples of int1 are replaced by str1, all multiples of int2 are replaced by str2, all multiples of int1 and int2 are replaced by str1str2")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List of numbers and strings", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = List.class), examples = {
					@ExampleObject(value = "1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz,16,...") }) }),
			@ApiResponse(responseCode = "400", description = "Limit should be a positive number", content = @Content) })
	@GetMapping("/fizzbuzz")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getFizzBuzz(@RequestParam(defaultValue = "3", required = true) Integer int1,
			@RequestParam(defaultValue = "5", required = true) Integer int2,
			@RequestParam(defaultValue = "fizz", required = true) String str1,
			@RequestParam(defaultValue = "buzz", required = true) String str2,
			@RequestParam(defaultValue = "100", required = true) Integer limit) {

		if (limit < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Limit should be a positive number");
		}
		return fizzService.getFizzBuzzList(new PageDto(int1, int2, str1, str2, limit));

	}

}
