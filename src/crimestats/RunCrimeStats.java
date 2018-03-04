package crimestats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;


/**
 * file:    RunCrimeStats.java
 * author:  Michael DeAngelo
 * date:    February 28, 2018
 * purpose: This is the driver program for the crime stats project. This wil control execution
 * of the program to read in the crime stats from csv file and store in an object array then
 * display a menu of multiple options for the user to select. Before exiting it will display
 * total runtime and an exit message.
 */
public class RunCrimeStats {

    /**
     * Main method accepts 1 command line argument, instantiates CrimeStats class, calls
     * methods to process the file, displays the menu and tracks and displays total runtime.
     * @param args command line argument
     */
    public static void main(String[] args) {
        // first get the beginning time using Instant class
        Instant start = Instant.now();

        // holds user selected option
        char option;

        // instantiate CrimeStats object so we can process the data
        CrimeStats crime = new CrimeStats(args);

        // call method to display the welcome message
        displayWelcome();

        // loop to display and process the menu until user enters the quit option
        do {
            option = getMenuOption();


            /*
            call method to perform the menu option entered and pass
            the option number and the crime object
            */
            processMenuAction(option, crime);

            /*
            call method to pause execution until user presses enter, allows user to view
            results without scrolling back
            */
            if (Character.toUpperCase(option) != 'Q') {
                pauseMenu();
            }

        } while (Character.toUpperCase(option) != 'Q');

        // user entered option to quit, get the end time
        Instant end = Instant.now();

        // call method to display exit message and runtime duration
        displayExit(start, end);


    }// end main

    /**
     * This method's only purpose is to pause the execution after displaying results to the
     * user before re-displaying the menu
     */
    private static void pauseMenu() {
        System.out.println();
        System.out.println("Hit enter key to continue ...");

        // don't need to process the input so just use a simple Scanner
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }

    /**
     * This method calculates the total runtime and displays the exit message and runtime duration
     * @param start Instant runtime start
     * @param end Instant runtime end
     */
    private static void displayExit(Instant start, Instant end) {
        // get the individual parts of the runtime for display
        long durSec = Duration.between(start, end).toSecondsPart();
        long durMin = Duration.between(start, end).toMinutesPart();
        long durMilli = Duration.between(start, end).toMillisPart();

        System.out.println("Thank you for using the U.S. Crime Stats application");
        // display the runtime duration
        System.out.println("Elapsed time: " + durMin + " Minutes : " + durSec + " Seconds : "
                + durMilli + " Milliseconds");
    }

