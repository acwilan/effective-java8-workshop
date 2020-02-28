package net.wrovira.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenNoPortSpecified_WhenToString_ThenDefaultsTo80() {
        final String expected = "http://www.scispike.com:80/scispike/?useCache=true";

        final String actual = new Config.Builder("scispike")
                .withHost("www.scispike.com")
                .withUseCache(true)
                .build()
                .toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenEmptyContextRoot_WhenConstructor_ThenThrowException() {
        thrown.expect(IllegalArgumentException.class);

        new Config.Builder(null).build();
    }

    @Test
    public void givenCustomPortAndProtocol_WhenToString_ThenUrlIsDifferent() {
        final String expected = "https://www.scispike.com:8080/scispike/?useCache=true";

        final String actual = new Config.Builder("scispike")
                .withProtocol("https")
                .withHost("www.scispike.com")
                .withPort("8080")
                .withUseCache(true)
                .build()
                .toString();

        assertThat(actual, is(expected));
    }

}
