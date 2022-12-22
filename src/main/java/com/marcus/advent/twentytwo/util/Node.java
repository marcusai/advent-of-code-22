package com.marcus.advent.twentytwo.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Node<T> {
    private T value;
    @Setter private Integer distance = Integer.MAX_VALUE;
    @Setter private List<Node<T>> shortestPath = new LinkedList<>();
    private Map<Node<T>, Integer> adjacentNodes = new HashMap<>();

    public Node(T value) {
        this.value = value;
    }

    public void addDestination(Node<T> node, int distance) {
        if (this == node) throw new IllegalArgumentException("Can't connect node to itself");
        this.adjacentNodes.put(node, distance);
    }
}
