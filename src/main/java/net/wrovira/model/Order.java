package net.wrovira.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class Order {

    private String customerName;
    private List<OrderItem> orderItems;
    private Table table;

    public Order(final String customerName) {
        this(customerName, new ArrayList<>(), null);
    }

    public Order(final String customerName, final List<OrderItem> items, final Table table) {
        assert customerName != null;
        this.customerName = customerName;
        this.orderItems = items;
        this.table = table;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        assert customerName != null;
        this.customerName = customerName;
    }

    public List<OrderItem> getItems() {
        return orderItems;
    }

    public void changeOrder(final List<OrderItem> items) {
        this.orderItems = items;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(final Table table) {
        this.table = table;
    }

    public void addOrder(final OrderItem oi) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(oi);
    }

    public double getTotalPrice() {
        return orderItems.stream()
                .map(orderItem -> orderItem.getQuantity() * orderItem.getItem().getPrice())
                .reduce(0.0, Double::sum);
    }

    @Override
    public String toString() {
        return format("{\n\tCustomer: %s,\n\tTable: %s,\n\tItems: %s,\n\tTotalPrice: %.2f\n}", customerName, table, orderItems, getTotalPrice());
    }


}
