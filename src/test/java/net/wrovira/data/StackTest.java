package net.wrovira.data;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

public class StackTest {

    private static final int INT_ELEMENT = 1;
    private Stack<Integer> stack;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        stack = new Stack<>();
    }

    @Test
    public void givenPush_WhenPop_ThenShouldReturnSameElement() {
        stack.push(INT_ELEMENT);

        final int actual = stack.pop();

        assertThat(actual, is(INT_ELEMENT));
        assertThat(stack.size(), is(0));
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void givenPushAll_WhenPopAll_ThenShouldReturnSameCollection() {
        final Collection<Integer> elements = asList(1, 2, 3);
        stack.pushAll(elements);

        final Collection<Integer> actual = new ArrayList<>();
        stack.popAll(actual);

        assertThat(actual.size(), is(elements.size()));
        assertThat(actual, containsInAnyOrder(elements.toArray()));
    }

    @Test
    public void givenStackIsEmpty_WhenPop_ThenThrowException() {
        thrown.expect(IllegalStateException.class);
        stack.pop();
    }

    @Test
    public void givenElementsPushedToStack_WhenToString_ThenShouldReturnFormattedString() {
        final Collection<Integer> elements = asList(1, 2, 3);
        stack.pushAll(elements);

        final String actual = stack.toString();

        assertThat(actual, is(elements.toString()));
    }
}
