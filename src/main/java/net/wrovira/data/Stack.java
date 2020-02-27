package net.wrovira.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Stack<E> {

    private final List<E> elements;

    public Stack() {
        elements = new ArrayList<>();
    }

    public void push(E obj) {
        elements.add(obj);
    }

    public void pushAll(Collection<? extends E> src) {
        for (final E element : src) {
            elements.add(element);
        }
    }

    public E pop() {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements.remove(elements.size() - 1);
    }

    public void popAll(Collection<? super E> dst) {
        while (!elements.isEmpty()) {
            dst.add(pop());
        }
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
