/**
*
* @author Alexander Jack Hughes
*
* The class Employee consists of three instant variables (first name, surname and year of joining)
* as well as methods that will allow you to get and set these three instant variables.
*
*There is also a method that will add all this information into a string for easy printing.
*
**/

import java.util.*; // Imports Java library
class Example { // Classes can be called anything - change all class names to the same

// All field variables are listed below privately:
// (Variables are the attributes of an object).
  private String name;

// Constructor called automatically on new instance of class.
// Values of new class are passed below:
  public Example(String name) {
    this.name = name;
  }

// All Methods for class will be listed below:
// (Methods (in general) are the verbs of the object - doing words).

    public void method(){
      System.out.println("Prints this string!");
    }

    public String methodVariable( String x ){
      return "data can be printed too! " + x;
    }

    public String getName(){
      return this.name;
    }

    public int factorial(int n) {
      if (n == 1) {
        return 1;
      } else {
        n = n *  factorial(n -1);
        return n;
      }
    }

    //Given N (greater than 0) print N stars
    public String printTriangle(int n) {
      if (n == 0) {
        return "*";
      } else {
            String p = printTriangle(n - 1);
            p = p + "*";
            System.out.println(p);

            return p;
      }
    }

// Main method of class - any new objects created will be placed here:
    public static void main(String[] args) {

      Example x = new Example("Name"); // Declares & Initializes new class
      System.out.println(x.factorial(10));
      System.out.println(x.printTriangle(10));
      x.method(); // Declare a message on class object
      x.methodVariable("Variable"); // Declare a message that includes a Variable
      x.getName();
    }
}
