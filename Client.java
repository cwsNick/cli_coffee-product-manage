
import java.util.Stack;

public class Client {

    public static void printCoffeeProduct(CoffeeProduct s) {
        System.out.println(s);
    }

    public static void printRedoUndoList(Stack commands, Stack redos) {

        System.out.println("\nUndo List:");
        if (commands.isEmpty()) {
            System.out.println("Empty");
        } else {
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(commands.elementAt(i).toString());
            }
        }

        System.out.println("\nRedo List:");
        if (redos.isEmpty()) {
            System.out.println("Empty");
        } else {
            for (int i = 0; i < redos.size(); i++) {
                System.out.println(redos.elementAt(i).toString());
            }
        }
        
        System.out.println();
    }

    public static void printAsRow(CoffeeProduct s) {
        System.out.print(s.getProductID() + "\t" + s.getName() + "\t" + s.getQty() + "\t\t\t");

        if (s instanceof CoffeeCandy) {
            CoffeeCandy coffeeCandy = (CoffeeCandy) s;
            System.out.println(coffeeCandy.getNoOfCandy()+ " candy per package(" + coffeeCandy.getCaloriesPerCandy()+ " calories each)");

        } else if (s instanceof CoffeePowder) {
            CoffeePowder coffeePowder = (CoffeePowder) s;
            System.out.println(coffeePowder.getWeight() + "g");
        }
    }
}
