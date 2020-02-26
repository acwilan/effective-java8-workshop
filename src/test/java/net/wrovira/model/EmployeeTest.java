package net.wrovira.model;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class EmployeeTest {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final long TIME_MILLIS = System.currentTimeMillis();
    private static final double SALARY = 1.0;
    private static final String EMPLOYEE_NUMBER = "employeeNumber";

    @Test
    public void givenTwoEqualEmployees_WhenHashCodeAndEquals_ThenShouldBeEqual() {
        final Employee firstEmployee = new Employee.Builder()
                .withEmployeeNumber(EMPLOYEE_NUMBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withDob(new Date(TIME_MILLIS))
                .withSalary(SALARY)
                .build();
        final Employee secondEmployee = new Employee.Builder()
                .withEmployeeNumber(EMPLOYEE_NUMBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withDob(new Date(TIME_MILLIS))
                .withSalary(SALARY)
                .build();

        assertThat(firstEmployee.hashCode(), is(equalTo(secondEmployee.hashCode())));
        assertThat(firstEmployee.equals(secondEmployee), is(true));
    }

    @Test
    public void givenDifferentEmployees_WhenHashCodeAndEquals_ThenShouldBeEqual() {
        final Employee firstEmployee = new Employee.Builder()
                .withEmployeeNumber(EMPLOYEE_NUMBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withDob(new Date(TIME_MILLIS))
                .withSalary(SALARY)
                .build();
        final Employee secondEmployee = new Employee.Builder()
                .withEmployeeNumber("employeeNumber2")
                .withFirstName("employeeName")
                .withLastName("employeeLastName")
                .withDob(new Date(TIME_MILLIS + TimeUnit.DAYS.toMillis(1)))
                .withSalary(2.0)
                .build();

        assertThat(firstEmployee.hashCode(), is(not(equalTo(secondEmployee.hashCode()))));
        assertThat(firstEmployee.equals(secondEmployee), is(false));
    }

    @Test
    public void givenEmployee_WhenToString_ThenShowNames() {
        final String expected = LAST_NAME + ", " + FIRST_NAME;
        final Employee employee = new Employee.Builder()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .build();

        assertThat(employee.toString(), is(expected));
    }

    @Test
    public void givenDifferentType_WhenEquals_ThenFalse() {
        final Employee employee = new Employee.Builder()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .build();

        assertThat(employee.equals(1L), is(false));
        assertThat(employee.equals(employee), is(true));
    }

}
