import java.awt.print.Book;
import java.util.HashSet;
import java.util.Map;

public class HotelService implements  ITestable{
    private Hotel hotel;
    private Service service;
    private int price;
    private int quality;
    private HashSet<Booking> givenServices;

    public HotelService(int price, int quality){
        givenServices = new HashSet<Booking>();
        this.price = price;
        this.quality = quality;
    }

    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }

    public void assignService(Service service){
        this.service = service;
    }

    public void addBooking(Booking booking){
        givenServices.add(booking);
    }


    public Hotel getHotel() {
        return hotel;
    }

    public Service getService() {
        return service;
    }

    public int getPrice() {
        return price;
    }

    public int getQuality() {
        return quality;
    }

    public HashSet<Booking> getGivenServices() {
        return givenServices;
    }

    @Override
    public boolean checkConstraints() {
        return constraint_1()&&constraint_2();
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }
    public boolean constraint_1(){
        double counter_reviews= 0;
        double sum_rank=0;
        if(this.hotel.getRate()==5){
            for(Booking booking : this.givenServices){
                if(booking.getReview()!=null){
                    counter_reviews++;
                    sum_rank= sum_rank+booking.getReview().getRank();
                }
            }
            double average= sum_rank/counter_reviews;
            if(average<=7.5){
                return false;
            }
            else{
                return true;
            }
        }
        return true;
    }
    public boolean constraint_2(){
        String service_name=this.service.getServiceName();
        int counter_same_name_service=0;
        for(Map.Entry<Service,HotelService> set:this.getHotel().getServices().entrySet()){
            if(set.getKey().getServiceName().equals(service_name)){
                counter_same_name_service++;
            }
        }
        if(counter_same_name_service>1){
            return false;
        }
        else{
            return true;
        }
    }
}
