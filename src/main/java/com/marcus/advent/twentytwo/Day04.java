package com.marcus.advent.twentytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day04 {
    public long part1() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-04.txt"))) {
            return stream
                    .map(s -> s.split(","))
                    .map(a -> String.join("-", a).split("-"))
                    .map(a -> Arrays.stream(a).mapToInt(Integer::parseInt))
                    .map(IntStream::toArray)
                    .filter(a -> (a[0] <= a[2] && a[1] >= a[3]) ||  (a[0] >= a[2] && a[1] <= a[3]))
                    .count();
        }
        
    }

    public long part2() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-04.txt"))) {
            return stream
                    .map(s -> s.split(","))
                    .map(a -> String.join("-", a).split("-"))
                    .map(a -> Arrays.stream(a).mapToInt(Integer::parseInt))
                    .map(IntStream::toArray)
                    .filter(a -> (a[0] >= a[2] && a[0] <= a[3]) ||  (a[1] >= a[2] && a[1] <= a[3]) || (a[0] <= a[2] && a[1] >= a[3]) ||  (a[0] >= a[2] && a[1] <= a[3]))
                    .count();
        }

    }
}
