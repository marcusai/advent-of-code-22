package com.marcus.advent.twentytwo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Day02Test {
    @Test
    void test() throws IOException {
        assertEquals(13509, new Day02().part1());
    }

}