    /**
     * This method will take the user selected option and CrimeStats object and process the
     * appropriate menu option
     *
     * @param option user selected option
     * @param crime CrimeStats object
     */
    private static void processMenuAction(char option, CrimeStats crime) {
        /*
        method variables
        variables used for calculating the population changes between consecutive years
        initialize year1 and pop1 to -1 as these 2 variables should never be negative
        */

        int year1 = -1;
        int pop1 = -1;

        // these will be assigned values before using
        int year2;
        int pop2;

        // switch statement to process user option
        switch (Character.toUpperCase(option)) {
            case 'A': // population changes between consecutive years
                // length of array
                int length = crime.getLength();

                // use StringBuilder to build the output string in the for loop
                StringBuilder s = new StringBuilder("Consecutive year population changes\n");

                // iterate over array calculating population change between consecutive years and display results
                for (int i = 1; i < length; i++) {
                    // first iteration only set current object year and population to year1 and pop1
                    if (year1 < 0) {
                        year1 = crime.getCrimeArray()[i].getYear();
                        pop1 = crime.getCrimeArray()[i].getPopulation();
                      /*
                      subsequent passes move year1 and pop1 to year2 and pop2, set current object year
                      and poplulation to year1 and pop1, then calculate the change and display results
                      */
                    } else {
                        year2 = year1;
                        pop2 = pop1;
                        year1 = crime.getCrimeArray()[i].getYear();
                        pop1 = crime.getCrimeArray()[i].getPopulation();

                        double change = (((pop1-pop2)/(double)pop2)*100);

                        // append to StringBuilder for final display
                        s.append("Between ");
                        s.append(year2);
                        s.append(" and ");
                        s.append(year1);
                        s.append(" there was a ");
                        s.append(String.format("%.4f", change));
                        s.append("% population change with a total increase of ");
                        s.append((pop1-pop2));
                        s.append("\n");

                    } // end else

                } // end for
                System.out.println(s);
                break;

            case 'B': // highest murder rate year
                // get USCrimeStats object that corresponds with the maximum murder rate
                USCrimeStats maxMurderRate = crime.getMaxRate("Murder");
                System.out.println("The highest murder rate was " + maxMurderRate.getMurderRate() + " in "
                        + maxMurderRate.getYear() + " with a total of " + maxMurderRate.getMurder() + " murders.");
                break;



            case 'C': // highest robbery rate year
                // get USCrimeStats object that corresponds with the maximum robbery rate
                USCrimeStats maxRobberyRate = crime.getMaxRate("Robbery");
                System.out.println("The highest robbery rate was " + maxRobberyRate.getRobberyRate() + " in "
                        + maxRobberyRate.getYear() + " with a total of " + maxRobberyRate.getRobbery() + " robberies.");
                break;



            case 'D': // highest violent crime year
                // get USCrimeStats object that corresponds with the maximum violent crime rate
                USCrimeStats maxVCrimeRate = crime.getMaxRate("Violent Crime");
                System.out.println("The highest violent crime rate was " + maxVCrimeRate.getViolentCrimeRate() + " in "
                        + maxVCrimeRate.getYear() + " with a total of " + maxVCrimeRate.getViolentCrime() + " violent crimes.");
                break;

            case 'E': // highest rape rate year
                // get USCrimeStats object that corresponds with the maximum rape rate
                USCrimeStats maxRapeRate = crime.getMaxRate("Rape");
                System.out.println("The highest rape rate was " + maxRapeRate.getRapeRate() + " in "
                        + maxRapeRate.getYear() + " with a total of " + maxRapeRate.getRape() + " rapes.");
                break;

            case 'F': // highest assault rate year
                // get USCrimeStats object that corresponds with the maximum assault rate
                USCrimeStats maxAssaultRate = crime.getMaxRate("Assault");
                System.out.println("The highest assault rate was " + maxAssaultRate.getAssaultRate() + " in "
                        + maxAssaultRate.getYear() + " with a total of " + maxAssaultRate.getAssault() + " assaults.");
                break;

            case 'G': // highest property crime rate year
                // get USCrimeStats object that corresponds with the maximum property crime rate
                USCrimeStats maxPropertyRate = crime.getMaxRate("Property Crime");
                System.out.println("The highest property crime rate was " + maxPropertyRate.getRobberyRate() + " in "
                        + maxPropertyRate.getYear() + " with a total of " + maxPropertyRate.getPropertyCrime()
                        + " property crimes.");
                break;

            case 'H': // highest burglary rate year
                // get USCrimeStats object that corresponds with the maximum burglary rate
                USCrimeStats maxBurglaryRate = crime.getMaxRate("Burglary");
                System.out.println("The highest burglary rate was " + maxBurglaryRate.getBurglaryRate() + " in "
                        + maxBurglaryRate.getYear() + " with a total of " + maxBurglaryRate.getBurglary() + " assaults.");
                break;

            case 'I': // highest theft rate year
                // get USCrimeStats object that corresponds with the maximum theft rate
                USCrimeStats maxTheftRate = crime.getMaxRate("Theft");
                System.out.println("The highest theft rate was " + maxTheftRate.getTheftRate() + " in "
                        + maxTheftRate.getYear() + " with a total of " + maxTheftRate.getTheft() + " thefts.");
                break;

            case 'J': // highest vehicle theft rate year
                // get USCrimeStats object that corresponds with the maximum vehicle theft rate
                USCrimeStats maxVehicleRate = crime.getMaxRate("Vehicle");
                System.out.println("The highest vehicle theft rate was " + maxVehicleRate.getVehicleTheftRate() + " in "
                        + maxVehicleRate.getYear() + " with a total of " + maxVehicleRate.getVehicleTheft()
                        + " vehicle thefts.");
                break;

            case 'K': // lowest murder rate year
                // get USCrimeStats object that corresponds with the minimum murder rate
                USCrimeStats minMurderRate = crime.getMinRate("Murder");
                System.out.println("The lowest murder rate was " + minMurderRate.getMurderRate() + " in "
                        + minMurderRate.getYear() + " with a total of " + minMurderRate.getMurder() + " murders.");
                break;

            case 'L': // lowest robbery rate year
                // get USCrimeStats object that corresponds with the minimum robbery rate
                USCrimeStats minRobberyRate = crime.getMinRate("Robbery");
                System.out.println("The lowest robbery rate was " + minRobberyRate.getRobberyRate() + " in "
                        + minRobberyRate.getYear() + " with a total of " + minRobberyRate.getRobbery() + " robberies.");
                break;

            case 'M': // lowest violent crime rate year
                // get USCrimeStats object that corresponds with the minimum violent crime rate
                USCrimeStats minVCrimeRate = crime.getMinRate("Violent Crime");
                System.out.println("The lowest violent crime rate was " + minVCrimeRate.getViolentCrimeRate() + " in "
                        + minVCrimeRate.getYear() + " with a total of " + minVCrimeRate.getViolentCrime()
                        + " violent crimes.");
                break;

            case 'N': // lowest rape rate year
                // get USCrimeStats object that corresponds with the minimum rape rate
                USCrimeStats minRapeRate = crime.getMinRate("Rape");
                System.out.println("The lowest rape rate was " + minRapeRate.getRapeRate() + " in "
                        + minRapeRate.getYear() + " with a total of " + minRapeRate.getRape() + " rapes.");
                break;

            case 'O': // lowest assault rate year
                // get USCrimeStats object that corresponds with the minimum assault rate
                USCrimeStats minAssaultRate = crime.getMinRate("Assault");
                System.out.println("The lowest assault rate was " + minAssaultRate.getAssaultRate() + " in "
                        + minAssaultRate.getYear() + " with a total of " + minAssaultRate.getAssault () + " assaults.");
                break;

            case 'P': // lowest property crime rate year
                // get USCrimeStats object that corresponds with the minimum property crime rate
                USCrimeStats minPropertyRate = crime.getMinRate("Property Crime");
                System.out.println("The lowest property crime rate was " + minPropertyRate.getPropertyCrimeRate() + " in "
                        + minPropertyRate.getYear() + " with a total of " + minPropertyRate.getPropertyCrime ()
                        + " property crimes.");
                break;

            case 'R': // lowest burglary rate year
                // get USCrimeStats object that corresponds with the minimum burglary rate
                USCrimeStats minBurglaryRate = crime.getMinRate("Burglary");
                System.out.println("The lowest burglary rate was " + minBurglaryRate.getBurglaryRate() + " in "
                        + minBurglaryRate.getYear() + " with a total of " + minBurglaryRate.getBurglary ()
                        + " burglaries.");
                break;

            case 'S': // lowest theft rate year
                // get USCrimeStats object that corresponds with the minimum theft rate
                USCrimeStats minTheftRate = crime.getMinRate("Theft");
                System.out.println("The lowest theft rate was " + minTheftRate.getTheftRate() + " in "
                        + minTheftRate.getYear() + " with a total of " + minTheftRate.getTheft () + " thefts.");
                break;

            case 'T': // lowest vehicle theft rate year
                // get USCrimeStats object that corresponds with the minimum vehicle theft rate
                USCrimeStats minVehicleRate = crime.getMinRate("Vehicle");
                System.out.println("The lowest vehicle theft rate was " + minVehicleRate.getVehicleTheftRate() + " in "
                        + minVehicleRate.getYear() + " with a total of " + minVehicleRate.getVehicleTheft ()
                        + " vehicle thefts.");
                break;

            case 'Q': // quit
                break;

            default: // invalid option selected
                System.out.println("Invalid option selected");
                System.out.println("Please Select a valid menu option");
                break;
        } // end switch
    } // end processMenuAction

