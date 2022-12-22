package com.marcus.advent.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Day13 {
    public int part1() throws IOException, ParseException {
        int sum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day-13.txt"))) {
            int index = 0;
            String line;
            JSONArray left = null;
            JSONArray right = null;
            JSONParser jsonParser = new JSONParser();
            while ((line = reader.readLine()) != null) {
                if (left == null) {
                    left = (JSONArray) jsonParser.parse(line);
                }
                else if (right == null) {
                    index++;
                    right = (JSONArray) jsonParser.parse(line);
                    Integer answer = compareArrays(left, right).orElse(0);
                    if (answer > 0) {
                        System.out.println("incrementing answer by " + index);
                        sum+= index;
                    }
                }
                else {
                    left = null;
                    right = null;
                }
            }
        }
        return sum;
    }

    public int part2() throws IOException {
        JSONParser jsonParser = new JSONParser();
        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/day-13.txt"))) {
            Stream<String> newStream = Stream.concat(stream, Stream.of("[[2]]","[[6]]"));
            List<String> collect = newStream.filter(l -> !l.isEmpty()).map(line -> {
                try {
                    return (JSONArray) jsonParser.parse(line);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            })
                    .sorted(this::compare)
                    .map(b->b.toJSONString())
                    .collect(Collectors.toList())
                    ;
            int index1 = collect.indexOf("[[2]]");
            int index2 = collect.indexOf("[[6]]");
            return (index1+1) * (index2+1);
        }
    }

    protected int compare(JSONArray left, JSONArray right) {
        Optional<Integer> value = compareArrays(left, right);
        return value.orElse(0) > 0 ? -1 : 1;
    }

    private static Optional<Integer> compareArrays(JSONArray left, JSONArray right) {
        System.out.println(left);
        System.out.println(right);
        Optional<Integer> answer;
        int minSize = Math.min(left.size(), right.size());
        for (int i = 0; i < minSize; i++) {
            if (left.get(i) instanceof Long) {
                if (right.get(i) instanceof Long) {
                    answer = compareLongs((Long)left.get(i), (Long)right.get(i), i);
                }
                else {
                    answer = compareArrays(toArray((Long)left.get(i)), (JSONArray) right.get(i));
                }
            }
            else {
                if (right.get(i) instanceof Long) {
                    answer = compareArrays((JSONArray) left.get(i), toArray((Long)right.get(i)));
                }
                else {
                    answer = compareArrays((JSONArray) left.get(i), (JSONArray) right.get(i));
                }
            }
            if (answer.isPresent()) {
                if (answer.get() > 0) {
                    answer =  Optional.of(i+1);
                }
                System.out.println(answer);
                return answer;
            }


        }

        if (left.size() < right.size()) {
            answer = Optional.of(1);
        }
        else if (left.size() > right.size()) {
            answer = Optional.of(0);
        }
        else {
            answer = Optional.empty();
        }
        System.out.println(answer);
        return answer;
    }

    private static JSONArray toArray(Long value) {
        JSONArray array = new JSONArray();
        array.add(value);
        return array;
    }

    private static Optional<Integer> compareLongs(Long left, Long right, int index) {
        if (left < right) {
            return Optional.of(index + 1);
        }
        if (left > right) {
            return Optional.of(0);
        }
        return Optional.empty();
    }
}
