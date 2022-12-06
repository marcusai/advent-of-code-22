package com.marcus.advent.twentytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day06 {
    public int part1() throws IOException {
        return getResult(4);
    }
    public int part2() throws IOException {
        return getResult(14);
    }
    private int getResult(int size) throws IOException {
        Path path = Paths.get("./src/main/resources/day-06.txt");
        byte[] bytes = Files.readAllBytes(path);
        for (int i = size; i < bytes.length; i++) {
            if(!hasDuplicates(Arrays.copyOfRange(bytes, i - size, i))) {
                return i;
            }
        }
        return 0;
    }

    private boolean hasDuplicates(byte[] bytes) {
        Set<Byte> set = new HashSet<>();
        for(Byte b : bytes) {
            if (set.contains(b)) {
                return true;
            }
            else {
                set.add(b);
            }
        }
        return false;
    }
}
