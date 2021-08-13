package pl.testing;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public List<Meal> getMeals() {
        return meals;
    }

    public void addMealToOrder(Meal meal){
        this.meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal){
        this.meals.remove(meal);
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    private List<Meal> meals = new ArrayList<>();

    @Override
    public String toString() {
        return "Order=" +
                meals +
                '}';
    }

    void cancel(){
        this.meals.clear();
    }
}
