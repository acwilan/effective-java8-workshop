package net.wrovira;

import net.wrovira.model.MenuItem;
import net.wrovira.model.Order;
import net.wrovira.model.OrderItem;
import net.wrovira.model.Table;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.currentTimeMillis;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestaurantTest {

    private static final Random RANDOM = new Random(currentTimeMillis());
    private static final String[] ITEM_NAMES = {
            "SOUP",
            "MEAT",
            "DRINK",
            "DESSERT",
            "SALAD"
    };
    private static final int TABLE_NUMBER = 1;

    @Test
    public void whenOrderCreated_ThenCalculatedPriceShouldBeCorrect() {
        final List<OrderItem> orderItems = new ArrayList<>(ITEM_NAMES.length);
        double expectedTotalPrice = 0.0;
        for (int i = 0; i < ITEM_NAMES.length; i++) {
            final int quantity = RANDOM.nextInt(2) + 1;
            final MenuItem menuItem = MenuItem.getMenuItem(ITEM_NAMES[i]);
            orderItems.add(new OrderItem(menuItem, quantity));
            expectedTotalPrice += quantity * menuItem.getPrice();
        }
        final Table table = Table.getTable(TABLE_NUMBER);
        final Order order = new Order("customerName", orderItems, table);

        System.out.println(order);
        assertThat(order.getTotalPrice(), is(expectedTotalPrice));
    }
}
