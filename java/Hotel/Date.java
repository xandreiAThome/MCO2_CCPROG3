public class Date {
    private int day;
    private int hour;
    private int minute;

    public Date(int day, int minute, int hour) {
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