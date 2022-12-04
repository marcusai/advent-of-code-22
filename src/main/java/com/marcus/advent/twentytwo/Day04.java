package com.marcus.advent.twentytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day04 {
    public long part1() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-04.txt"))) {
            return getResult(stream, this::subSetFilter);
        }
    }

    public long part2() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-04.txt"))) {
            return getResult(stream, this::overLapFilter);
        }
    }
    private long getResult(Stream<String> stream, Predicate<int[]> filter) {
        return stream
                .map(s -> s.replace(",", "-").split("-"))
                .map(a -> Arrays.stream(a).mapToInt(Integer::parseInt))
                .map(IntStream::toArray)
                .filter(filter)
                .count();
    }

    private boolean overLapFilter(int[] a) {
        return (a[0] >= a[2] && a[0] <= a[3])
                || (a[1] >= a[2] && a[1] <= a[3])
                || (a[0] <= a[2] && a[1] >= a[3])
                || (a[0] >= a[2] && a[1] <= a[3]);
    }

    private boolean subSetFilter(int[] a) {
        return (a[0] <= a[2] && a[1] >= a[3]) || (a[0] >= a[2] && a[1] <= a[3]);
    }

}
