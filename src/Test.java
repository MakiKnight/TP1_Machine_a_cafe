import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by makiknight on 03/02/17.
 */
public class Test {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        try{
            int bla = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Pas un entier");
        }
    }
}
