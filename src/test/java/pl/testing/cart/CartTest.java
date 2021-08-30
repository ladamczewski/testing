package pl.testing.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.testing.order.Order;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;


@DisplayName("Test cases for Card")
class CartTest {

    @Test
    void simulateLargeOrder(){
        //given
        Cart cart = new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(18), cart::simulateLargeOrder);
    }

    @Test
    void cardShouldNotBeEmptyAfterAddOrderToCart(){
        //given
        Order order = new Order();
        Cart cart = new Cart();

        //when
        cart.addOrderToCard(order);

        //then
        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));
        assertAll("This is a group assertions",
                ()-> assertThat(cart.getOrders(), notNullValue()),
                ()-> assertThat(cart.getOrders(), hasSize(1)),
                ()-> assertThat(cart.getOrders(), is(not(empty())))
                );
    }
}