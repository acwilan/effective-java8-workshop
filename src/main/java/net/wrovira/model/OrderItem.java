package net.wrovira.model;

import static java.lang.String.format;

public class OrderItem {

    private MenuItem item;
    private int quantity;

    public OrderItem(final MenuItem item, final int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(final MenuItem item) {
        assert item != null;
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return format("{\n\tItem: %s,\n\tQuantity: %s\n}", item, quantity);
    }

}
