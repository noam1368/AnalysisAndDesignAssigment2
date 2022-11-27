import java.util.*;

public class Hotel implements  ITestable{
    private String name;
    private HashMap<Client, ReservationSet> allReservation;
    private HashMap<Service, HotelService> services;
    private HashMap<Integer,Room> rooms;
    private String city;
    private Group group;
    private int rate;



    public Hotel(String city, String name,int rate){
        this.city = city;
        this.name = name;
        this.rate = rate;
        rooms = new HashMap<Integer,Room>();
        allReservation = new HashMap<Client, ReservationSet>();
        services = new HashMap<Service, HotelService>();

    }

    public void addReservationSet(Client client,ReservationSet reservationSet){
        allReservation.put(client,reservationSet);
    }

    public void addService(Service service, HotelService hotelService){
        services.put(service,hotelService);
    }

    public void addRoom(int roomNumber, Room room){
        rooms.put(roomNumber,room);
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public HashMap<Client, ReservationSet> getAllReservation(){return allReservation;}

    public HashMap<Service, HotelService> getServices(){return services;}

    public int getRate(){return rate;}

    @Override
    public boolean checkConstraints() {
        if(!constraint_1())
            return false;
        if(!constraint_2())
            return false;
        if(!constraint_3())
            return false;
        if(!constraint_4())
            return false;
        if(!constraint_5())
            return false;
        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }

    public boolean constraint_1(){
        if(this.rooms!=null) {
            double num_all_rooms = this.rooms.size();
            double counter = 0;
            for (HashMap.Entry<Integer, Room> set : this.rooms.entrySet()) {
                if (set.getValue().getRoomCategory().getType().equals(RoomCategory.RoomType.VIP)) {
                    counter++;
                }
            }
            double counter_divide_num_of_rooms = counter / num_all_rooms;
            if (counter_divide_num_of_rooms > 0.1) {
                return false;
            }
        }
        return true;
    }
     public boolean constraint_2(){
        String las_vegas= "LAS VEGAS";
        if(this.allReservation!=null&&this.city.toLowerCase().equals(las_vegas.toLowerCase())){
            for(HashMap.Entry<Client,ReservationSet> set:this.allReservation.entrySet()){
                if(set.getKey().getAge()<21){
                    return false;
                }
            }
        }
        return true;
     }
    public boolean constraint_3(){
        double counter_reviews= 0;
        double sum_rank=0;
        if(this.getRate()==5&&this.getAllReservation()!=null){
            for(HashMap.Entry<Client,ReservationSet> set : this.getAllReservation().entrySet()){
                for(Reservation reservation:set.getValue().getReservations()){
                    if(reservation.getBookings()!=null&&reservation.getBookings().getReview()!=null){
                        counter_reviews++;
                        sum_rank= sum_rank+reservation.getBookings().getReview().getRank();
                    }
                }
            }
            double average= sum_rank/counter_reviews;
            if(average<=7.5){
                return false;
            }

        }
        return true;
    }
    public boolean constraint_4(){
        if(this.services!=null) {

            for (HashMap.Entry<Service, HotelService> set : this.getServices().entrySet()) {
                int counter_same_name_service = 0;
                String service_name = set.getKey().getServiceName().toLowerCase();
                for (HashMap.Entry<Service, HotelService> set_2 : this.getServices().entrySet()) {
                    if (service_name.equals(set_2.getKey().serviceName.toLowerCase())) {
                        counter_same_name_service++;
                    }
                }
                if (counter_same_name_service > 1) {
                    return false;
                }
            }
        }
       return true;
    }

    public  boolean constraint_5(){
        if(this.getServices()!=null) {
            Calendar calendar = Calendar.getInstance();

            HashMap<Integer, Integer> year_per_profit = new HashMap<Integer, Integer>();
            for (HashMap.Entry<Service, HotelService> set : this.getServices().entrySet()) {
                if (set.getValue().getGivenServices() != null) {
                    for (Booking bookings_of_service : set.getValue().getGivenServices()) {
                        Date date_of_service = bookings_of_service.getDate();
                        calendar.setTime(date_of_service);
                        Integer integer_casting = Integer.valueOf(calendar.get(Calendar.YEAR));
                        if (!year_per_profit.containsKey(integer_casting))
                            year_per_profit.put(integer_casting, set.getValue().getPrice());
                        else {
                            year_per_profit.put(integer_casting, year_per_profit.get(integer_casting) + set.getValue().getPrice());
                        }
                    }


                }
            }
            for (Integer year : year_per_profit.keySet()) {
                if (year_per_profit.containsKey(year - 1) && year_per_profit.get(year - 1) >= year_per_profit.get(year)) {
                    return false;
                }
            }
        }
        return true;

    }
}
