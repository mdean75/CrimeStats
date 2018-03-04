package crimestats;

/**
 * file:    USCrimeStats.java
 * author:  Michael DeAngelo
 * date:    February 28, 2018
 * purpose: USCrimeStats class contains the data for the US Crime Stats
 */

public class USCrimeStats {
    // class fields

    private int year;
    private int population;
    private int violentCrime;
    private double violentCrimeRate;
    private int murder;
    private double murderRate;
    private int rape;
    private double rapeRate;
    private int robbery;
    private double robberyRate;
    private int assault;
    private double assaultRate;
    private int propertyCrime;
    private double propertyCrimeRate;
    private int burglary;
    private double burglaryRate;
    private int theft;
    private double theftRate;
    private int vehicleTheft;
    private double vehicleTheftRate;

    /**
     * constructor takes String argument containing crime data
     * and splits it into class fields.
     * @param data String containing one row of a csv file
     */
    public USCrimeStats(String data){
        // split String data into array
        String[] crimeArray = data.split(",");

        // call method to set set class fields from array
        parseArray(crimeArray);

    }

    // getter methods

    public int getYear() {
        return year;
    }

    public int getPopulation() {
        return population;
    }

    public int getViolentCrime() {
        return violentCrime;
    }

    public double getViolentCrimeRate() {
        return violentCrimeRate;
    }

    public int getMurder() {
        return murder;
    }

    public double getMurderRate() {
        return murderRate;
    }

    public int getRape() {
        return rape;
    }

    public double getRapeRate() {
        return rapeRate;
    }

    public int getRobbery() {
        return robbery;
    }

    public double getRobberyRate() {
        return robberyRate;
    }

    public int getAssault() {
        return assault;
    }

    public double getAssaultRate() {
        return assaultRate;
    }

    public int getPropertyCrime() {
        return propertyCrime;
    }

    public double getPropertyCrimeRate() {
        return propertyCrimeRate;
    }

    public int getBurglary() {
        return burglary;
    }

    public double getBurglaryRate() {
        return burglaryRate;
    }

    public int getTheft() {
        return theft;
    }

    public double getTheftRate() {
        return theftRate;
    }

    public int getVehicleTheft() {
        return vehicleTheft;
    }

    public double getVehicleTheftRate() {
        return vehicleTheftRate;
    }

    /**
     * parseArray method assigns crime data from array (from crime data file)
     * to class fields for further processing
     * @param crimeArray array of crime data
     */
    private void parseArray(String[] crimeArray) {
        this.year = Integer.parseInt(crimeArray[0]);
        this.population = Integer.parseInt(crimeArray[1]);
        this.violentCrime = Integer.parseInt(crimeArray[2]);
        this.violentCrimeRate = Double.parseDouble(crimeArray[3]);
        this.murder = Integer.parseInt(crimeArray[4]);
        this.murderRate = Double.parseDouble(crimeArray[5]);
        this.rape = Integer.parseInt(crimeArray[6]);
        this.rapeRate = Double.parseDouble(crimeArray[7]);
        this.robbery = Integer.parseInt(crimeArray[8]);
        this.robberyRate = Double.parseDouble(crimeArray[9]);
        this.assault = Integer.parseInt(crimeArray[10]);
        this.assaultRate = Double.parseDouble(crimeArray[11]);
        this.propertyCrime = Integer.parseInt(crimeArray[12]);
        this.propertyCrimeRate = Double.parseDouble(crimeArray[13]);
        this.burglary = Integer.parseInt(crimeArray[14]);
        this.burglaryRate = Double.parseDouble(crimeArray[15]);
        this.theft = Integer.parseInt(crimeArray[16]);
        this.theftRate = Double.parseDouble(crimeArray[17]);
        this.vehicleTheft = Integer.parseInt(crimeArray[18]);
        this.vehicleTheftRate = Double.parseDouble(crimeArray[19]);

    } // end parseArray
} // end class
