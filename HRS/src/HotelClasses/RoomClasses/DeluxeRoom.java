package HotelClasses.RoomClasses;

public class DeluxeRoom extends Room {
    private double priceInc;

    public DeluxeRoom(String name, double basePrice) {
        super(name, basePrice);
        this.priceInc = 0.2;
        double temp = super.getBasePrice();
        super.setPrice(temp * priceInc + temp);
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price * this.priceInc + price);
    }
}
