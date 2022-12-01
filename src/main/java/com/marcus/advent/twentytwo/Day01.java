package com.marcus.advent.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day01 {
    public int task1() throws IOException {
        int max = 0;
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-01-1-input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null){
                if (line.equals("")) {
                    max = Math.max(max, total);
                    total = 0;
                }
                else {
                    total += Integer.parseInt(line);
                }
            }
        }
        return Math.max(max, total);

    }

    public int task2() throws IOException {
        List<Integer> calories = new ArrayList<>();
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-01-1-input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null){
                if (line.equals("")) {
                    calories.add(total);
                    total = 0;
                }
                else {
                    total += Integer.parseInt(line);
                }
            }
        }
        calories.add(total);
        return calories.stream().sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).sum();

    }
}
