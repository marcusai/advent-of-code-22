package com.marcus.advent.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 {
    public String part1() throws IOException {
        Deque<String>[] cargo = new Deque[9];
        for (int i = 0; i < 9; i++){
            cargo[i] = new ArrayDeque<>();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-05.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[")) {
                    load(line, cargo);
                }
                else if (line.startsWith("move")) {
                    move2(line, cargo);
                }
            }
        }
        return getAnswer(cargo);
    }

    private String getAnswer(Deque<String>[] cargo) {
        StringBuilder builder = new StringBuilder();
        for (Deque<String> strings : cargo) {
            builder.append(strings.peekFirst());
        }
        return builder.toString();
    }

    private void move(String line, Deque<String>[] cargo) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);
        ArrayList<Integer> numbers = new ArrayList<>();
        while(matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        for (int i = 0; i < numbers.get(0); i++) {
            String s = cargo[numbers.get(1) - 1].removeFirst();
            cargo[numbers.get(2) - 1].addFirst(s);
        }
    }

    private void move2(String line, Deque<String>[] cargo) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);
        ArrayList<Integer> numbers = new ArrayList<>();
        while(matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < numbers.get(0); i++) {
            String s = cargo[numbers.get(1) - 1].removeFirst();
            stack.addFirst(s);
        }
        for (String s : stack) {
            cargo[numbers.get(2) - 1].addFirst(s);
        }
    }

    private void load(String line, Deque<String>[] cargo) {
        for (int i = 0; i < cargo.length; i++) {
            int index = 1 + i * 4;
            String character = line.substring(index, index + 1);
            if (!character.equals(" ")) {
                cargo[i].addLast(character);
            }
        }
    }

}
