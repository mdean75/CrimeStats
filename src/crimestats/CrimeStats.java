package crimestats;

import java.io.*;

/**
 * file:    CrimeStats.java
 * author:  Michael DeAngelo
 * date:    February 28, 2018
 * purpose: CrimeStats class processes statistical data from US crime stats by comparing multiple years data
 * for all data points. Opens and reads the crime stats csv file into an object array, then closes the file
 * and performs all other processing from the array.
 */
public class CrimeStats {
    // class fields

    private BufferedReader in = null;
    private USCrimeStats[] crimeArray = new USCrimeStats[21];
    private int length = crimeArray.length;

    /**
     * getter method to get the crimeArray array
     * @return array crimeArray
     */
    public USCrimeStats[] getCrimeArray() {
        return crimeArray;
    }

    /**
     * getter method to get the length of the crimeArray
     * @return int length of the CrimeArray
     */
    public int getLength() {
        return this.length;
    }


    /**
     * This constructor checks that a command line argument was entered and
     * assigns it to a field and calls processFile with the file as a String
     * argument to open process and close the file.
     * @param args command line argument
     */
    public CrimeStats(String[] args) {
        String file = null;

        try{
            file = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: no file specified ");
            System.exit(1);
        }

        /*
        call processFile method to open the crime csv file, read the data and store in an object array,
        then close the file
        */
        processFile(file);

    } // end constructor

    /**
     * This method is called by the class constructor and opens the crime file, then reads and processes
     * it into an object array, and finally closes the file.
     * @param file name of csv passed into the program by command line argument
     */
    private void processFile(String file) {
        // method variables

        // used for the array index
        int index = 0;
        // used to hold the String from reading one line of the file
        String line;

        // process the file
        try {
            // first open the file
            this.in = new BufferedReader(new FileReader(file));

            System.out.println("File opened: " + file);
            System.out.println("Processing file ... ");
            // read the file and process into an object array
            while ((line = in.readLine())!= null) {
                // first row is headers, we do not want this row
                if (index > 0) {
                    // create new USCrimeStats object and store in the array
                    crimeArray[index] = new USCrimeStats(line);
                }
                // increment index
                index++;
            }

        }catch (FileNotFoundException e){
            System.err.println("Error opening file: " + e.getMessage());
            System.exit(1);
        }catch (IOException io) {
            System.err.println("I/O error: " + io.getMessage());
            System.exit(1);
        }finally {
            // close file
            try {
                System.out.println("File processing completed ...");
                System.out.println("Closing file .... " );
                in.close();
                System.out.println("File successfully closed");
                System.out.println();
            } catch (IOException ioClose) {
                System.err.println("Error closing file: " + ioClose);
            }
        }
    }


    /**
     * This method calculates the maximum rate of the selected option and returns the corresponding object
     * @return maximum rate of passed in argument as USCrimeStats object
     */
    public USCrimeStats getMaxRate(String option) {
        // method variables
        double compareOption = 0.0;

        // used to find the highest rate, initialize to -1 since there will be no data with negative values
        double maxRate = -1;
        // index of array object to be returned
        int returnIndex = 0;

        /*
        scan array comparing each robbery rate to find the highest rate. It will compare the previously stored
        minimum with the current and store the least then set the current index to the returnIndex
        */
        try {
            for (int i = 1; i < this.length; i++) {
                switch (option) {
                    case "Violent Crime":
                        compareOption = this.crimeArray[i].getViolentCrimeRate();
                        break;

                    case "Murder":
                        compareOption = this.crimeArray[i].getMurderRate();
                        break;

                    case "Robbery":
                        compareOption = this.crimeArray[i].getRobberyRate();
                        break;

                    case "Rape":
                        compareOption = this.crimeArray[i].getRapeRate();
                        break;

                    case "Assault":
                        compareOption = this.crimeArray[i].getAssaultRate();
                        break;

                    case "Property Crime":
                        compareOption = this.crimeArray[i].getPropertyCrimeRate();
                        break;

                    case "Burglary":
                        compareOption = this.crimeArray[i].getBurglaryRate();
                        break;

                    case "Theft":
                        compareOption = this.crimeArray[i].getTheftRate();
                        break;

                    case "Vehicle":
                        compareOption = this.crimeArray[i].getVehicleTheftRate();
                        break;

                } // end switch

                if (maxRate < compareOption) {
                    maxRate = compareOption;
                    returnIndex = i;
                }// end if
            }// end for
        }catch (NullPointerException n) {
            System.out.println("Error: no data " + n.getMessage());
        }
        return crimeArray[returnIndex];
    } // end getMaxRate


    /**
     * This method calculates the minimum rate of the selected option and returns the corresponding object
     * @return minimum rate of passed in argument as USCrimeStats object
     */
    public USCrimeStats getMinRate(String option) {
        // method variables
        double compareOption = 0.0;

        // used to find the lowest rate, initialize to -1 since there will be no data with negative values
        double minRate = -1;
        // index of array object to be returned
        int returnIndex = 0;

        /*
        scan array comparing each robbery rate to find the lowest rate. First iteration will assign the
        current object to the minRate variable, subsequent iterations will compare the previously stored
        minimum with the current and store the least then set the current index to the returnIndex
        */
        try {
            for (int i = 1; i < this.length; i++) {
                switch (option) {
                    case "Violent Crime":
                        compareOption = this.crimeArray[i].getViolentCrimeRate();
                        break;

                    case "Murder":
                        compareOption = this.crimeArray[i].getMurderRate();
                        break;

                    case "Robbery":
                        compareOption = this.crimeArray[i].getRobberyRate();
                        break;

                    case "Rape":
                        compareOption = this.crimeArray[i].getRapeRate();
                        break;

                    case "Assault":
                        compareOption = this.crimeArray[i].getAssaultRate();
                        break;

                    case "Property Crime":
                        compareOption = this.crimeArray[i].getPropertyCrimeRate();
                        break;

                    case "Burglary":
                        compareOption = this.crimeArray[i].getBurglaryRate();
                        break;

                    case "Theft":
                        compareOption = this.crimeArray[i].getTheftRate();
                        break;

                    case "Vehicle":
                        compareOption = this.crimeArray[i].getVehicleTheftRate();
                        break;

                } // end switch
                if (minRate == -1) {
                    minRate = compareOption;
                } else if (minRate > compareOption) {
                    minRate = compareOption;
                    returnIndex = i;
                } // end if
            } // end for
        }catch (NullPointerException n) {
            System.out.println("Error: no data " + n.getMessage());
        }
        return crimeArray[returnIndex];
    } // end getMinRate

} // end class
