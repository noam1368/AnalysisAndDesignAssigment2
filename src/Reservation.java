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
        return constraint_1();
    }

    public static boolean checkAllIntancesConstraints(Model model) {
        return true;
    }
    public boolean constraint_1(){
        Hotel hotel_set= this.getReservationSet().getHotel();
        Hotel hotel_booking= this.booking.getRoom().getHotel();
        if(!hotel_set.equals(hotel_booking)){
            return false;
        }
        else{
            return true;
        }
    }


}
    