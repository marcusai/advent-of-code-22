package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day12Test {
    @Test
    void part1() throws IOException {
        assertEquals(447, new Day12().part1());
    }

    @Test
    void part2() throws IOException {
        assertEquals(446, new Day12().part2());
    }

}