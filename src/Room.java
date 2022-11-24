import java.util.Date;
import java.util.HashMap;

public class Room implements  ITestable{
    private RoomCategory roomCategory;
    private HashMap<Date,Booking> bookings;
    private int number;
    private Hotel hotel;


    public Room(int number){
        this.number = number;
        bookings = new HashMap<Date,Booking>();
    }

    public void setHotel(Hotel h){ hotel = h; }

    public void asignRoomCategory(RoomCategory roomCategory){
        this.roomCategory = roomCategory;
    }

    public void addBooking(Booking booking, Date date) {
        bookings.put(date, booking);
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public HashMap<Date, Booking> getBookings() {
        return bookings;
    }

    public int getNumber() {
        return number;
    }

    public Hotel getHotel(){ return hotel; }

    @Override
    public boolean checkConstraints() {
        return constraint_1();
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }
    public boolean constraint_1(){
        if(this.getRoomCategory().getType()!= RoomCategory.RoomType.VIP) {
            return true;
        }
            for (HashMap.Entry<Service, HotelService> set :this.getHotel().getServices().entrySet()){
                if(set.getKey().getClass()!=VipService.class){
                    return false;
                }


            }

return true;
    }
}
