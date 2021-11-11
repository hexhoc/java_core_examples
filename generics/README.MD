# Generics

## Requirements:

### Create simple generics class
- Create class "TwoGen" that contain two non-static variables - "key" and "value" with generics type 

### Generics method (easy)
- Create class "GenericsMethod" without generics
- Create a method "print" that accepts 2 parameters with the generics type

### Generics bounded (easy)
- Create class "Animal", class "Cat" that extend "Animal", class "HomeCat" and "WildCat" that extend "Cat"
- Create class "GenericsBounded" that work only for the Cat class and its children (WildCat, HomeCat)
- Create three instance of GenericsBounded class with Cat, HomeCat and WildCat

### Exercise 1 (medium)
- Create a class "Stats" that contains an array as a non-static variable with a generic type
- Create non-static the sameAvg() method, which will check the equality
  of the average values of arrays of two Stats objects and return true if equals or false.
- Notice цe should be able to compare an array with different types from each other. For example
```
public class StatsDemoApp {
  public static void main(String args[]) {
    Stats<Integer> intStats = new Stats<>( 1 , 2 , 3 , 4 , 5 );
    Stats<Double> doubleStats = new Stats<>( 1.0 , 2.0 , 3.0 , 4.0 , 5.0 );
    if (intStats.sameAvg(doubleStats)) {
      System.out.println( "Средние значения равны" );
    } else {
      System.out.println( "Средние значения не равны" );
      }
    }
}
```

### Exercise 2 (hard)

- Classes Fruit -> Apple, Orange are given.
- Class Box, in which you can put fruits. Boxes are sorted by type
of fruit, so you can't put both apples and oranges in one box.
- To store fruits inside the box, you can use an ArrayList.
- Write a getWeight() method that calculates the weight of the box. Set the weight of one
fruit and their number: the weight of an apple is 1.0f, an orange is 1.5f (units of measurement are not
important).
- Inside the Box class, write a Compare method that allows you to compare the current
a box with the one that will be submitted to Compare as a parameter. True if their masses
are equal, False otherwise. You can compare boxes with apples and
oranges.
- Write a method that allows you to pour fruit from the current box into another.
Remember about sorting fruits: you can not pour apples into a box with oranges.
Accordingly, there is no fruit left in the current box
, and objects that were in the first one are thrown into the other.
- Do not forget about the method of adding fruit to the box.
