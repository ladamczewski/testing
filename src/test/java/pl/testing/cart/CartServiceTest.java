package pl.testing.cart;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import pl.testing.order.Order;
import pl.testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Test
    void processCartShouldSendToPrepare(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCard(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCard = cartService.processCart(cart);

        //then
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart);

    assertThat(resultCard.getOrders(), hasSize(1));
        assertThat(resultCard.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);
    }
    @Test
    void processCartShouldNotSendToPrepare(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCard(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart resultCard = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);
        assertThat(resultCard.getOrders(), hasSize(1));
        assertThat(resultCard.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
}
    @Test
    void canHandleCartShouldReturnMultiplyValues(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCard(order);

        CartHandler cartHandler = mock(CartHandler.class);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(true, false, false, true);

        //when

        //then
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
    }
    void processCartShouldNotSendToPrepareWithArgumentMatchers(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCard(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        //when
        Cart resultCard = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPrepare(any(Cart.class));
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));
        assertThat(resultCard.getOrders(), hasSize(1));
        assertThat(resultCard.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void processCartShouldSendToPrepareWithLambdas(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCard(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(argThat(c -> c.getOrders().size()>0))).willReturn(true);

        //when
        Cart resultCard = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCard.getOrders(), hasSize(1));
        assertThat(resultCard.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));

    }
    @Test
    void canHandleCartShouldThrowException(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCard(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartService.processCart(cart)).willThrow(IllegalStateException.class);

        //when
        //then
        assertThrows(IllegalStateException.class, ()-> cartService.processCart(cart));
    }

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor(){
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCard(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        ArgumentCaptor<Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCard = cartService.processCart(cart);

        //then
//        verify(cartHandler).sendToPrepare(argumentCaptor.capture());
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));

        assertThat(resultCard.getOrders(), hasSize(1));
        assertThat(resultCard.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));

    }
}
