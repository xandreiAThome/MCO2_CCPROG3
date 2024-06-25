package HotelClasses;

/**
 * Represents a specific day and hour in the month
 */
public class Date {
    private int day;
    private int hour;

    /**
     * Constructs a Date object representing a specific day and hour in the month.
     * 
     * @param day
     * @param hour
     */
    public Date(int day, int hour) {
        if (day > 31 || day < 1) {
            throw new IllegalArgumentException("Incorrect Day format: Month only has 31 days!");
        }

        if (hour > 23 || hour < 0) {
            throw new IllegalArgumentException("Incorrect Hour format: 0000H - 2300H");
        }

        this.day = day;
        this.hour = hour;

    }

    /**
     * Retrieves the day of the month represented by the current instance of Date.
     * 
     * @return day variable of the current instance
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Retrieves the hour of the day represented by the current instance of Date.
     * 
     * @return hour variable of the current instance
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * Prints the current date in a specific format.
     * 
     */
    public void displayDate() {
        System.out.println("Day " + this.day + " - " + this.hour + "H");
    }
}