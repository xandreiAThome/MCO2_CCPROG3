package HotelClasses;

public class Date {
    private int day;
    private int hour;

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

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public boolean isDateEarlier(Date date2) {
        if (this.day < date2.getDay()) {
            return true;
        } else if (this.day == date2.getDay() && this.hour < date2.getHour()) {
            return true;
        }

        return false;
    }

}