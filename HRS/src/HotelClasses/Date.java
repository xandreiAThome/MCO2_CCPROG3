package HotelClasses;

public class Date {
    private int day;
    private int hour;
    private int minute;

    public Date(int day, int minute, int hour) {
        if (day > 31 || day < 1) {
            throw new IllegalArgumentException("Incorrect Day format: Month only has 31 days!");
        }

        if (hour > 23 || hour < 1) {
            throw new IllegalArgumentException("Incorrect Hour format: 0000H - 2300H");
        }

        if (minute > 59 || minute < 0) {
            throw new IllegalArgumentException("Incorrect Minute format: 0 - 59 min");
        }

        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

}