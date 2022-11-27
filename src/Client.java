import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Client implements  ITestable {
    private HashMap<Hotel,ReservationSet> reservationsHistory;
    private int id;
    private int age;
    private String name;
    private String city;

    public HashMap<Hotel, ReservationSet> getReservationsHistory() {
        return reservationsHistory;
    }

    public Client(int a_id, int a_age, String a_name, String a_city){
        reservationsHistory = new HashMap<Hotel,ReservationSet>();
        id = a_id;
        age = a_age;
        city = a_city;
        name = a_name;
    }

    public void addReservationSet(Hotel hotel, ReservationSet reset){
        reservationsHistory.put(hotel,reset);
    }

    // getters

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean checkConstraints() {
        if(!constraint_1())
            return false;
        if(!constraint_2())
            return false;
        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }


    public boolean constraint_1(){
        for (HashMap.Entry<Hotel, ReservationSet> set :
                this.reservationsHistory.entrySet()){
            ArrayList<Booking> list_books= new ArrayList<>();
            int counter= 0;
            if (set.getValue().getReservations().size()>=5){
                for( Reservation reservation : set.getValue().getReservations()){
                    if(reservation.getBookings()!=null) {
                        list_books.add(reservation.getBookings());
                    }
                }
                if(list_books.size()>=5){
                    for(Booking book:list_books){
                        if (book.getRoom().getRoomCategory().getType().equals(RoomCategory.RoomType.VIP)) {
                            counter++;
                        }
                    }
                    if(counter==0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean constraint_2(){
        if(this.reservationsHistory!=null) {
            for (HashMap.Entry<Hotel, ReservationSet> set : this.reservationsHistory.entrySet()) {
                for (Reservation reservation : set.getValue().getReservations()) {
                    if (reservation.getBookings() != null) {
                        for (HotelService service_kind : reservation.getBookings().getServices())
                            if ((service_kind.getService() instanceof VipService) && reservation.getBookings().getReview() == null) {
                                return false;
                            }
                    }
                }

            }
        }
        return true;
    }
}
