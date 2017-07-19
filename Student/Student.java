/**
*
* @author Alexander Jack Hughes
*
* The student class consists of 4 variables; Name, date of birth, student ID, and degree programme.
*
* The methods allow you to get and set these variables.
* There is also a toString method that puts all 4 variables into one string (for easy printing/records etc).
*
**/

class Student { // Name of the class

	// All Instant variables of Student are listed below privately:
    private String name;
    private String dateOfBirth;
    private String studentID;
    private String degreeProgramme;

   //Constructor for Student; name, date of birth, student ID, Degree programme.
    public Student(String TheName, String TheDateOfBirth, String TheStudentID, String TheDegreeProgramme) {

      name = TheName;
      dateOfBirth = TheDateOfBirth;
      studentID = TheStudentID;
      degreeProgramme = TheDegreeProgramme;

    }

  // All Methods for the class are listed below:
      public String getName(){ // get the name variable (String) of student
        return(name);
      }

      public String getDateOfBirth(){ // get date of birth variable (String) of student
        return(dateOfBirth);
      }

      public String getStudentID(){ // get student ID (String) of student
        return(studentID);
      }

      public String getDegreeProgramme(){ // get the degree programme (String) of student
        return(degreeProgramme);
      }

      public String toString(){ // Adds all student data into one string:
        return("[" + name + ", " + dateOfBirth + ", ID: " + studentID + ", " + degreeProgramme + "]");
      }


  }
