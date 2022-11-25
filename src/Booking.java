import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking implements  ITestable{
    private Date date;
    private Room room;
    private ArrayList<HotelService> services;
    private Reservation reservation;
    private Review review;


    public Booking(Date a_date, Room a_room){
        date = a_date;
        room = a_room;
        services = new ArrayList<HotelService>();
    }

    public void addService(HotelService s){
        services.add(s);
    }

    public void addReview(Review a_review) {review  = a_review; }

    public void addReservation(Reservation r){
        reservation = r;
    }

    public void assignRoom(Room room){
        this.room = room;
    }


    // getters

    public Date getDate() {
        return date;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Review getReview() {
        return review;
    }


    @Override
    public boolean checkConstraints() {
        return constraint_1()&& constraint_2();
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }
    public boolean constraint_1(){
        RoomCategory.RoomType type_room =this.reservation.getRoomCategory().getType();
        if((this.room.getRoomCategory().getType()== RoomCategory.RoomType.BASIC)&&(type_room== RoomCategory.RoomType.VIP)||
        type_room== RoomCategory.RoomType.SUITE){
            return false;
        }
        if(this.room.getRoomCategory().getType()==RoomCategory.RoomType.SUITE&&type_room==RoomCategory.RoomType.VIP){
            return false;
        }
        return true;


    }
    public boolean constraint_2(){
        for(HotelService hotel_service:this.getServices()){
            Hotel hotel_compare_1= hotel_service.getHotel();
            if(this.getRoom()!=null){
                Hotel hotel_compare_2=this.getRoom().getHotel();
                if(!hotel_compare_1.equals(hotel_compare_2)){
                    return false;
                }
            }
        }
        return true;
    }
}
