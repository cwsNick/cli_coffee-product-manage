import java.util.*;

public class Assignment {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Vector CoffeeProducts = new Vector();
        Stack commands = new Stack();
        Stack redos = new Stack();

        String command;
        while (true) {
            System.out.println(
                    "\nCoffee Inventory Management System" 
                    + "\nPlease enter command: [a | v | c | s | u | r | sl | x]"
                    + "\na = add product,  v = view products,  c = collect product,  s = ship product,"
                    + "\nu = undo,  r = redo,  sl = show list undo/redo,  x = exit system\n"
                    );
            command = sc.next();
            Command com = createCommand(command, commands, redos, CoffeeProducts);

            if (com != null) {
                com.execute();
            }
        }
    }

    public static Command createCommand(String command, Stack commands, Stack redos, Vector CoffeeProducts) {
        Command com = null;
        switch (command) {
            case "x":
                com = new ExitCommand();
                break;
            case "u":
                com = new UndoCommand(commands, redos);
                break;
            case "r":
                com = new RedoCommand(commands, redos);
                break;
            case "v":
                com = new viewProductCommand(sc, CoffeeProducts);
                break;
            case "a":
                com = new addProductCommand(commands, redos, sc, CoffeeProducts);
                break;
            case "sl":
                com = new ViewRedoUndoListCommand(commands, redos);
                break;
            case "c":
                com = new collectProductCommand(commands, redos, sc, CoffeeProducts);
                break;
            case "s":
                com = new SendProductCommand(commands, redos, sc, CoffeeProducts);
                break;
            default:
                com = null;
        }
        return com;
    }
}
