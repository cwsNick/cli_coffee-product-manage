### Apply the following design patterns

Class diagram
![Assignment](https://user-images.githubusercontent.com/67496200/173881032-041a0858-e112-4a77-ba26-1ba4d4564eb6.jpg)

1.  Command pattern to provide the “create product”, “show product”, “receive product”, “deliver product”, “undo”, “redo” and “display undo/redo list” functions 
2.  Factory pattern or Abstract Factory Pattern to create different Command objects and CoffeeProduct objects (e.g. Coffee Candy object, Coffee Powder object, etc.)
3.  Memento pattern to provide “Undo” and “Redo” functions

### Provide the following functions:

1. Add a CoffeeProduct record with zero quantity (CoffeeCandy or CoffeePowder or any new kind of CoffeeProduct in the coming future).

2. Show CoffeeProduct details (such as productNo, name, qty and related Information) by a given productNo (input code=* to show all records)

3. Update CoffeeProduct when the product is received from vendor

4. Update CoffeeProduct when the product is sent to the customer

5. Undo last command

6. Redo the last undone command

7. Show undo/redo list
