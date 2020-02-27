package net.wrovira.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;

public class MenuItem {

    private static final Map<String, MenuItem> MENU_ITEM_MAP_CACHE = new HashMap<>();
    private static final Random RANDOM = new Random(currentTimeMillis());

    private final String name;
    private final double price;

    private MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static MenuItem getMenuItem(final String name) {
        assert name != null;
        if (MENU_ITEM_MAP_CACHE.containsKey(name)) {
            return MENU_ITEM_MAP_CACHE.get(name);
        }
        return new MenuItem(name, 1.0 + 99.0 * RANDOM.nextDouble());
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return format("{\n\tName: %s,\n\tPrice: %.2f\n}", name, price);
    }

}
