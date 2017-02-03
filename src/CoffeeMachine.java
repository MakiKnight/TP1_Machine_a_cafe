import java.util.ArrayList;

/**
 * Created by makiknight on 31/01/17.
 *
 * Class reprsenting a coffee machine
 *
 */
public class CoffeeMachine {

    private Ingredient coffee;
    private Ingredient milk;
    private Ingredient chocolate;
    private Ingredient sugar;

    private ArrayList<Drink> listDrink;


    public CoffeeMachine() {
        this.coffee = new Ingredient("Coffee");
        this.milk = new Ingredient("Milk");
        this.chocolate = new Ingredient("Chocolate");
        this.sugar = new Ingredient("Sugar");
        this.listDrink = new ArrayList<Drink>();
    }

    public Ingredient getCoffee() {
        return coffee;
    }

    public void setCoffee(Ingredient coffee) {
        this.coffee = coffee;
    }

    public Ingredient getMilk() {
        return milk;
    }

    public void setMilk(Ingredient milk) {
        this.milk = milk;
    }

    public Ingredient getChocolate() {
        return chocolate;
    }

    public void setChocolate(Ingredient chocolate) {
        this.chocolate = chocolate;
    }

    public Ingredient getSugar() {
        return sugar;
    }

    public void setSugar(Ingredient sugar) {
        this.sugar = sugar;
    }

    public ArrayList<Drink> getListDrink() {
        return listDrink;
    }

    public void setListDrink(ArrayList<Drink> listDrink) {
        this.listDrink = listDrink;
    }

    @Override
    public String toString() {
        return "CoffeeMachine{" +
                "coffee=" + coffee +
                ", milk=" + milk +
                ", chocolate=" + chocolate +
                ", sugar=" + sugar +
                ", listDrink=" + listDrink +
                '}';
    }

    public ArrayList<Drink> getAvailableDrinks(){
        ArrayList<Drink> result = new ArrayList<Drink>();

        for(Drink drink : listDrink){
            if(coffee.getStock() >= drink.getCoffeeUnit() && chocolate.getStock() >= drink.getChocolateUnit() && milk.getStock() >= drink.getMilkUnit() && sugar.getStock() >= drink.getSugarUnit()){
                result.add(drink);
            }
        }

        return result;
    }

    /**
     * @param drink : The new drink created
     * @return true if a drink already have the same name in the coffee machine
     */
    public boolean alreadyExist(Drink drink) {

        boolean result = false;
        int size = listDrink.size();
        int i = 0;

        while (!result && i < size) {
            result = drink.equals(listDrink.get(i));
            i++;
        }

        return result;
    }

    /**
     * @param drink : THe drink to add
     * @return true if the drink has been added
     */
    public boolean addDrink(Drink drink) {
        if(alreadyExist(drink)){
            System.out.println("A drink with the same name is already existing.");
            return false;
        } else if(listDrink.size()>=3) {
            System.out.println("Too many drinks in the machine (3). Delete one before add.");
            return false;
        } else {
            listDrink.add(drink);
            return true;
        }
    }
}
