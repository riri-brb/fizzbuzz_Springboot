package com.rihabtest.fizzbuzz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rihabtest.fizzbuzz.service.FizzBuzzService;

import java.util.List;

@RestController
@RequestMapping("/fizzbuzz")
public class FizzBuzzController {

    public FizzBuzzController(FizzBuzzService fizzBuzzService) {
    }

    @GetMapping("/{number}")
    public ResponseEntity<Object> getFizzBuzzList(@PathVariable int number) {
        if (number <= 0) {
            String errorMessage = "Number must be greater than zero.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        List<String> result = FizzBuzzService.generateFizzBuzzList(number);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
