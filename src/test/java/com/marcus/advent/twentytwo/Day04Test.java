package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day04Test {
    @Test
    void part1() throws IOException {
        assertEquals(487, new Day04().part1());
    }

    @Test
    void part2() throws IOException {
        assertEquals(849, new Day04().part2());
    }

}