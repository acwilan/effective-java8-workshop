package net.wrovira.model;

import java.util.Date;

public class Payment {

    private final float amount;
    private final Date paymentDate;

    private Payment(final Builder builder) {
        amount = builder.amount;
        paymentDate = builder.paymentDate;
    }

    public float getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public static final class Builder {
        private float amount;
        private Date paymentDate;

        public Builder() {
        }

        public Builder withAmount(final float amount) {
            this.amount = amount;
            return this;
        }

        public Builder withPaymentDate(final Date paymentDate) {
            this.paymentDate = new Date(paymentDate.getTime());
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
