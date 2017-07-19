import java.util.*; // `Imports the Java library so that anything works`
public class Ex5 { // Should always be name of the program
    public static void main(String[] args) {

      Clock CurrentTime = new Clock(11, 49); // Initializes the class at the right time
      CurrentTime.hourCheck();
      CurrentTime.degreeCalculator();
      CurrentTime.timeCheck();

    }
}

class Clock { // Classes can be called anything

  private double hour, minute, hourDegree, minuteDegree, angleOfHands;

  public Clock(double TheHour, double TheMinute) {
// variables that belong to the clock live below, namely its current hour and minute hands
    hour = TheHour;
    minute = TheMinute;
  }

// All Methods for the Clock are listed below:
    public void hourCheck() { // Checks whether the hour hand is 12 or 24, and converts to AM

      if ((hour <= 12) & (hour >= 1) & (minute >= 0) & (minute <= 59)) {
          hour = hour;
      } else if ((hour < 24) & (hour >= 13) & (minute >= 0) & (minute <= 59)) {
          hour = hour - 12;
      }

    }

    public void degreeCalculator() { // Calculates the angle of the hands
      hourDegree = (0.5 * ((hour * 60) + minute)); // hourHand goes round the clock 0.5 degrees a minute
      minuteDegree = minute * 6; // minute hand goes round the clock 6 degrees a minute

      // 360 degrees in a clock + (hour degree (rounded down) - minute degree) == x
      // remainder between x and total clock degrees == angle difference
      angleOfHands = (360 + (Math.floor(hourDegree) - minuteDegree)) % 360;
    }

// if the hour and minute hand are (1-24)(0-59), returns answer - if not returns error message
    public void timeCheck(){

      if ((hour <= 24) & (hour >= 0) & (minute >= 0) & (minute <= 59)) {
          System.out.println("The angle between the hour and minute hand is: "
          + Math.floor(angleOfHands) + " degrees.");

      } else if ((hour > 24) || (hour < 0) || (minute < 0) ||(minute >= 60)) {
          System.out.println("Sorry, you've made a mistake!");
          System.out.println("Please input an hour number of 1-24, and a minute number of 0-59.");

      }
    }
}
