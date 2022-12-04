package com.marcus.advent.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day01 {
    public int task2(int limit) throws IOException {
        List<Integer> calories = new ArrayList<>();
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-01-1-input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null){
                if (line.isEmpty()) {
                    calories.add(total);
                    total = 0;
                }
                else {
                    total += Integer.parseInt(line);
                }
            }
        }
        calories.add(total);
        return calories.stream().sorted(Comparator.reverseOrder()).limit(limit).mapToInt(Integer::intValue).sum();

    }
}
