import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by makiknight on 31/01/17.
 */
public class Main {

    public static int askInt(Scanner scan) {

        int result = 0;

        boolean isInt = false;
        boolean isPos = false;
        while (!isInt) {
            while(!isPos) {
                System.out.println("Value :");
                try{
                    result = scan.nextInt();
                    isInt = true;
                    if(result>=0){
                        isPos = true;
                    } else {
                        System.out.println("The value is negative...");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("The value is not a integer. Please put a correct integer.");
                    scan.next();
                }
            }

        }

        return result;

    }



    public static void main(String[] args) {

        Client client = new Client(10);

        CoffeeMachine myMachine = new CoffeeMachine();
        Drink ristretto = new Drink("Ristretto",1,2,0,0,0);
        myMachine.getListDrink().add(ristretto);

        Scanner scanner = new Scanner(System.in);

        boolean quit = false;

        while(!quit){
            System.out.println("- -  Coffee Machine 1.0.0  - -");
            System.out.println("*    Make your selection   * -");
            System.out.println("* [1] Buy a drink          * -");
            System.out.println("* [2] Add a new drink      * -");
            System.out.println("* [3] Modify a drink       * -");
            System.out.println("* [4] Delete a drink       * -");
            System.out.println("* [5] Reload an ingredient * -");
            System.out.println("* [6] Check the stock      * -");
            System.out.println("* [7] Quit                 * -");
            System.out.println("");
            System.out.println("Your selection : ");
            System.out.println("");

            try{
                int selection = scanner.nextInt();

                switch (selection){
                    case 1 :
                        boolean buyDrink = false;

                        while (!buyDrink) {
                            System.out.println("- -  Coffee Machine 1.0.0  - -");
                            System.out.println("- *     Buy a drink        - *");
                            System.out.println(" - *                       - *");
                            System.out.println("* Your credits : " + client.getCash());
                            System.out.println("");
                            ArrayList<Drink> listDrink2 = myMachine.getAvailableDrinks();
                            if (listDrink2.size() == 0) {
                                System.out.println("No drink is available. Please check the stock or add a new drink.");
                                System.out.println("");
                                buyDrink = true;
                            } else {
                                for (Drink drink:listDrink2) {
                                    System.out.println(drink.getName() + " Price : " + drink.getPrice());
                                    System.out.println("");
                                }
                                System.out.println("Your selection : ( [4] to go back) ");
                                int selection1 = askInt(scanner);

                                if(selection1 == 4) {
                                    buyDrink = true;
                                } else if(selection1-1 < listDrink2.size() && selection1>0) {
                                    System.out.println("- -  Coffee Machine 1.0.0  - -");
                                    System.out.println("");
                                    Drink selectedDrink = listDrink2.get(selection1-1);
                                    if(listDrink2.get(selection1-1).getPrice()> client.getCash()) {
                                        System.out.println("You do not have enough money.");
                                        System.out.println("");
                                        buyDrink = true;
                                    } else {
                                        myMachine.getCoffee().setStock(myMachine.getCoffee().getStock()-selectedDrink.getCoffeeUnit());
                                        myMachine.getChocolate().setStock(myMachine.getChocolate().getStock()-selectedDrink.getChocolateUnit());
                                        myMachine.getMilk().setStock(myMachine.getMilk().getStock()-selectedDrink.getMilkUnit());
                                        myMachine.getSugar().setStock(myMachine.getSugar().getStock()-selectedDrink.getSugarUnit());

                                        client.setCash(client.getCash() - selectedDrink.getPrice());

                                        System.out.println("Your " + selectedDrink.getName() + " is ready ! Enjoy it !");
                                        System.out.println("");
                                        buyDrink = true;

                                    }
                                } else {
                                    System.out.println("Impossible to reach the drink at the position " + selection1);
                                    System.out.println("");
                                }
                            }
                        }






                        break;
                    case 2 :

                        System.out.println("- -  Coffee Machine 1.0.0  - -");
                        System.out.println("- *    Add a new drink     - *");
                        if(myMachine.getListDrink().size()>=3){
                            System.out.println("* Impossible to add a new drink, you have to delete one !");
                            System.out.println("");
                        } else {
                            boolean goodName = false;
                            String name = "";
                            while (!goodName) {
                                System.out.println("* Put a name (cannot be change after !) : ");
                                name = scanner.next();
                                Drink dr = new Drink(name,0,0,0,0,0);
                                if(myMachine.alreadyExist(dr)){
                                    System.out.println("A drink with the same name is already existing.");
                                    System.out.println("");
                                } else {
                                    goodName = true;
                                }
                            }

                            boolean goodValue = false;
                            boolean goodPrice = false;

                            int coffee = 0;
                            int chocolate = 0;
                            int milk = 0;
                            int price = 0;

                            while(!goodPrice) {
                                System.out.println("* Price : ");
                                price = askInt(scanner);

                                if(price == 0 ) {
                                    System.out.println("Price is 0, not good for capitalism. Please insert a good price.");
                                } else {
                                    goodPrice = true;
                                }
                            }

                            while(!goodValue) {
                                System.out.println("* Coffee : ");
                                coffee = askInt(scanner);

                                System.out.println("Chocolate : ");
                                chocolate = askInt(scanner);

                                System.out.println("* Milk : ");
                                milk = scanner.nextInt();

                                if(coffee == 0 && chocolate == 0 && milk == 0){
                                    System.out.println("At least one of these ingredients need to be use.");
                                } else {
                                    goodValue = true;
                                }
                            }

                            System.out.println("* Sugar : ");
                            int sugar = askInt(scanner);

                            Drink drink = new Drink(name,price,coffee,chocolate,milk,sugar);

                            myMachine.getListDrink().add(drink);
                            System.out.println("* Drink added !");
                            System.out.println("");

                        }
                        break;
                    case 3 :
                        boolean modifyDrink = false;

                        while (!modifyDrink) {
                            ArrayList<Drink> listDrink = myMachine.getListDrink();
                            System.out.println("- -  Coffee Machine 1.0.0  - -");
                            System.out.println("* -     Modify a drink     * -");
                            for (Drink drink:listDrink) {
                                System.out.println(drink.getName());
                            }
                            System.out.println("Your selection : ( [4] to go back) ");
                            int selection2 = askInt(scanner);

                            if(selection2 == 4) {
                                modifyDrink = true;
                            } else if(selection2-1 < myMachine.getListDrink().size() && selection2>0) {
                                System.out.println("- -  Coffee Machine 1.0.0  - -");
                                System.out.println("* You will change the drink " + myMachine.getListDrink().get(selection2 - 1).getName());
                                System.out.println("* Actual price : " + myMachine.getListDrink().get(selection2 - 1).getPrice());
                                System.out.println("* Actual coffee : " + myMachine.getListDrink().get(selection2 - 1).getCoffeeUnit());
                                System.out.println("* Actual chocolate : " + myMachine.getListDrink().get(selection2 - 1).getChocolateUnit());
                                System.out.println("* Actual milk : " + myMachine.getListDrink().get(selection2 - 1).getMilkUnit());
                                System.out.println("* Actual sugar : " + myMachine.getListDrink().get(selection2 - 1).getSugarUnit());
                                System.out.println("");

                                System.out.println("* New price : ");
                                int price = askInt(scanner);

                                System.out.println("* Coffee value : ");
                                int coffee = askInt(scanner);

                                System.out.println("* Chocolate value : ");
                                int chocolate = askInt(scanner);

                                System.out.println("* Milk value : ");
                                int milk = askInt(scanner);

                                System.out.println("* Sugar value : ");
                                int sugar = askInt(scanner);

                                myMachine.getListDrink().get(selection2 - 1).setCoffeeUnit(coffee);
                                myMachine.getListDrink().get(selection2 - 1).setChocolateUnit(chocolate);
                                myMachine.getListDrink().get(selection2 - 1).setMilkUnit(milk);
                                myMachine.getListDrink().get(selection2 - 1).setSugarUnit(sugar);
                                myMachine.getListDrink().get(selection2 - 1).setPrice(price);

                                modifyDrink = true;

                                System.out.println("");
                            } else {
                                System.out.println("Impossible to reach the drink at the position " + selection2);
                                System.out.println("");
                            }
                        }

                        break;
                    case 4 :
                        boolean deleteDrink = false;
                        while(!deleteDrink) {
                            ArrayList<Drink> listDrink = myMachine.getListDrink();
                            System.out.println("- -  Coffee Machine 1.0.0  - -");
                            System.out.println("* -     Delete a drink     * -");

                            for (Drink drink:listDrink) {
                                System.out.println(drink.getName());
                            }
                            System.out.println("Your selection : ( [4] to get back) ");

                            int selection3 = askInt(scanner);

                            if(selection3 == 4) {
                                deleteDrink = true;
                            } else if(selection3 -1 < myMachine.getListDrink().size() && selection3>0) {
                                System.out.println("You have deleted " + myMachine.getListDrink().get(selection3-1).getName());
                                myMachine.getListDrink().remove(selection3 -1);
                                System.out.println("");

                                deleteDrink = true;
                            } else {
                                System.out.println("Impossible to reach the drink at the position " + selection3);
                                System.out.println("");
                            }
                        }
                        break;

                    case 5 :
                        boolean reloadIngredient = false;

                        while (!reloadIngredient) {
                            System.out.println("- -       Coffee Machine 1.0.0      - -");
                            System.out.println("* -     Which ingredient reload?    * -");
                            System.out.println("* [1] Coffee                        * -");
                            System.out.println("* [2] Chocolate                     * -");
                            System.out.println("* [3] Milk                          * -");
                            System.out.println("* [4] Sugar                         * -");
                            System.out.println("Your selection : ");
                            System.out.println("** ** ** ** ** * ** ** ** ** **");
                            System.out.println("");

                            int selection5 = askInt(scanner);

                            switch (selection5) {
                                case 1 :
                                    System.out.println("- -  Coffee Machine 1.0.0  - -");
                                    System.out.println("* -     Adding coffee      - *");
                                    System.out.println(" Quantity : ");
                                    System.out.println("** ** ** ** ** * ** ** ** ** **");
                                    System.out.println("");

                                    int quantityCof = askInt(scanner);
                                    myMachine.getCoffee().setStock(myMachine.getCoffee().getStock() + quantityCof);

                                    reloadIngredient = true;
                                    break;
                                case 2 :
                                    System.out.println("- -  Coffee Machine 1.0.0  - -");
                                    System.out.println("* -    Adding chocolate    - *");
                                    System.out.println(" Quantity : ");
                                    System.out.println("** ** ** ** ** * ** ** ** ** **");
                                    System.out.println("");

                                    int quantityCho = askInt(scanner);
                                    myMachine.getChocolate().setStock(myMachine.getChocolate().getStock() + quantityCho);

                                    reloadIngredient = true;
                                    break;
                                case 3 :
                                    System.out.println("- -  Coffee Machine 1.0.0  - -");
                                    System.out.println("* -      Adding milk       - *");
                                    System.out.println(" Quantity : ");
                                    System.out.println("** ** ** ** ** * ** ** ** ** **");
                                    System.out.println("");

                                    int quantityMil = askInt(scanner);
                                    myMachine.getMilk().setStock(myMachine.getMilk().getStock() + quantityMil);

                                    reloadIngredient = true;
                                    break;
                                case 4 :
                                    System.out.println("- -  Coffee Machine 1.0.0  - -");
                                    System.out.println("* -      Adding sugar      - *");
                                    System.out.println(" Quantity : ");
                                    System.out.println("** ** ** ** ** * ** ** ** ** **");
                                    System.out.println("");

                                    int quantity = askInt(scanner);
                                    myMachine.getSugar().setStock(myMachine.getSugar().getStock() + quantity);

                                    reloadIngredient = true;
                                    break;
                            }
                        }

                        break;
                    case 6 :
                        int coffee = myMachine.getCoffee().getStock();
                        int chocolate = myMachine.getChocolate().getStock();
                        int milk = myMachine.getMilk().getStock();
                        int sugar = myMachine.getSugar().getStock();
                        System.out.println("- -  Coffee Machine 1.0.0  - -");
                        System.out.println("* -     Check the stock    * -");
                        System.out.println("* Coffee : " + coffee);
                        System.out.println("* Chocolate : " + chocolate);
                        System.out.println("* Milk : " + milk);
                        System.out.println("* Sugar : " + sugar);
                        System.out.println("** ** ** ** ** * ** ** ** ** **");
                        System.out.println("");
                        break;
                    case 7 :
                        quit = true;
                        System.out.println("Goodbye !");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, the machine needs a number.");
                System.out.println("");
                scanner.next();
            }

        }



    }
}
