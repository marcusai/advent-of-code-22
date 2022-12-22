package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

class Day13Test {
    @Test
    void part1() throws IOException, ParseException {
        assertEquals(6623, new Day13().part1());
    }

    @Test
    void part2() throws IOException, ParseException {
        assertEquals(23049, new Day13().part2());
    }

}