package net.wrovira.model;

import org.junit.Before;
import org.junit.Test;

import static net.wrovira.model.EurozoneCountry.SPAIN;
import static net.wrovira.model.EurozoneCountry.getEurozoneGDPInMillions;
import static net.wrovira.model.EurozoneCountry.getEurozonePopulation;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EurozoneCountryTest {

    private static final long EUROZONE_POPULATION = 333_035_385L;
    private static final long EUROZONE_GDP_MILLIONS = 12_460_362L;
    private static final long SPAIN_POPULATION = 47_190_493L;
    private static final long SPAIN_GDP_MILLIONS = 1_460_250L;

    @Test
    public void givenCountry_WhenGetPopulation_ThenShouldReturnValue() {
        assertThat(SPAIN.getPopulation(), is(SPAIN_POPULATION));
    }

    @Test
    public void givenCountry_WhenGetGDPInMillions_ThenShouldReturnValue() {
        assertThat(SPAIN.getGDPInMillions(), is(SPAIN_GDP_MILLIONS));
    }

    @Test
    public void givenCountry_WhenGetGDPPerCapita_ThenReturnValue() {
        assertThat(SPAIN.getGDPPerCapita(), is(SPAIN_GDP_MILLIONS / SPAIN_POPULATION));
    }

    @Test
    public void whenGetEurozonePopulation_ThenReturnValue() {
        assertThat(getEurozonePopulation(), is(EUROZONE_POPULATION));
    }

    @Test
    public void whenGetEurozoneGDPInMillions_ThenReturnValue() {
        assertThat(getEurozoneGDPInMillions(), is(EUROZONE_GDP_MILLIONS));
    }
}
