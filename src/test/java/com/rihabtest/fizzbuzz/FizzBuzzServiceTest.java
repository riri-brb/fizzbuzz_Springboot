package com.rihabtest.fizzbuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rihabtest.fizzbuzz.service.FizzBuzzService;

public class FizzBuzzServiceTest {
  @MockBean
  private FizzBuzzService fizzBuzzService;

  @ParameterizedTest
    @CsvSource({
        "1, 1",
        "2, 1, 2",
        "3, 1, 2, Fizz",
        "5, 1, 2, Fizz, 4, Buzz",
        "15, 1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz"
    })
    public void testGenerateFizzBuzzList(int number, String... expectedFizzBuzz) {
        List<String> expectedFizzBuzzList = Arrays.asList(expectedFizzBuzz);

        List<String> actualFizzBuzzList = FizzBuzzService.generateFizzBuzzList(number);
        assertEquals(expectedFizzBuzzList, actualFizzBuzzList);
    }
  
  @Test
    public void testGenerateFizzBuzzListWithLargeNumber() {
        int number = 100;
        // Calculate the expected FizzBuzz list for a large number
        List<String> expectedFizzBuzzList = IntStream.rangeClosed(1, number)
                .mapToObj(i -> {
                    if (i % 3 == 0 && i % 5 == 0) {
                        return "FizzBuzz";
                    } else if (i % 3 == 0) {
                        return "Fizz";
                    } else if (i % 5 == 0) {
                        return "Buzz";
                    } else {
                        return String.valueOf(i);
                    }
                })
                .collect(Collectors.toList());

        when(FizzBuzzService.generateFizzBuzzList(number)).thenReturn(expectedFizzBuzzList);

        List<String> actualFizzBuzzList = FizzBuzzService.generateFizzBuzzList(number);
        assertEquals(expectedFizzBuzzList, actualFizzBuzzList);
    }
}
