package com.marcus.advent.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Day10 {
    public int part1() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-10.txt"))) {
            String line;
            int registerX = 1;
            int cycle = 0;
            int strength = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("noop")) {
                    cycle ++;
                    strength += checkCycle(cycle, registerX);
                    if (cycle >= 220) {
                        break;
                    }
                }
                else {
                    int increment = Integer.parseInt(line.substring(5));
                    cycle++;
                    strength += checkCycle(cycle, registerX);
                    if (cycle >= 220) {
                        break;
                    }
                    cycle++;
                    strength += checkCycle(cycle, registerX);
                    registerX += increment;
                    if (cycle >= 220) {
                        break;
                    }
                }
            }
            return strength;
        }
    }

    public void part2() throws IOException {
        char[] pixels = new char[240];
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-10.txt"))) {
            String line;
            int registerX = 1;
            int cycle = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("noop")) {
                    addPixel(cycle, registerX, pixels);
                    cycle ++;
                    if (cycle >= 240) {
                        break;
                    }
                }
                else {
                    int increment = Integer.parseInt(line.substring(5));
                    addPixel(cycle, registerX, pixels);
                    cycle++;
                    if (cycle >= 240) {
                        break;
                    }
                    addPixel(cycle, registerX, pixels);
                    cycle++;
                    registerX += increment;
                    if (cycle >= 240) {
                        break;
                    }
                }
            }
            printPixels(pixels);
        }
    }

    private void printPixels(char[] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            System.out.print(pixels[i]);
            if ((i + 1) % 40 == 0) {
                System.out.print("\n");
            }
        }
    }

    private void addPixel(int cycle, int registerX, char[] pixels) {
        int position = cycle % 40;
        if (position >= registerX -1 && position<= registerX +1) {
              pixels[cycle] = '#';
        }
        else {
            pixels[cycle]= '.';
        }
    }

    private int checkCycle(int cycle, int registerX) {
        List<Integer> cycles = List.of(20, 60, 100, 140, 180, 220);
        if (cycles.contains(cycle)) {
            return cycle * registerX;
        }
        return 0;
    }
}
