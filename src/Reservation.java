import java.util.Date;
import java.util.ArrayList;

public class Reservation implements  ITestable {
    private int id;
    private RoomCategory roomCategory;
    private Date orderDate;
    private Date requestDate;
    private Booking booking;
    private ReservationSet reservationSet;


    public Reservation(Date ordDate, Date reqDate, int id) {
        this.id = id;
        orderDate = ordDate;
        requestDate = reqDate;
    }

    public void setReservationSet(ReservationSet reservationSet){
        this.reservationSet = reservationSet;
    }


    public void addRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public void addBooking(Booking _booking) {
        booking = _booking;
    }


    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Booking getBookings() {
        return booking;
    }

    public int getId() {
        return id;
    }

    public ReservationSet getReservationSet(){return reservationSet;}

    @Override
    public boolean checkConstraints() {
        if(!constraint_1())
            return false;
        if(!constraint_2())
            return false;

        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model) {
        return true;
    }
    public boolean constraint_1(){
        Hotel hotel_set= this.getReservationSet().getHotel();
        Hotel hotel_booking= this.booking.getRoom().getHotel();
        if(hotel_set.getCity().equals(hotel_booking.getCity())&&hotel_set.getName().equals(hotel_booking.getName())&&hotel_set.getGroup().getGroupId()==hotel_booking.getGroup().getGroupId()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean constraint_2(){
        String las_vegas= new String("LAS VEGAS");
        if(this.reservationSet.getHotel().getCity().toLowerCase().equals(las_vegas.toLowerCase())){
            if(this.reservationSet.getClient().getAge()<21){
                return false;
            }

        }
        return true;
    }


}
    