package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day08Test {
    @Test
    void part1() throws IOException {
        assertEquals(1533, new Day08().part1());
    }

    @Test
    void part2() throws IOException {
        assertEquals(345744, new Day08().part2());
    }

}