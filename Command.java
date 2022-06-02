import java.util.*;

public interface Command {
    abstract public void execute();

    abstract public void undo();

    abstract public void redo();
}

class collectProductCommand implements Command {

    CoffeeProduct coffeeProduct;
    Scanner sc;
    Vector CoffeeProducts;
    int qtyToDeposit;
    Stack commands;
    Stack redos;

    public collectProductCommand(Stack commands, Stack redos, Scanner sc, Vector CoffeeProducts) {
        this.sc = sc;
        this.coffeeProduct = null;
        this.qtyToDeposit = 0;
        this.CoffeeProducts = CoffeeProducts;
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public void execute() {
        System.out.println("===============================================================");
        System.out.println("Enter product id:");
        String code = sc.next();

        SearchByIdCommand f = new SearchByIdCommand(CoffeeProducts);

        coffeeProduct = f.getCoffeeProductByID(Integer.parseInt(code));

        if (coffeeProduct == null) {
            System.out.println("No this product!");
        } else {
            System.out.println("Quantity to deposit:");
            qtyToDeposit = sc.nextInt();

            coffeeProduct.setQty(coffeeProduct.getQty() + qtyToDeposit);
            System.out.println("Received " + qtyToDeposit + " packs of " + coffeeProduct.getName()
                    + ". Current quantity is " + coffeeProduct.getQty() + ".");
            redos.clear();
            commands.push(this);
        }
    }

    @Override
    public void undo() {
        coffeeProduct.setQty(coffeeProduct.getQty() - qtyToDeposit);
    }

    @Override
    public void redo() {
        coffeeProduct.setQty(coffeeProduct.getQty() + qtyToDeposit);
    }

    @Override
    public String toString() {
        return "Received " + qtyToDeposit + " " + coffeeProduct.getName() + " (" + coffeeProduct.getProductID() + ")";
    }
}

class SendProductCommand implements Command {

    CoffeeProduct coffeeProduct;
    Vector CoffeeProducts;
    Scanner sc;
    int qtyToSend;
    Stack commands;
    Stack redos;

    public SendProductCommand(Stack commands, Stack redos, Scanner sc, Vector CoffeeProducts) {
        this.sc = sc;
        this.coffeeProduct = null;
        this.CoffeeProducts = CoffeeProducts;
        this.qtyToSend = 0;
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public void execute() {
        System.out.println("===============================================================");
        System.out.println("Enter product id:");
        String code = sc.next();

        SearchByIdCommand f = new SearchByIdCommand(CoffeeProducts);
        coffeeProduct = f.getCoffeeProductByID(Integer.parseInt(code));

        if (coffeeProduct == null) {
            System.out.println("No this product!");
        } else {
            System.out.println("Quantity to ship:");
            qtyToSend = sc.nextInt();

            if (qtyToSend > coffeeProduct.getQty()) {
                System.out.println("Invalid quantity (current balance is less than required quantity). Try again!!!");
            } else {
                coffeeProduct.setQty(coffeeProduct.getQty() - qtyToSend);
                System.out.println("Shipped " + qtyToSend + " packs of " + coffeeProduct.getName()
                        + ". Current quantity is " + coffeeProduct.getQty() + ".");
                redos.clear();
                commands.push(this);
            }

        }

    }

    @Override
    public void undo() {
        coffeeProduct.setQty(coffeeProduct.getQty() + qtyToSend);
    }

    @Override
    public void redo() {
        coffeeProduct.setQty(coffeeProduct.getQty() - qtyToSend);
    }

    @Override
    public String toString() {
        return "Shipped " + qtyToSend + " " + coffeeProduct.getName() + " (" + coffeeProduct.getProductID() + ")";
    }
}

class viewProductCommand implements Command {

    Scanner sc;
    Command com = null;
    Vector CoffeeProducts;

    public viewProductCommand(Scanner sc, Vector CoffeeProducts) {
        this.sc = sc;
        this.CoffeeProducts = CoffeeProducts;
    }

    @Override
    public void execute() {
        System.out.println("===============================================================");
        System.out.println("Enter product id (* to show all):");
        String id = sc.next();
        if ("*".equals(id)) {
            com = new ViewAllCommand(CoffeeProducts);
            com.execute();
        } else {
            com = new ViewByIdCommand(Integer.parseInt(id), CoffeeProducts, sc);
            com.execute();
        }
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }

    @Override
    public String toString() {
        return null;
    }
}

class addProductCommand implements Command {

    Scanner sc;
    Command com = null;
    Vector CoffeeProducts;
    Stack commands;
    Stack redos;

    public addProductCommand(Stack commands, Stack redos, Scanner sc, Vector CoffeeProducts) {
        this.sc = sc;
        this.CoffeeProducts = CoffeeProducts;
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public void execute() {
        System.out.println("===============================================================");
        System.out.println("Enter Coffee type (cc=Coffee Candy/cp=Coffee Powder):");
        String type = sc.next();
        if (null == type) {
            com = null;
        } else {
            switch (type) {
                case "cc":
                    com = new CreateCoffeeCandyCommand(CoffeeProducts, sc);
                    com.execute();
                    redos.clear();
                    commands.push(this);
                    break;
                case "cp":
                    com = new CreateCoffeePowderCommand(CoffeeProducts, sc);
                    com.execute();
                    redos.clear();
                    commands.push(this);

                    break;
                default:
                    com = null;
                    break;
            }
        }
    }

    @Override
    public void undo() {
        com.undo();
    }

    @Override
    public void redo() {
        com.redo();
    }

    @Override
    public String toString() {
        return com.toString();
    }
}

class CreateCoffeeCandyCommand implements Command {

