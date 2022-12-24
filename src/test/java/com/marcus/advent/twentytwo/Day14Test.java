package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day14Test {
    @Test
    void part1() throws IOException {
        assertEquals(655, new Day14().part1());
    }

    @Test
    void part2() throws IOException {
        assertEquals(26484, new Day14().part2());
    }


}