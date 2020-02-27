package net.wrovira.model;

public enum EurozoneCountry {

    AUSTRIA(8_404_252L, 384_908L),
    BELGIUM(10_918_405L, 468_522L),
    CYPRUS(838_896L, 24_910L),
    ESTONIA(1_340_194L, 19_120L),
    FINLAND(5_375_276L, 237_512L),
    FRANCE(65_075_373L, 2_649_390L),
    GERMANY(81_751_602L, 3_330_032L),
    GREECE(11_325_897L, 329_924L),
    IRELAND(4_480_858L, 227_193L),
    ITALY(60_626_442L, 2_112_780L),
    LUXEMBOURG(511_840L, 52_449L),
    MALTA(417_617L, 7_449L),
    NETHERLANDS(16_655_799L, 792_128L),
    PORTUGAL(10_636_979L, 227_676L),
    SLOVAKIA(5_435_273L, 87_642L),
    SLOVENIA(2_050_189L, 48_477L),
    SPAIN(47_190_493L, 1_460_250L);


    long population;
    long gdpInMillions;

    EurozoneCountry(final long population, final long gdpInMillions) {
        this.population = population;
        this.gdpInMillions = gdpInMillions;
    }

    public long getPopulation() {
        return population;
    }

    public long getGDPInMillions() {
        return gdpInMillions;
    }

    public long getGDPPerCapita() {
        return gdpInMillions / population;
    }

    public static long getEurozonePopulation() {
        long population = 0L;
        for (final EurozoneCountry country : values()) {
            population += country.population;
        }
        return population;
    }

    public static long getEurozoneGDPInMillions() {
        long gdp = 0L;
        for (final EurozoneCountry country : values()) {
            gdp += country.gdpInMillions;
        }
        return gdp;
    }

}
