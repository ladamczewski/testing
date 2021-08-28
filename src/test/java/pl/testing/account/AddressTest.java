package pl.testing.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

class AddressTest {
    @ParameterizedTest
    @CsvSource({"Fabryczna, 10", "Armii Krajowej, 11", "Kopernika, 12", "Pradawna, 22", "Ks. Popiełuszki, 22"})
    void givenAddressShouldBeNotEmptyAndHaveProperNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(number.length(), lessThan(5));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/adresses.csv")
    void givenAddressFromFileShouldBeNotEmptyAndHaveProperNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(number.length(), lessThan(5));
    }
}