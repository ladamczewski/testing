package pl.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class OrderTest {
    private Order order;

    @BeforeEach
    void initializeOrder(){
        order = new Order();
    }
    @AfterEach
    void cleanUp(){
        order.cancel();
    }
    @Test
    void testAssertArrayEquals() {
        //given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        //then
        assertArrayEquals(ints1, ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOrder() {
        //then
        assertThat(order.getMeals(), empty());
    }

    @Test
    void addMealToOrderShouldIncreaseOrderSize() {
        //given
        Meal meal = new Meal(11, "hotdog");
        Meal meal2 = new Meal(8, "toast");
        Order order = new Order();

        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));
    }

    @Test
    void removeMealFromOrderShouldReduceOrderSize() {
        //given
        Meal meal = new Meal(11, "hotdog");
        Meal meal2 = new Meal(8, "toast");

        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder(){
        //given
        Meal meal = new Meal(11, "hotdog");
        Meal meal2 = new Meal(8, "toast");

        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(), contains(meal, meal2));
    }

    @Test
    void testIfTwoMealsListAreTheSame(){
        Meal meal = new Meal(11, "hot dog");
        Meal meal1 = new Meal(13, "burger");
        Meal meal2 = new Meal(8, "toast");
        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);

        //then
        assertThat(meals1, is(meals2));
    }
}