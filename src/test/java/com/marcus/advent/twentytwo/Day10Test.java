package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day10Test {
    @Test
    void part1() throws IOException {
        assertEquals(13220, new Day10().part1());
    }

    @Test
    void part2() throws IOException {
        new Day10().part2();
    }

}