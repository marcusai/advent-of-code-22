package com.marcus.advent.twentytwo;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.UnaryOperator;

import lombok.RequiredArgsConstructor;

public class Day11 {
    public long part1() {
        List<Monkey> monkeys = loadMonkeys();
        final int ROUNDS = 20;
        for (int i = 0; i < ROUNDS; i++) {
            for (Monkey monkey : monkeys) {
                while (monkey.items.size() > 0) {
                    monkey.activity = monkey.activity + 1;
                    BigInteger item = monkey.items.removeFirst();
                    BigInteger worryLevel = monkey.operation.apply(item).divide(BigInteger.valueOf(3L));
                    Integer nextMonkey = worryLevel.mod(BigInteger.valueOf(monkey.test)).longValue() == 0L ? monkey.ifTrue : monkey.ifFalse;
                    monkeys.get(nextMonkey).items.addLast(worryLevel);
                }
            }
        }
        return monkeys.stream().sorted().skip(monkeys.size()- 2).map(m-> m.activity).mapToLong(m -> m).reduce((first, second) -> first * second).getAsLong();
    }

    public long part2() {
        List<Monkey> monkeys = loadMonkeys();
        long superMod = 1;
        for (Monkey monkey : monkeys) {
            superMod *= monkey.test;
        }
        final int ROUNDS = 10000;
        for (int i = 0; i < ROUNDS; i++) {
            System.out.println("Round: " + i);
            for (Monkey monkey : monkeys) {
                while (monkey.items.size() > 0) {
                    monkey.activity = monkey.activity + 1;
                    BigInteger item = monkey.items.removeFirst();
                    BigInteger worryLevel = monkey.operation.apply(item).mod(BigInteger.valueOf(superMod));
                    Integer nextMonkey = worryLevel.mod(BigInteger.valueOf(monkey.test)).longValue() == 0L ? monkey.ifTrue : monkey.ifFalse;
                    monkeys.get(nextMonkey).items.addLast(worryLevel);
                }
            }
        }
        return monkeys.stream().sorted().skip(monkeys.size()- 2).map(m-> m.activity).mapToLong(m -> m).reduce((first, second) -> first * second).getAsLong();
    }
    private List<Monkey> loadMonkeys() {
        List<Monkey> monkeys = new ArrayList<>();

        monkeys.add(new Monkey(new ArrayDeque(List.of(BigInteger.valueOf(93L),BigInteger.valueOf(98L))), (i -> i.multiply(BigInteger.valueOf(17L))), 19L, 5, 3));
        monkeys.add(new Monkey(new ArrayDeque(List.of(BigInteger.valueOf(95L), BigInteger.valueOf(72L), BigInteger.valueOf(98L), BigInteger.valueOf(82L), BigInteger.valueOf(86L))), (i -> i.add(BigInteger.valueOf(5L))), 13L, 7, 6));
        monkeys.add(new Monkey(new ArrayDeque(List.of(BigInteger.valueOf(85L), BigInteger.valueOf(62L), BigInteger.valueOf(82L), BigInteger.valueOf(86L), BigInteger.valueOf(70L), BigInteger.valueOf(65L), BigInteger.valueOf(83L), BigInteger.valueOf(76L))), (i -> i.add(BigInteger.valueOf(8L))), 5L, 3, 0));
        monkeys.add(new Monkey(new ArrayDeque(List.of(BigInteger.valueOf(86L), BigInteger.valueOf(70L), BigInteger.valueOf(71L), BigInteger.valueOf(56L))), (i -> i.add( BigInteger.valueOf(1L))), 7L, 4, 5));
        monkeys.add(new Monkey(new ArrayDeque(List.of(BigInteger.valueOf(77L), BigInteger.valueOf(71L), BigInteger.valueOf(86L), BigInteger.valueOf(52L),BigInteger.valueOf(81L), BigInteger.valueOf(67L))), (i -> i.add( BigInteger.valueOf(4L))), 17L, 1, 6));
        monkeys.add(new Monkey(new ArrayDeque(List.of(BigInteger.valueOf(89L), BigInteger.valueOf(87L), BigInteger.valueOf(60L), BigInteger.valueOf(78L), BigInteger.valueOf(54L), BigInteger.valueOf(77L), BigInteger.valueOf(98L))), (i -> i.multiply( BigInteger.valueOf(7L))), 2L, 1, 4));
        monkeys.add(new Monkey(new ArrayDeque(List.of(BigInteger.valueOf(69L), BigInteger.valueOf(65L), BigInteger.valueOf(63L))), (i -> i.add(BigInteger.valueOf(6L))), 3L, 7, 2));
        monkeys.add(new Monkey(new ArrayDeque(List.of(BigInteger.valueOf(89L))), (i -> i.multiply(i)), 11L, 0, 2));

        return monkeys;
    }
}

@RequiredArgsConstructor
class Monkey implements  Comparable<Monkey>{
    final public Deque<BigInteger> items;
    final public UnaryOperator<BigInteger> operation;
    final public long test;
    final public int ifTrue;
    final public int ifFalse;
    public long activity = 0L;

    @Override public int compareTo(Monkey monkey) {
        if (activity > monkey.activity) return 1;
        if (activity == monkey.activity) return 0;
        return -1;
    }
}