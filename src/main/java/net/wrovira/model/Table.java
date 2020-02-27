package net.wrovira.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;

public class Table {

    private static final Map<Integer, Table> TABLE_MAP_CACHE = new HashMap<>();
    private static final Random RANDOM = new Random(currentTimeMillis());

    private final int number;
    private final int numberOfSeats;

    private Table(final int number, final int numberOfSeats) {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
    }

    public static Table getTable(int number) {
        if (TABLE_MAP_CACHE.containsKey(number)) {
            return TABLE_MAP_CACHE.get(number);
        }
        return new Table(number, RANDOM.nextInt(10) + 1);
    }

    public int getNumber() {
        return number;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public String toString() {
        return format("{\n\tNumber: %d,\n\tSeats: %d\n}", number, numberOfSeats);
    }

}
