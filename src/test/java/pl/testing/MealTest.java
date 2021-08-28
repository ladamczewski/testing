package pl.testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.testing.extensions.IAExceptionIgnore;
import pl.testing.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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

    @Tag("Pizza")
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

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 19})
    void mealPricesShouldBeLowerThan20(int price){
    assertThat(price, lessThan(20));
    }

    @ExtendWith(IAExceptionIgnore.class)
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4, 9})
     void mealPricesShouldBeLowerThan10(int price){
        if(price>4){
            throw new IllegalStateException();
        }
        assertThat(price, lessThan(10));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNAmeAndPrice(String name, int price){
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name){
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    private static Stream<String> createCakeNames(){
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    private int calculatePrice(int price, int quantity){
        return price * quantity;
    }
    @Tag("Pizza")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices(){
        Order order = new Order();
        order.addMealToOrder(new Meal(10, "Hamburger", 2));
        order.addMealToOrder(new Meal(13, "Cheeseburger", 4));
        order.addMealToOrder(new Meal(7, "Hot dog", 2));
        order.addMealToOrder(new Meal(6, "Pizza", 8));


        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for (int i = 0; i< order.getMeals().size(); i++){
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price,quantity), lessThan(60));
            };
            String name = "Test name: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;
    }
}