import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
}
