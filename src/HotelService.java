import java.awt.print.Book;
import java.util.HashMap;
import java.util.HashSet;

public class HotelService implements  ITestable{
    private Hotel hotel;
    private Service service;
    private int price;
    private int quality;
    private HashSet<Booking> givenServices;

    public HotelService(int price, int quality){
        givenServices = new HashSet<Booking>();
        this.price = price;
        this.quality = quality;
    }

    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }

    public void assignService(Service service){
        this.service = service;
    }

    public void addBooking(Booking booking){
        givenServices.add(booking);
    }


    public Hotel getHotel() {
        return hotel;
    }

    public Service getService() {
        return service;
    }

    public int getPrice() {
        return price;
    }

    public int getQuality() {
        return quality;
    }

    public HashSet<Booking> getGivenServices() {
        return givenServices;
    }

    @Override
    public boolean checkConstraints() {
        if(!constraint_1())
            return false;


        return true;
    }


    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }

    public boolean constraint_1(){
        HashSet<Hotel> hotel_set= this.hotel.getGroup().getHotels();
        for(Hotel hotel:hotel_set){
            if(!hotel.getServices().containsKey(this.getService())){
                return false;
            }
        }
        return true;
    }

}
