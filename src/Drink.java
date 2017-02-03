import java.util.Objects;

/**
 * Created by makiknight on 31/01/17.
 *
 *
 * Class representing a drink
 */
public class Drink {

    private String name;
    private int price;
    private int coffeeUnit;
    private int chocolateUnit;
    private int milkUnit;
    private int sugarUnit;

    /**
     * @param name          : Name of the drink
     * @param price         : Cost of the drink
     * @param coffeeUnit    : Coffee require
     * @param chocolateUnit : Chocolate require
     * @param milkUnit      : Milk require
     * @param sugarUnit     : Sugar require
     */
    public Drink(String name, int price, int coffeeUnit, int chocolateUnit, int milkUnit, int sugarUnit) {
        this.name = name;
        this.price = price;
        this.coffeeUnit = coffeeUnit;
        this.chocolateUnit = chocolateUnit;
        this.milkUnit = milkUnit;
        this.sugarUnit = sugarUnit;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCoffeeUnit() {
        return coffeeUnit;
    }

    public void setCoffeeUnit(int coffeeUnit) {
        this.coffeeUnit = coffeeUnit;
    }

    public int getChocolateUnit() {
        return chocolateUnit;
    }

    public void setChocolateUnit(int chocolateUnit) {
        this.chocolateUnit = chocolateUnit;
    }

    public int getMilkUnit() {
        return milkUnit;
    }

    public void setMilkUnit(int milkUnit) {
        this.milkUnit = milkUnit;
    }

    public int getSugarUnit() {
        return sugarUnit;
    }

    public void setSugarUnit(int sugarUnit) {
        this.sugarUnit = sugarUnit;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", coffeeUnit=" + coffeeUnit +
                ", chocolateUnit=" + chocolateUnit +
                ", milkUnit=" + milkUnit +
                ", sugarUnit=" + sugarUnit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(getName(), drink.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public void changeIngredient(int coffee, int chocolate, int milk, int sugar, int price) {
        coffeeUnit = coffee;
        chocolateUnit = chocolate;
        milkUnit = milk;
        sugarUnit = sugar;
        this.price = price;
    }


}
