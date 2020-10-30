/**
* Calendar Lab (Template Class and Test Cases)
* This is the template class and test cases for the Calendar Lab.
* Written for the Oberoi International School, JVLR 
* in Mumbai, Maharasthra, India.
* 
* @author Aryan Arora
* @version 1.0
*/

public class Calendar {
    public static void main(String[] args) {
        
        //  Tests for calculateDayOfWeek:
        System.out.println(calculateDayOfWeek(9, 7, 1983));     // Output: 6
        System.out.println(calculateDayOfWeek(15, 4, 2016));    // Output: 5
        System.out.println(calculateDayOfWeek(30, 1, 2000));    // Output: 0

        //  Tests for getDayOfWeek:
        System.out.println(getDayOfWeek(9, 7, 1983));                   // Output: "Saturday"
        System.out.println(getDayOfWeek(15, 4, 2016));              // Output: "Friday"
        System.out.println(getDayOfWeek(30, 1, 2000));              // Output: "Sunday"

        //  Tests for printCalendar:
        //   Use the calendar feature of your computer to verify correct output.
        printCalendar(7, 1983);
        printCalendar(4, 2016);
        printCalendar(1, 2000);
    }
    /**
     * 
     * w = (d + ⌊2.6m-0.2⌋+y + ⌊0.25y⌋+⌊0.25c⌋-2c) mod 7
     * 
      */
    /**
    * Calculates the day of the week as a number between 0 and 6.
    * (0=Sunday, 6=Saturday)
    *
    * @param day
    *   Precondition: day is a valid day of the given month.
    * @param month
    *   Precondition:   month is a valid month of the year.
    * @param year
    *   Precondition: year is a valid year after the adoption of the Gregorian 
    *                 Calendar.
    * @return   A number corresponding to the day of the week for the given 
    *           date.
    *           (0=Sunday, 6=Saturday)
    */
    public static int calculateDayOfWeek(int day, int month, int year) {
        //  To be implemented in Activity #1, Exercise 1
        if(month==1||month==2)
        {
            year-=1;//year = year -1
            month+=10;
        }
        else
        {
            month-=2;
        }
        int c = year/100;
        int y = mod(year,100);
        int days = (int)(day +   Math.floor(2.6*month-0.2)+y + Math.floor(0.25*y)+Math.floor(0.25*c)-2*c);
        int DayOfWeek = mod(days ,7);
        
        return DayOfWeek;
    }

    /**
    * Returns the day of the week as a string.
    *
    * @param day
    *   Precondition: day is a valid day of the given month.
    * @param month
    *   Precondition: month is a valid month of the year.
    * @param year
    *   Precondition: year is a valid year after the adoption of the Gregorian 
    *                 Calendar.
    * @return   A string corresponding to the day of the week for the given 
                date.
    */
    public static String getDayOfWeek(int day, int month, int year) {
        //  To be implemented in Activity #1, Exercise 2
        int d = calculateDayOfWeek(day,month,year);
        String days[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        return days[d];
    }
    /**
     * Conditions Required to be a leap year
     * Year Must be divisible by 4
     * If a year is a century year eg. 1700, 1800, 1900, the year must be divisible by 400
       */
    /**
    * Determines whether or not a given year is a leap year.
    *
    * @param year
    *   Precondition: year is a valid year after the adoption of the Gregorian 
    *                 Calendar.
    * @return   true if the given year is a leap year, false otherwise.
    */
    public static boolean isLeapYear(int year) {
        //  To be implemented in Activity #2, Exercise 1
        if(mod(year,4)==0)// Checks if a year is divisible by 4
        {
            if(mod(year,100)==0)// Checks if year is 
            {
                if(mod(year/100,4)==0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }            
        }
        else
        {
            return false;
        }
    }
    /**
    * Prints a visual calendar for the given month and year.
    *
    * @param month
    *   Precondition: month is a valid month is the year.
    * @param year
    *   Precondition: year is a valid year after the adoption of the Gregorian 
    *                 Calendar.
    */
    public static void printCalendar(int month, int year) {
        //  To be implemented in Activity #2, Exercise 2
        String months[] = {"","January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
        int D[]={0,31,28,31,30,31,30,31,31,30,31,30,31};
       
        System.out.println("\t"+months[month]+","+year);
        System.out.println("Mo Tu We Th Fr Sa Su");
        int day = calculateDayOfWeek(1,month,year);
        boolean monthStart = false;
        if(day==0)
        day=7;
        if(isLeapYear(year))
        {
            D[2]=29;
        }
        int ctr=1;
        outer:
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                if(i==0&&!(monthStart))
                {
                    if(j+1==day)
                    {
                        System.out.print("0"+Integer.toString(ctr));
                        monthStart=true;
                        ctr++;
                    }
                    else
                    {
                        System.out.print("  ");
                    }
                }
                else
                {
                    if(ctr/10<1)
                    {
                        System.out.print("0"+Integer.toString(ctr));
                    }
                    else
                    {
                        System.out.print(Integer.toString(ctr));
                    }
                    ctr++;
                }  
                System.out.print(" ");
                if(ctr>=D[month]+1)
                break outer;
                
             
               
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
    * A fix to the Java built in modulus operator (%) to behave more
    * like mathematical modulo.
    * @param n
    * @param m
    * @return returns n mod m as expected mathematically
    */
    public static int mod(int n, int m) {
        return (n % m + m) % m;
    }
}