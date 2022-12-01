package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day01Test {
    @Test
    void test1() throws IOException {
        Day01 day01 = new Day01();
        assertEquals(67027, day01.task1());
    }

    @Test
    void test2() throws IOException {
        Day01 day01 = new Day01();
        assertEquals(67027, day01.task2());
    }
}