package net.wrovira;

import net.wrovira.exception.PropertiesReadException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ConfigDepotEnumTest {

    private static final String INVALID_CONFIG_FILE = "invalidConfigFile";
    private static final String DEV_CONFIG_FILE = "dev.properties";
    private static final String PROD_CONFIG_FILE = "prod.properties";

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private ConfigDepotEnum configDepotEnum;

    @Before
    public void setUp() {
        configDepotEnum = ConfigDepotEnum.getInstance();
    }

    @After
    public void tearDown() {
        configDepotEnum.clear();
    }

    @Test
    public void givenSameInputFile_WhenGetConfig_ThenShouldReturnSameMap() {
        final Map<String, String> expected = configDepotEnum.getConfig(DEV_CONFIG_FILE);

        final Map<String, String> actual = configDepotEnum.getConfig(DEV_CONFIG_FILE);

        assertThat(actual, is(notNullValue()));
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void givenDifferentValidFiles_WhenGetConfig_ThenShouldReturnDifferentMapObjects() {
        final Map<String, String> firstCallMap = configDepotEnum.getConfig(DEV_CONFIG_FILE);

        final Map<String, String> secondCallMap = configDepotEnum.getConfig(PROD_CONFIG_FILE);

        assertThat(firstCallMap, is(notNullValue()));
        assertThat(secondCallMap, is(not(equalTo(firstCallMap))));
    }

    @Test
    public void givenInvalidInputFile_WhenGetConfig_ThenShouldThrowException() {
        thrown.expect(PropertiesReadException.class);

        configDepotEnum.getConfig(INVALID_CONFIG_FILE);
    }

    @Test
    public void givenDevProperties_WhenGetConfig_ThenTestValues() {
        final Map<String, String> properties = configDepotEnum.getConfig(DEV_CONFIG_FILE);

        assertThat(properties.get("host"), is("localhost"));
        assertThat(properties.get("debug"), is("true"));
    }

    @Test
    public void givenProdProperties_WhenGetConfig_ThenTestValues() {
        final Map<String, String> properties = configDepotEnum.getConfig(PROD_CONFIG_FILE);

        assertThat(properties.get("host"), is("rest.scispike.com"));
        assertThat(properties.get("debug"), is("false"));
    }
}
