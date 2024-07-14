package HotelClasses;

public class DateModifier {

    private double[] priceRateList;
    
    public DateModifier(){
        priceRateList = new double[31];

        for(int i = 0; i < 31; i++){
            priceRateList[i] = 1.0;
        }

    }

    public void setPriceRate(int day, double rate){
        if (day >= 1 && day <= 31){
            this.priceRateList[day] = rate;
        } else {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }
    }

    public double getPriceRate(int day){
        if (day >= 1 && day <= 31){
            return this.priceRateList[day-1];
        } else {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }
    }
}
