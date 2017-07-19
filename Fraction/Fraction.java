/**
*
* @author Alexander Jack Hughes
*
* The Fraction class consists of variables; numerator and demoniator.
*
* Basic Methods allow you to get and set variables, as well as add data into a string.
*
* You can also add, multiply and less fractions.
*
**/

class Fraction { // Name of class

// All Instant variables are listed below privately:
  private int numerator;
  private int denominator;

// Constructor for Fraction; numerator of fraction, denominator of fraction
  public Fraction(int TheNumerator, int TheDenominator) {
    numerator = TheNumerator;
    denominator = TheDenominator;
  }

// All Methods for class will be listed below:

    public int getNumerator() { //Get numerator of Fraction
      return(numerator);
    }

    public int getDenominator() { // Get Denominator of Fraction
      return(denominator);
    }

    public String toString() { // Adds both variables of Fraction into a string, returns string
      return(numerator + "/" + denominator);
    }

    public Fraction add( Fraction summand ) { // Add two Fractions together
      int x = ((this.getNumerator() * summand.getDenominator()) + (summand.getNumerator() * this.getDenominator()));
      int y = (this.getDenominator() * summand.getDenominator());
      Fraction f3 = new Fraction(x, y);
        return(f3);
    }

    public Fraction multiply( Fraction factor ) { // Multiply two Fractions together
      int a = (this.getNumerator() * factor.getNumerator());
      int b = (this.getDenominator() * factor.getDenominator());
      Fraction f4 = new Fraction(a, b);
        return(f4);
    }

    // Find out whether a Fraction is bigger or smaller than another Fraction
    public boolean less( Fraction comp ) {

      int c = comp.getNumerator() * this.getDenominator();
      int d = comp.getDenominator() * this.getDenominator();

      int e = this.getNumerator() * comp.getDenominator();
      int f = this.getDenominator() * comp.getDenominator();

      Fraction f6 = new Fraction(c, d);
      Fraction f5 = new Fraction(e, f);

      int firstNumerator = f5.getNumerator();
      int secondNumerator = f6.getNumerator();

      boolean answer = firstNumerator < secondNumerator;

      return(answer);

// Is code below needed? answer already returns boolean
    }
}
