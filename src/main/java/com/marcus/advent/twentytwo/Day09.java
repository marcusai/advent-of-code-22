package com.marcus.advent.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Day09 {
    public int part1() throws IOException {
        Coord head = new Coord(0, 0);
        Coord tail = null;

        Set<Coord> visited = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-09.txt"))) {
            String line;
            String prevDirection = null;
            while ((line = reader.readLine()) != null) {
                String direction = line.substring(0,1);
                int amount = Integer.parseInt(line.substring(2));
                for (int i = 0; i < amount; i++) {
                    setHead(head, direction);
                    if (tail == null) {
                        tail = new Coord(0, 0);
                        visited.add(new Coord(tail.x, tail.y));
                        continue;
                    }

                    if (!isTouching(head, tail)) {
                        setTail(tail ,head);
                        visited.add(new Coord(tail.x, tail.y));
                    }

                }
            }
        }

        return visited.size();
    }

    public int part2() throws IOException {
        Coord[] knots = new Coord[] {new Coord(0,0), null, null, null, null, null, null, null, null, null};
        Set<Coord> visited = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-09.txt"))) {
            String line;
            String prevDirection = null;
            while ((line = reader.readLine()) != null) {
                String direction = line.substring(0,1);
                int amount = Integer.parseInt(line.substring(2));
                for (int i = 0; i < amount; i++) {
                    setHead(knots[0], direction);
                    for (int j = 1; j < knots.length; j++) {
                        if (knots[j] == null) {
                            if (!(knots[j-1].x == 0 && knots[j-1].y ==0)) {
                                knots[j] = new Coord(0, 0);
                            }
                            break;
                        }
                        if (!isTouching(knots[j], knots[j-1])) {
                            setTail(knots[j], knots[j-1]);
                        }
                    }
                    if (knots[9] != null) {
                        visited.add(new Coord(knots[9].x, knots[9].y));
                    }
                }
            }
        }
        return visited.size();
    }

    private void setTail(Coord tail, Coord head) {
        if (head.x != tail.x) {
            if (head.x > tail.x) {
                tail.x++;
            }
            else {
                tail.x--;
            }
        }

        if (head.y != tail.y) {
            if (head.y > tail.y) {
                tail.y++;
            }
            else {
                tail.y--;
            }
        }
    }

    private boolean isTouching(Coord head, Coord tail) {
        return Math.abs(head.x-tail.x) <= 1 && Math.abs(head.y-tail.y) <=1;
    }

    private void setHead(Coord head, String direction) {
        int x = head.x, y = head.y;
        switch (direction) {
        case "U":
            head.y++;
            break;
        case "D":
            head.y--;
            break;
        case "L":
            head.x++;
            break;
        case "R":
            head.x--;
            break;
        }
    }

}

@Data
@AllArgsConstructor
class Coord {
    public int x;
    public int y;
}
