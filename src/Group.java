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
        if(!Constraint_1())
            return false;
        if(!constaint_2())
            return false;
        return true;
    }
    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }
    public boolean Constraint_1(){
        for(Hotel hotel:this.hotels){
            int counter=0;
            String city_name= hotel.getCity().toLowerCase();
            for(Hotel hotel_2:this.hotels){
                String city_name_comper= hotel_2.getCity().toLowerCase();
                if(city_name.equals(city_name_comper)){
                    counter++;
                }
            }
            if(counter>1){
                return false;
            }
        }
        return true;
    }

    public boolean constaint_2(){
        if(hotels!=null){
            for(Hotel hotel:this.hotels){
                for(HashMap.Entry<Service, HotelService> set:hotel.getServices().entrySet()){
                    for(Hotel hotel_2:this.hotels){
                        if(!hotel_2.getServices().containsKey(set.getKey()))
                            return false;
                    }
                }
            }
            }
        return true;
        }
}
