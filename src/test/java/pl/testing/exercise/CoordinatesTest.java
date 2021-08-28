package pl.testing.exercise;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinatesTest {
    @Test
    void minusValueArgumentsInConstructorShouldThrowException(){
        //given
        int a = 1;
        int b = -3;
        //when
        //then
        assertThrows(IllegalArgumentException.class, ()->{new Coordinates(a, b);});
    }

    @Test
    void copyShouldChangeCoordinatesByGivenArguments(){
        //given
        Coordinates coordinates = new Coordinates(4, 5);
        int a = 2;
        int b = 5;
        //when
        Coordinates nextCoordinates = Coordinates.copy(coordinates, a, b);
        //then
        assertAll("Checking coordinates change",
                ()->assertThat(nextCoordinates.getX(), is(6)),
                ()->assertThat(nextCoordinates.getY(), is(10)));

    }

    @Test
    void copyCoordinatesWithZeroValueShouldNotChangeThem(){
        Coordinates coordinates = new Coordinates(4, 5);
        int a = 0;
        int b = 0;
        //when
        Coordinates nextCoordinates = Coordinates.copy(coordinates, a, b);
        //then
        assertAll("Checking coordinates change",
                ()->assertThat(nextCoordinates.getX(), is(4)),
                ()->assertThat(nextCoordinates.getY(), is(5)));

    }
}