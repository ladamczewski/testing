package pl.testing.cart;

import pl.testing.Meal;
import pl.testing.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Order> orders = new ArrayList<>();

    void addOrderToCard(Order order){
        this.orders.add(order);
    }

    void clearCart(){
        this.orders.clear();
    }

    public List<Order> getOrders() {
        return orders;
    }

    void simulateLargeOrder(){
        for(int i = 0 ; i< 1_000; i++){
            Meal meal = new Meal(i%10, "meal no " + i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCard(order);
        }
        System.out.println("Cart size: " + orders.size());
        clearCart();
    }
}
