package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day07Test {
    @Test
    void part1() throws IOException {
        assertEquals(1644735, new Day07().part1());
    }

    @Test
    void part2() throws IOException {
        assertEquals(1300850, new Day07().part2());
    }

}