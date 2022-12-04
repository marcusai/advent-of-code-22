package com.marcus.advent.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day03 {
    public int part1() {
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-03.txt"))) {
            return stream.mapToInt(this::getCommonCharacter)
                    .sum();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int part2() throws IOException {
        int count = 0;
        int result = 0;
        List<String> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-03.txt"))) {
            String line;
            while ((line = reader.readLine()) != null){
                items.add(line);
                count++;
                if (count == 3) {
                    result += getBadgeValue(items);
                    items = new ArrayList<>();
                    count = 0;
                }
            }

        }
        return result;
    }

    private int getBadgeValue(List<String> items) {
        return items.get(0).chars()
                .filter(c -> items.get(1).indexOf(c) >= 0)
                .filter(c ->items.get(2).indexOf(c) >=0)
                .map(this::convert).findFirst().getAsInt();
    }

    private int getCommonCharacter(String input) {
        String string1 = input.substring(0, input.length()/2);
        String string2 = input.substring(input.length()/2);
        for (Character c : string1.toCharArray()) {
            if (string2.contains(c.toString())) {
                return convert(c);
            }
        }
        return 0;
    }

    private int convert(int i) {
        return i >= 96 ? i - 96 : i - 64 + 26;
    }
}