    /**
     * This method displays the welcome message
     */
    private static void displayWelcome() {
        System.out.println("**********************************************************************");
        System.out.println("\t\t\t Welcome to the US Crime Stats Application");
        System.out.println("**********************************************************************");
    }

    /**
     * This method calls the displayMenu method and returns the user selected option
     * @return char user selected option
     */
    private static char getMenuOption() {
        displayMenu();
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            return (char) input.read();
        }catch (IOException e) {
            System.out.println("Error reading input " + e.getMessage());
        } // end try .. catch

        return 0;
    } // end getMenuOption

    /**
     * This method displays the user menu and prompts the user for a selection
     */
    private static void displayMenu() {
        System.out.println();
        System.out.println("Please select an option or 'Q' to quit");
        System.out.println();
        System.out.println("A.\tPopulation growth percentage for each consecutive year from 1994 - 2013");
        System.out.println();
        System.out.println("**********\tHIGHEST RATE STATISTICS\t**********");
        System.out.println();
        System.out.println("B.\tWhat year was the murder rate the highest?");
        System.out.println("C.\tWhat year was the robbery rate the highest?");
        System.out.println("D.\tWhat year was the violent crime rate the highest?");
        System.out.println("E.\tWhat year was the rape rate the highest?");
        System.out.println("F.\tWhat year was the assault rate the highest?");
        System.out.println("G.\tWhat year was the property crime rate the highest?");
        System.out.println("H.\tWhat year was the burglary rate the highest?");
        System.out.println("I.\tWhat year was the theft rate the highest?");
        System.out.println("J.\tWhat year was the vehicle theft rate the highest?");
        System.out.println();
        System.out.println("**********\tLOWEST RATE STATISTICS\t**********");
        System.out.println();
        System.out.println("K.\tWhat year was the murder rate the lowest?");
        System.out.println("L.\tWhat year was the robbery rate the lowest?");
        System.out.println("M.\tWhat year was the violent crime rate the lowest?");
        System.out.println("N.\tWhat year was the rape rate the lowest?");
        System.out.println("O.\tWhat year was the assault rate the lowest?");
        System.out.println("P.\tWhat year was the property crime rate the lowest?");
        System.out.println("R.\tWhat year was the burglary rate the lowest?");
        System.out.println("S.\tWhat year was the theft rate the lowest?");
        System.out.println("T.\tWhat year was the vehicle theft rate the lowest?");
        System.out.println();
        System.out.println("Q.\tQuit");
        System.out.println();
        System.out.println("Enter your selection: ");
        System.out.println();

    } // end displayMenu

} // end class
