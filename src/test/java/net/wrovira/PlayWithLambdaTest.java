package net.wrovira;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayWithLambdaTest {

    @Test
    public void lambdaTest() {
        final String[] planetNames = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};

        final List<String> sortedByName = sortByName(new ArrayList<>(asList(planetNames)));
        assertThat(sortedByName.get(0), is("Earth"));

        final List<String> sortedByNameLength = sortByStringLength(new ArrayList<>(asList(planetNames)));
        assertThat(sortedByNameLength.get(0), is("Mars"));

        sortThenDo(new ArrayList<>(asList(planetNames)), naturalOrder(), System.out::println);
    }

    // Sort the list by name using lambda expression and print out the list
    private static List<String> sortByName(List<String> planets) {
        return planets.stream()
                      .sorted(naturalOrder())
                      .collect(toList());
    }

    // Sort the list by length of name using lambda expression and print out the list
    private static List<String> sortByStringLength(List<String> planets) {
        return planets.stream()
                      .sorted(comparingInt(String::length))
                      .collect(toList());
    }

    private static void sortThenDo(List<String> l, Comparator<String> sorter, Consumer<String> action) {
        l.sort(sorter);
        l.forEach(action);
    }

}
