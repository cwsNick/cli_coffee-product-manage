
/**
 * @author Chong Wan Si
 */

public class CoffeeProduct {

    private String name;
    private int productID;
    private int qty;

    public CoffeeProduct(String name, int productID) {
        this.name = name;
        this.productID = productID;
        this.qty = 0;
    }

    @Override
    public String toString() {
        return "Product information" 
                + "\nID: " + productID 
                + "\nName: " + name 
                + "\nQuantity: "+ qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}

class CoffeeCandy extends CoffeeProduct {

    private int noOfCandy;
    private int caloriesPerCandy;

    public CoffeeCandy(String name, int productID, int noOfCandy, int caloriesPerCandy) {
        super(name, productID);
        this.noOfCandy = noOfCandy;
        this.caloriesPerCandy = caloriesPerCandy;
    }

    public int getNoOfCandy() {
        return noOfCandy;
    }

    public void setNoOfCandy(int noOfCandy) {
        this.noOfCandy = noOfCandy;
    }

    public int getCaloriesPerCandy() {
        return caloriesPerCandy;
    }

    public void setCaloriesPerCandy(int caloriesPerCandy) {
        this.caloriesPerCandy = caloriesPerCandy;
    }

    @Override
    public String toString() {
        return super.toString() 
          + "\nNumber of candies per package: " + noOfCandy
          + "\nCalories Per candy: "+ caloriesPerCandy;
    }
}

class CoffeePowder extends CoffeeProduct {

    private double weight;

    public CoffeePowder(String name, int productID, double weight) {
        super(name, productID);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return super.toString() 
          + "\nweight=" + weight;
    }
}
