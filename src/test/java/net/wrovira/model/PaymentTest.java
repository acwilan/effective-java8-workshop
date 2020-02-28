package net.wrovira.model;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PaymentTest {

    private static final long TIME_MILLIS = System.currentTimeMillis();
    private static final float AMOUNT = 1.0f;

    @Test
    public void givenParams_WhenConstructor_ThenCheckFields() {
        final Payment payment = new Payment.Builder()
                .withAmount(AMOUNT)
                .withPaymentDate(new Date(TIME_MILLIS))
                .build();

        assertThat(payment.getPaymentDate().getTime(), is(TIME_MILLIS));
        assertThat(payment.getAmount(), is(AMOUNT));
    }

    @Test
    public void givenOriginalDateModifiedAfterConstructor_WhenGetProperty_ThenShouldStaySame() {
        final Date paymentDate = new Date(TIME_MILLIS);
        final Payment payment = new Payment.Builder()
                .withAmount(AMOUNT)
                .withPaymentDate(paymentDate)
                .build();

        paymentDate.setTime(TIME_MILLIS + TimeUnit.DAYS.toMillis(1L));

        assertThat(payment.getPaymentDate().getTime(), is(TIME_MILLIS));
        assertThat(payment.getAmount(), is(AMOUNT));
    }

}
