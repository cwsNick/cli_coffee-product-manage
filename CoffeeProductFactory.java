import java.util.Scanner;

/**
 *
 * @author wansichong
 */
public class CoffeeProductFactory {

    public CoffeeProduct addCoffeeCandy(Scanner sc) {
        System.out.println("===============================================================");
        System.out.println("Enter product id, name, number of candy and calories per candy:");
        sc.nextLine();
        String[] input = sc.nextLine().split(", ");

        int productID = Integer.parseInt(input[0]);
        String name = input[1];
        int noOfCandy = Integer.parseInt(input[2]);
        int caloriesPerCandy = Integer.parseInt(input[3]);

        return new CoffeeCandy(name, productID, noOfCandy, caloriesPerCandy);
    }

    public CoffeeProduct addCoffeePowder(Scanner sc) {
        System.out.println("===============================================================");
        System.out.println("Enter product id, name and weight(g): ");
        sc.nextLine();
        String[] input = sc.nextLine().split(", ");

        int productID = Integer.parseInt(input[0]);
        String name = input[1];
        double weight = Double.parseDouble(input[2]);

        return new CoffeePowder(name, productID, weight);
    }
}
