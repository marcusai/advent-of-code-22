package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day03Test {
    @Test
    void part1() {
        assertEquals(8185, new Day03().part1());
    }

    @Test
    void part2() throws IOException {
        assertEquals(2817, new Day03().part2());
    }


}