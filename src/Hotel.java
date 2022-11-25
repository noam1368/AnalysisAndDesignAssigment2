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
        return constraint_1() && constraint_2()&&constraint_3()&&constraint_4();
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }

    public boolean constraint_1(){
        HashSet<Hotel> temp_hash_hotel= this.group.getHotels();
        temp_hash_hotel.remove(this);
        for(Hotel hotel:temp_hash_hotel){
            if(hotel.city.equals(this.city)) {
                return false;
            }

        }
        return true;

    }
    public boolean constraint_2(){
        HashSet<Hotel> temp_hash_hotel = this.group.getHotels();
        temp_hash_hotel.remove(this);
        for(Hotel hotel:temp_hash_hotel){
            for(Map.Entry<Service, HotelService> service_hash:hotel.getServices().entrySet()){
                if(this.getServices().containsKey(service_hash.getKey())){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean constraint_3(){
        double num_all_rooms= this.rooms.size()*0.1;
        double counter =0;
        for (HashMap.Entry<Integer, Room> set : this.rooms.entrySet()){
            if(set.getValue().getRoomCategory().getType()== RoomCategory.RoomType.VIP){
                counter++;
            }
        }
        if(counter>num_all_rooms){
            return false;
        }
        else {
            return true;
        }
    }
    public  boolean constraint_4(){
        Calendar calendar = Calendar.getInstance();

        HashMap<Integer, Integer> year_per_profit = new HashMap<Integer,Integer>();
        for(HashMap.Entry<Service,HotelService> set: this.getServices().entrySet()){
            if(set.getValue().getGivenServices()!=null) {
                for (Booking bookings_of_service : set.getValue().getGivenServices()) {
                    Date date_of_service= bookings_of_service.getDate();
                    calendar.setTime(date_of_service);
                    Integer integer_casting= Integer.valueOf(calendar.get(Calendar.YEAR));

                    year_per_profit.put(integer_casting,year_per_profit.get(integer_casting)+set.getValue().getPrice());
                }

            }
        }
        for(Integer year:year_per_profit.keySet()){
            if(year_per_profit.containsKey(year-1)&&year_per_profit.get(year-1)>=year_per_profit.get(year)){
                return false;
            }
        }
        return true;

    }

}
