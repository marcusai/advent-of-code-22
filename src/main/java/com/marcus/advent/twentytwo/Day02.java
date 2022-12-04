package com.marcus.advent.twentytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class Day02 {

    public int part1() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-02.txt"))) {
            return getResult(stream, scores1);
        }
    }

    public int part2() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-02.txt"))) {
            return getResult(stream, scores2);
        }
    }

    private int getResult(Stream<String> stream, Map<String, Integer> scores) {
        return stream.mapToInt(scores::get)
                .sum();
    }

    private final Map<String, Integer> scores1 = Map.of(
            "A X", 4,
            "A Y", 8,
            "A Z", 3,
            "B X", 1,
            "B Y", 5,
            "B Z", 9,
            "C X", 7,
            "C Y", 2,
            "C Z", 6

    );

    private final Map<String, Integer> scores2 = Map.of(
            "A X", 3,
            "A Y", 4,
            "A Z", 8,
            "B X", 1,
            "B Y", 5,
            "B Z", 9,
            "C X", 2,
            "C Y", 6,
            "C Z", 7
    );

}

