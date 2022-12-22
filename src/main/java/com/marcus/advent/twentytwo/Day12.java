package com.marcus.advent.twentytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.marcus.advent.twentytwo.util.Coord;
import com.marcus.advent.twentytwo.util.Graph;
import com.marcus.advent.twentytwo.util.Node;

public class Day12 {
    public int part1() throws IOException {
        Graph<Coord> graph = new Graph<>();
        Node<Coord>[][] nodes = null;
        nodes = getNodes(graph);
        Node<Coord> start = nodes[20][0], end = nodes[20][135];


        Graph<Coord> result = graph.calculateShortestPathFromSource(graph, start);

        return end.getDistance() + 2;
    }

    public int part2() throws IOException {
        Graph<Coord> graph = new Graph<>();
        Node<Coord>[][] nodes = null;
        nodes = getNodes(graph);
        Node<Coord> end = nodes[20][135];
        int distance = Integer.MAX_VALUE;

        for (int i =0; i < nodes.length; i++) {
            for (int j =0; j < nodes.length; j++) {
                Node<Coord> node = nodes[i][j];
                if (node.getValue().value == (int)'a') {
                    Graph result = graph.calculateShortestPathFromSource(graph, node);
                    distance = Math.min(distance, end.getDistance());
                }

            }
        }


        return distance+2;
    }


    private static Node<Coord>[][] getNodes(Graph<Coord> graph) throws IOException {
        Node<Coord>[][] nodes;
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-12.txt"))) {
            int[][] grid = stream.map(String::chars).map(IntStream::toArray).toArray(int[][]::new);
            Map<Coord, Integer> map = new HashMap<>();
            nodes = new Node[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    nodes[i][j] = new Node(new Coord(i, j, grid[i][j]));
                }
            }

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    Node<Coord> node = nodes[i][j];
                    if (grid[i][j] == 83) {
                        grid[i][j] = (int)'a';
                    }
                    if (grid[i][j] == 69) {
                        grid[i][j] = (int)'z';
                    }
                    if (i > 0) {
                        if (grid[i - 1][j] <= grid[i][j] +1) {
                            node.addDestination(nodes[i - 1][j], 1);
                        }
                    }
                    if (i< grid.length-1) {
                        if (grid[i + 1][j] <= grid[i][j] + 1) {
                            node.addDestination(nodes[i + 1][j], 1);
                        }
                    }
                    if (j > 0) {
                        if (grid[i][j-1] <= grid[i][j] + 1) {
                            node.addDestination(nodes[i][j-1], 1);
                        }
                    }
                    if (j< grid[i].length-1) {
                        if (grid[i][j+1] <= grid[i][j] + 1) {
                            node.addDestination(nodes[i][j+1], 1);
                        }
                    }
                    graph.addNode(node);
                }
            }
        }
        return nodes;
    }

}
