package HotelClasses.RoomClasses;

public class DeluxeRoom extends Room {
    private double priceInc;

    public DeluxeRoom(String name) {
        super(name);
        this.priceInc = 0.2;
        double temp = super.getPrice();
        super.setPrice(temp * priceInc + temp);
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price * this.priceInc + price);
    }
}
