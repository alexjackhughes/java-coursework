/**
* 
* @author Alexander Jack Hughes
* 
* This code answers the question for WS2 - EX3.
* 
* The weight class converts a weight given in kilograms into Kilograms, pounds and Ounces.
* 
**/

class Weight { // Name of class

// All Instant variables are listed below privately:
  private double kg;

//Constructor for Weight; weight given in kilograms: 
  public Weight(double TheKG) {
    kg = TheKG;
  }

// All Methods for class will be listed below:
    public double getPounds() { // Converts the weight given in kilograms into pounds
      return(kg / 0.45359237);
    }

    public double getKilograms() { //Converts the weight given in kilograms into kilograms
      return(kg);
    }

    public double getOunces() { // Converts the weight given in kilograms into ounces
      return((kg / 0.45359237) * 16.0);
    }
}