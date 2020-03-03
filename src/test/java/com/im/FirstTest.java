package com.im;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class FirstTest {


    @Test
    void shouldReturnMainStringWhenMainStringIsEmpty() {
        // given
        String mainString = " ";
        String subString = "abc";
        String replacementString = "xyz";

        // when
        First first = new First();
        String returnedString = first.replaceSubString(mainString,subString,replacementString);

        // then
        assertEquals(mainString,returnedString);

    }

    @Test
    void shouldReturnMainStringWhenSubStringIsNull() {
        // given
        String mainString = "abcdef";
        String subString = null;
        String replacementString = "xyz";

        // when
        First first = new First();
        String returnedString = first.replaceSubString(mainString,subString,replacementString);

        // then
        assertEquals(mainString,returnedString);

    }

    @Test
    void shouldReturnMainStringWhenReplacementStringIsNull()  {
        // given
        String mainString = "abcdef";
        String subString = "def";
        String replacementString = null;

        // when
        First first = new First();
        String returnedString = first.replaceSubString(mainString,subString,replacementString);

        // then
        assertEquals(mainString,returnedString);
    }

    @Test
    void shouldReturnNewStringIfMainStringContainsSubString() {
        // given
        String mainString = "Hello World";
        String subString = "World";
        String replacementString = "Newer";
        String expectedString = "Hello Newer";

        // when
        First first = new First();
        String returnedString = first.replaceSubString(mainString,subString,replacementString);

        // then
        assertEquals(expectedString,returnedString);
    }

    @Test
    void shouldReturnOddElementList() {
        // given
        int[] array = new int[]{1,3,5};

        // when
        First first = new First();
        List<Integer> returnedList = first.filterEvenElements(IntStream.of(1,2,3,4,5).boxed().collect(Collectors.toList()));
        Object[] array1 = returnedList.toArray();


//         then
        Assert.assertThat(array,is(array1));
    }

    @Test
    void shouldThrowRuntimeExceptionWhenValueAreNull() {
        // given
        List<BigDecimal> bigDecimals = Arrays.asList();

        // when
        First first = new First();
        Throwable throwable = assertThrows(RuntimeException.class,() -> first.calculateAverage(bigDecimals));

        // then
        System.out.println(throwable.getMessage());
    }

    @Test
    void shouldReturnAverageOfBigDecimalList() throws ArithmeticException {
        // given
        List<BigDecimal> bigDecimals = new ArrayList<>();
        bigDecimals.add(BigDecimal.valueOf(50000));
        bigDecimals.add(BigDecimal.valueOf(150000));
        bigDecimals.add(BigDecimal.valueOf(20000));
        BigDecimal expectedValue = new BigDecimal("73333.33333333333333333333333333333");


        // when
        First first = new First();
        BigDecimal returnedNumber = first.calculateAverage(bigDecimals);
        System.out.println(returnedNumber);

        // then
        assertEquals(expectedValue,returnedNumber);
    }

    @Test
    void shouldReturnFalseIfStringIsNotPalindrome() {
        // given
        String str = "abcd";

        // when
        First first = new First();
        boolean returnedValue = first.isPallindrome(str);

        // then
        assertFalse(returnedValue);

    }

    @Test
    void shouldReturnTrueIfStringIsPalindrome() {
        // given
        String str = "aaabbbaaa";

        // when
        First first = new First();
        boolean returnedValue = first.isPallindrome(str);

        // then
        assertTrue(returnedValue);

    }
}