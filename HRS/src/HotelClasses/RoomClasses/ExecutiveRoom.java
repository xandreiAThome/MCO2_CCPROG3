package HotelClasses.RoomClasses;

public class ExecutiveRoom extends Room {
    private double priceInc;

    public ExecutiveRoom(String name, double basePrice) {
        super(name, basePrice);
        this.priceInc = 0.35;
        double temp = super.getBasePrice();
        super.setPrice(temp * priceInc + temp);
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price * this.priceInc + price);
    }
}
