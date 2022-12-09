package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day09Test {
    @Test
    void part1() throws IOException {
        assertEquals(6337, new Day09().part1());
    }

    @Test
    void part2() throws IOException {
        assertEquals(2455, new Day09().part2());
    }

}