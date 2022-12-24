package com.marcus.advent.twentytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.marcus.advent.twentytwo.util.Coord;

public class Day14 {
    public int part1() throws IOException {
        int borderLeft = Integer.MAX_VALUE;
        int borderRight = Integer.MAX_VALUE;
        int borderBottom = Integer.MIN_VALUE;

        List<List<Coord>> collect = null;
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-14.txt"))) {
            collect = stream.map(s -> s.split(" -> "))
                    .map(a -> Arrays.stream(a).map(s -> s.split(",")).map(u -> new Coord(Integer.parseInt(u[0]), Integer.parseInt(u[1])))
                            .collect(Collectors.toList())).collect(Collectors.toList());

        }

        Set<Coord> populated = new HashSet<>();

        for (List<Coord> coords : collect) {
            Coord prev = null;
            for (Coord coord : coords) {
                if (prev == null) {
                    prev = coord;
                    continue;
                }
                if (coord.x == prev.x) {
                    for (int y = Math.min(coord.y, prev.y);  y<= Math.max(coord.y, prev.y); y++) {
                        populated.add(new Coord(coord.x, y));
                        borderLeft = Math.min(coord.x, borderLeft);
                        borderRight = Math.max(coord.x, borderRight);
                        borderBottom = Math.max(y, borderBottom);
                    }
                }
                else {
                    for (int x = Math.min(coord.x, prev.x);  x<= Math.max(coord.x, prev.x); x++) {
                        populated.add(new Coord(x, coord.y));
                        borderLeft = Math.min(x, borderLeft);
                        borderRight = Math.max(x, borderRight);
                        borderBottom = Math.max( coord.y, borderBottom);
                    }
                }
                prev = coord;
            }
        }


        Coord position = new Coord(500, 0);
        int count = 0;
        while (true) {
            Coord test = new Coord(position.x, position.y + 1);
            if (populated.contains(test)) {
                test = new Coord(position.x - 1, position.y + 1);
                if (populated.contains(test)) {
                    test = new Coord(position.x + 1, position.y + 1);
                    if (populated.contains(test)) {
                        populated.add(position);
                        position = new Coord(500, 0);
                        count++;
                        continue;
                    }
                    else {
                        if (test.x < borderLeft || test.x > borderRight || test.y > borderBottom) {
                            break;
                        }
                        position = test;
                        continue;
                    }
                }
                else {
                    if (test.x < borderLeft || test.x > borderRight || test.y > borderBottom) {
                        break;
                    }
                    position = test;
                    continue;
                }
            }
            else {
                if (test.x < borderLeft || test.x > borderRight || test.y > borderBottom) {
                    break;
                }
                position = test;
                continue;
            }
        }


        return count;
    }
    public int part2() throws IOException {
        int borderLeft = Integer.MAX_VALUE;
        int borderRight = Integer.MAX_VALUE;
        int borderBottom = Integer.MIN_VALUE;

        List<List<Coord>> collect = null;
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-14.txt"))) {
            collect = stream.map(s -> s.split(" -> "))
                    .map(a -> Arrays.stream(a).map(s -> s.split(",")).map(u -> new Coord(Integer.parseInt(u[0]), Integer.parseInt(u[1])))
                            .collect(Collectors.toList())).collect(Collectors.toList());

        }

        Set<Coord> populated = new HashSet<>();

        for (List<Coord> coords : collect) {
            Coord prev = null;
            for (Coord coord : coords) {
                if (prev == null) {
                    prev = coord;
                    continue;
                }
                if (coord.x == prev.x) {
                    for (int y = Math.min(coord.y, prev.y);  y<= Math.max(coord.y, prev.y); y++) {
                        populated.add(new Coord(coord.x, y));
                        borderLeft = Math.min(coord.x, borderLeft);
                        borderRight = Math.max(coord.x, borderRight);
                        borderBottom = Math.max(y, borderBottom);
                    }
                }
                else {
                    for (int x = Math.min(coord.x, prev.x);  x<= Math.max(coord.x, prev.x); x++) {
                        populated.add(new Coord(x, coord.y));
                        borderLeft = Math.min(x, borderLeft);
                        borderRight = Math.max(x, borderRight);
                        borderBottom = Math.max( coord.y, borderBottom);
                    }
                }
                prev = coord;
            }
        }

        for (int i = -10000; i < 10000; i++) {
            populated.add(new Coord(i, borderBottom+2));
        }


        Coord position = new Coord(500, 0);
        int count = 0;
        while (true) {
            Coord test = new Coord(position.x, position.y + 1);
            if (populated.contains(test)) {
                test = new Coord(position.x - 1, position.y + 1);
                if (populated.contains(test)) {
                    test = new Coord(position.x + 1, position.y + 1);
                    if (populated.contains(test)) {
                        if (position.equals(new Coord(500, 0))) {
                            count++;
                            break;
                        }
                        populated.add(position);
                        position = new Coord(500, 0);
                        count++;
                        continue;
                    }
                    else {
//                        if (test.x < borderLeft || test.x > borderRight || test.y > borderBottom) {
//                            break;
//                        }
                        position = test;
                        continue;
                    }
                }
                else {
//                    if (test.x < borderLeft || test.x > borderRight || test.y > borderBottom) {
//                        break;
//                    }
                    position = test;
                    continue;
                }
            }
            else {
//                if (test.x < borderLeft || test.x > borderRight || test.y > borderBottom) {
//                    break;
//                }
                position = test;
                continue;
            }
        }


        return count;
    }


}
