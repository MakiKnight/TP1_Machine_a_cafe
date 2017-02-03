/**
 * Created by makiknight on 31/01/17.
 *
 * Class of an ingredient in the coffe machine
 *
 */
public class Ingredient {

    private String name;
    private int stock;

    /**
     * @param name  : Name of the ingredient to create
     */
    public Ingredient(String name) {
        this.name = name;
        this.stock = 10;                 // By default, the stock is empty
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", stock=" + stock +
                '}';
    }
}
