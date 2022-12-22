package com.marcus.advent.twentytwo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @AllArgsConstructor
public class Coord {
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
        value = 0;
    }
    public int x;
    public int y;
    public int value ;
}
