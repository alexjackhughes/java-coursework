/**
* 
* @author Alexander Jack Hughes
* 
* This code answers the question for WS2 - EX4.
* 
* The employee consists of 3 variables; Name, hourly Salary and number of hours.
* 
* Basic methods get and set these three variables.
* 
* toString adds data of employee into one string. Methods can also calculate monthly salary and
* a hypothetical increase in salary.
* 
**/

class Employee { // Name of class

// All Instant Variables are listed below privately:
  private String name;
  private double hourlySalary;
  private int numberOfHours;

// Constructor for Employee; name, hourly salary and number of hours worked:
  public Employee(String TheName, double TheHourlySalary, int TheNumberOfHours) {
    this.name = TheName;
    this.hourlySalary = TheHourlySalary;
    this.numberOfHours = TheNumberOfHours;
  }

// All Methods for class will be listed below:

    public String getName() { // Get name of Employee
      return(name);
    }

    public double getHourlySalary() { // Get hourly salary of Employee
      return(hourlySalary);
    }
    
    public int getNumberOfHours() { // Get number of hours worked for Employee
        return(numberOfHours);
     }
    
    public void setName( String x ) { // Set name of Employee
	    name = x;
	 }
    
    public void setHourlySalary( double h ) { // Set hourly salary of Employee
	    hourlySalary = h;
	}
    
    public void setNumberOfHours( int n ) { // Set number of hours worked for Employee
	    numberOfHours = n;
	}

    public String toString() { // Adds all data of Employee into string, and returns string
      return("Employee Information - Name: " + name + " | Hourly Salary: "
      + hourlySalary + " | Number of Hours: " + numberOfHours);
    }

    public double monthlySalary() { // Multiplies hourly salary and hours worked == monthly salary
      double monthlySalary = hourlySalary * (double) numberOfHours;
      return(monthlySalary);
    }

 // Calculates Employee salary increase based off an inputed percentage
    public double increaseSalary( double percentage ) { 
      double percentageIncrease = percentage / 100.00000;
      double finalSalary = hourlySalary + (hourlySalary * percentageIncrease);
      hourlySalary = finalSalary;
      return(finalSalary);
    }
}