    Vector CoffeeProducts;
    CoffeeProduct coffeeCandy;
    Scanner sc;

    public CreateCoffeeCandyCommand(Vector CoffeeProducts, Scanner sc) {
        this.CoffeeProducts = CoffeeProducts;
        this.sc = sc;
        this.coffeeCandy = null;
    }

    @Override
    public void execute() {
        CoffeeProductFactory f = new CoffeeProductFactory();
        coffeeCandy = f.addCoffeeCandy(sc);
        CoffeeProducts.add(coffeeCandy);
        System.out.println("New product record created.");
    }

    @Override
    public void undo() {
        if (coffeeCandy != null) {
            CoffeeProducts.remove(coffeeCandy);
        }
    }

    @Override
    public void redo() {
        if (coffeeCandy != null) {
            CoffeeProducts.add(coffeeCandy);
        }
    }

    @Override
    public String toString() {
        return "Add " + coffeeCandy.getProductID() + " " + coffeeCandy.getName();
    }
}

class CreateCoffeePowderCommand implements Command {

    Vector CoffeeProducts;
    CoffeeProduct coffeePowder;
    Scanner sc;

    public CreateCoffeePowderCommand(Vector CoffeeProducts, Scanner sc) {
        this.CoffeeProducts = CoffeeProducts;
        this.sc = sc;
        this.coffeePowder = null;
    }

    @Override
    public void execute() {
        CoffeeProductFactory f = new CoffeeProductFactory();
        coffeePowder = f.addCoffeePowder(sc);
        CoffeeProducts.add(coffeePowder);
        System.out.println("New product record created.");
    }

    @Override
    public void undo() {
        if (coffeePowder != null) {
            CoffeeProducts.remove(coffeePowder);
        }
    }

    @Override
    public void redo() {
        if (coffeePowder != null) {
            CoffeeProducts.add(coffeePowder);
        }
    }

    @Override
    public String toString() {
        return "Add " + coffeePowder.getProductID() + " " + coffeePowder.getName();
    }
}

// View
class SearchByIdCommand implements Command {

    Vector CoffeeProducts;

    public SearchByIdCommand(Vector CoffeeProducts) {
        this.CoffeeProducts = CoffeeProducts;
    }

    public CoffeeProduct getCoffeeProductByID(int id) {
        for (int i = 0; i < CoffeeProducts.size(); i++) {
            CoffeeProduct coffeeProduct = (CoffeeProduct) CoffeeProducts.elementAt(i);
            if (coffeeProduct.getProductID() == id) {
                return coffeeProduct;
            }
        }
        return null;
    }

    public int getCoffeeProductQtyByID(int id) {
        return getCoffeeProductByID(id).getQty();
    }

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }
}

class ViewByIdCommand implements Command {

    int id;
    Scanner sc;
    Vector CoffeeProducts;

    public ViewByIdCommand(int id, Vector CoffeeProducts, Scanner sc) {
        this.id = id;
        this.CoffeeProducts = CoffeeProducts;
        this.sc = sc;
    }

    @Override
    public void execute() {
        boolean haveProduct = false;

        for (int i = 0; i < CoffeeProducts.size(); i++) {
            CoffeeProduct coffeeProduct = (CoffeeProduct) CoffeeProducts.elementAt(i);
            if (coffeeProduct.getProductID() == id) {
                System.out.println();
                Client.printCoffeeProduct(coffeeProduct);
                haveProduct = true;
            }
        }

        if (haveProduct == false) {
            System.out.println("No product id = " + id);
        }
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }
}

class ViewAllCommand implements Command {

    Vector CoffeeProducts;

    public ViewAllCommand(Vector CoffeeProducts) {
        this.CoffeeProducts = CoffeeProducts;
    }

    @Override
    public void execute() {

        System.out.println("Coffee Product information\nID\t\tName\t\t\t\tQuantity\t\tOther Info");

        for (int i = 0; i < CoffeeProducts.size(); i++) {
            Client.printAsRow((CoffeeProduct) CoffeeProducts.elementAt(i));
        }
        System.out.println("============== End of info =================");
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }
}

class ViewRedoUndoListCommand implements Command {

    Stack commands;
    Stack redos;

    public ViewRedoUndoListCommand(Stack commands, Stack redos) {
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public void execute() {
        Client.printRedoUndoList(commands, redos);
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }
}

// Redo Undo
////////////////////////////////////////////////////////////////////////////////
class RedoCommand implements Command {

    Stack commands;
    Stack redos;

    public RedoCommand(Stack commands, Stack redos) {
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public void execute() {
        if (!redos.isEmpty()) {
            Command com = (Command) redos.pop();
            com.redo();
            commands.push(com);
            System.out.println("redo completed.");
        } else {
            System.out.println("Nothing to redo!");
        }
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }
}

class UndoCommand implements Command {

    Stack commands;
    Stack redos;

    public UndoCommand(Stack commands, Stack redos) {
        this.commands = commands;
        this.redos = redos;
    }

    @Override
    public void execute() {
        if (!commands.isEmpty()) {
            Command com = (Command) commands.pop();
            com.undo();
            redos.push(com);
            System.out.println("undo completed.");
        } else {
            System.out.println("Nothing to undo!");
        }
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }
}


// Other
class ExitCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Thanks for using Coffee Inventory Management System!!");
        System.exit(0);
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }
}
