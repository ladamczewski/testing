package pl.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(8);

        //then
        assertEquals(27,discountedPrice);
        }

    @Test
    void referencesToSameObjectShouldBeEquals(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;
        //then
        assertSame(meal1, meal2);
        }

    @Test
    void referencesToDifferentObjectShouldNotBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(10);
        //then
        assertNotSame(meal1, meal2);
        }

    @Test
    void twoMealsShouldBeEqualWhenNamesAndPricesAreTheSame(){
        //given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");
        //then
        assertEquals(meal1, meal2);
    }
    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThenPrice(){
        //given
        Meal meal = new Meal(10, "soup");

        //when
        //then
        assertThrows(IllegalArgumentException.class, ()-> meal.getDiscountedPrice(12));

    }
}