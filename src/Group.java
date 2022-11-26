import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Group implements  ITestable{
    private int groupId;
    HashSet<Hotel> hotels;

    public Group(int id){
        hotels = new HashSet<Hotel>();
        groupId = id;
    }



    public void addHotelToGroup(Hotel hotel){
        hotels.add(hotel);
    }

    //getters

    public int getGroupId() {
        return groupId;
    }

    public HashSet<Hotel> getHotels() {
        return hotels;
    }

    @Override
    public boolean checkConstraints() {
        return true;
    }
    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }

    public boolean constaint_1(){
        if(hotels!=null){
            Hotel hotel=hotels.stream().findFirst().get();
            HashMap<Service,HotelService> set_service= hotel.getServices();
            for(Hotel hotel_1: this.hotels){
                HashMap<Service,HotelService> set_comper= hotel_1.getServices();
                for(Map.Entry<Service,HotelService> set:set_service.entrySet()){
                    if(!set_comper.containsKey(set.getKey()))
                        return false;
                }
            }
        }
        return true;
    }
}
