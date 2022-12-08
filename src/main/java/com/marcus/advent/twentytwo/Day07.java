package com.marcus.advent.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {
    public long part1() throws IOException {
        final long maxSize = 100000L;
        Map<String, Long> dirSizes = getDirSizes();

        return dirSizes.values().stream().mapToLong(l->l)
                .filter(l -> l<=maxSize)
                .sum();
    }

    public long part2() throws IOException {
        final long totalSize = 70000000L;
        final long sizeRequired = 30000000L;
        Map<String, Long> dirSizes = getDirSizes();
        final long size = dirSizes.get("//");
        final long spaceToSave = sizeRequired - (totalSize - size);

        return dirSizes.values().stream().mapToLong(l->l)
                .filter(l -> l>=spaceToSave)
                .sorted()
                .findFirst()
                .getAsLong();
    }

    private Map<String, Long> getDirSizes() throws IOException {
        Map<String, Long> dirSizes = new HashMap<>();
        Deque<String> currentDirectories = new ArrayDeque<>();
        String currentDir = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-07.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("$ cd ..")) {
                    currentDir = currentDir.substring(0, currentDir.lastIndexOf("/"));
                    currentDirectories.removeLast();
                }
                else if (line.startsWith("$ cd ")) {
                    String directory = line.substring(5);
                    currentDir += "/" + directory;
                    currentDirectories.addLast(currentDir);
                }
                else if (line.startsWith("$ ls") || line.startsWith("dir")) {
                    continue;
                }
                else {
                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(line);
                    matcher.find();
                    addFileSize(dirSizes, currentDirectories, Long.parseLong(matcher.group(0)));
                }

            }
        }
        return dirSizes;
    }

    private void addFileSize(Map<String, Long> sizes, Deque<String> currentDirectories, long size) {
        for (String dir : currentDirectories) {
            Long currentSize = sizes.putIfAbsent(dir, size);
            if (currentSize != null) {
                sizes.put(dir, currentSize + size);
            }
        }
    }
}
