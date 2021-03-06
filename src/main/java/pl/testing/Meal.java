package pl.testing;

import java.util.Objects;

public class Meal {
    private int price;
    private String name;
    private int quantity;

    public Meal(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Meal(int price, String name, int quantity) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return price == meal.price && Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }

    public Meal(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return " price=" + price +
                ", name='" + name + " ";
    }

    public int getDiscountedPrice(int discount){

        if(discount > this.price){
            throw new IllegalArgumentException("Discount cannot be higher then price!");
        }
        return this.price - discount;
    }
}
