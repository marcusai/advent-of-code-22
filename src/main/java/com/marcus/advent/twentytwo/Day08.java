package com.marcus.advent.twentytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day08 {
    public int part1() throws IOException {
        int count = 0;
        Integer[][] trees = loadTrees();
        for (int x = 0; x < trees.length; x++) {
            for (int y = 0; y < trees[x].length; y++) {
                if (isVisible(trees, x, y)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int part2() throws IOException {
        int visibility = 0;
        Integer[][] trees = loadTrees();
        for (int x = 0; x < trees.length; x++) {
            for (int y = 0; y < trees[x].length; y++) {
                visibility = Integer.max(visibility, visibility(trees, x, y));
            }
        }
        return visibility;
    }

    private int visibility(Integer[][] trees, int x, int y) {
        int up =0, down=0, left=0, right =0;

        for (int i = x+1; i < trees.length; i++) {
            right++ ;
            if (trees[x][y] <= trees[i][y]) {
                break;
            }
        }
        for (int i = x-1; i >= 0; i--) {
            left++;
            if (trees[x][y] <= trees[i][y]) {
                break;
            }
        }

        for (int i = y-1; i >= 0; i--) {
            up++;
            if (trees[x][y] <= trees[x][i]) {
                break;
            }
        }

        for (int i = y+1; i < trees[x].length; i++) {
            down++;
            if (trees[x][y] <= trees[x][i]) {
                break;
            }
        }
        return left * right * up * down;
    }

    private boolean isVisible(Integer[][] trees, int x, int y) {
        if (x == 0 || x == trees.length-1 || y == 0 || y == trees[x].length - 1) {
            return true;
        }
        boolean isVisible = true;
        for (int i = x+1; i < trees.length; i++) {
            if (trees[x][y] <= trees[i][y]) {
                isVisible = false;
                break;
            }
        }
        if (isVisible) return true;

        isVisible = true;
        for (int i = x-1; i >= 0; i--) {
            if (trees[x][y] <= trees[i][y]) {
                isVisible = false;
                break;
            }
        }
        if (isVisible) return true;

        isVisible = true;
        for (int i = y-1; i >= 0; i--) {
            if (trees[x][y] <= trees[x][i]) {
                isVisible = false;
                break;
            }
        }
        if (isVisible) return true;

        isVisible = true;
        for (int i = y+1; i < trees[x].length; i++) {
            if (trees[x][y] <= trees[x][i]) {
                isVisible = false;
                break;
            }
        }
        if (isVisible) return true;

        return false;
    }

    private Integer[][] loadTrees() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-08.txt"))) {
            return stream.map(
                            line -> line.chars()
                                    .mapToObj(c -> (char) c)
                                    .mapToInt(c -> Integer.parseInt(String.valueOf(c)))
                                    .boxed()
                                    .toArray(Integer[]::new)
                    )
                    .toArray(Integer[][]::new);

        }
    }
}
