package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day06Test {
    @Test
    void part1() throws IOException {
        assertEquals(1300, new Day06().part1());
    }

    @Test
    void part2() throws IOException {
        assertEquals(3986, new Day06().part2());
    }
}