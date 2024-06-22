package HotelClasses;

/**
 * Represents a specific day and hour in the month
 */
public class Date {
    private int day;
    private int hour;

    /**
     * Constructs the Date object
     * 
     * @param day
     * @param hour
     */
    public Date(int day, int hour) {
        if (day > 31 || day < 1) {
            throw new IllegalArgumentException("Incorrect Day format: Month only has 31 days!");
        }

        if (hour > 23 || hour < 1) {
            throw new IllegalArgumentException("Incorrect Hour format: 0000H - 2300H");
        }

        this.day = day;
        this.hour = hour;

    }

    /**
     * 
     * @return day variable of the current instance
     */
    public int getDay() {
        return this.day;
    }

    /**
     * 
     * @return hour variable of the current instance
     */
    public int getHour() {
        return this.hour;
    }
}