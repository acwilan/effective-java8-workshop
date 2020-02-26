package net.wrovira.model;

import java.util.Date;
import java.util.Objects;

import static java.lang.String.format;

public class Employee {

    private String employeeNumber;
    private String firstName;
    private String lastName;
    private Date dob;
    private double salary;

    private Employee(final Builder builder) {
        employeeNumber = builder.employeeNumber;
        firstName = builder.firstName;
        lastName = builder.lastName;
        dob = builder.dob;
        salary = builder.salary;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        final Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(employeeNumber, employee.employeeNumber) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(dob, employee.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, firstName, lastName, dob, salary);
    }

    @Override
    public String toString() {
        return format("%s, %s", lastName, firstName);
    }

    public static final class Builder {
        private String employeeNumber;
        private String firstName;
        private String lastName;
        private Date dob;
        private double salary;

        public Builder() {
        }

        public Builder withEmployeeNumber(final String employeeNumber) {
            this.employeeNumber = employeeNumber;
            return this;
        }

        public Builder withFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withDob(final Date dob) {
            this.dob = dob;
            return this;
        }

        public Builder withSalary(final double salary) {
            this.salary = salary;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
