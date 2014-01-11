package war;

import com.google.inject.Inject;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import war.domain.Hotel;
import war.domain.HotelRepository;
import war.domain.Rooms;
import war.domain.RoomsRepository;

import java.util.List;

public class HotelPage extends WebPage {
    @Inject
    HotelRepository hotelRepository;
    @Inject
    RoomsRepository roomsRepository;

    private Label userlogin;
    private Object sessijaname;
    private Label hotelname;
    private Label stars;
    private Label country;
    private Label city;
    private Label address;
    private Label rooms;
    private Label pets;
    private Label foodanddrink;
    private Label activities;
    private Label description;
    private Label services;
    private Label parking;
    private Label telephone;
    private Label wifi;
    private Button book;
    private Button changedata;
    private Button delete;
    private Link registration;
    private Link loginpage;
    private Link homelink;

    public HotelPage (Integer id){

        final Hotel hotel  = hotelRepository.hotelbyId(id);
        Form form  = new Form("form");
        sessijaname = (((MySession) Session.get()).getMyObject());
        registration = new Link("link") {
            @Override
            public void onClick() {
                setResponsePage(new RegistrationPage());
            }
        };
        loginpage = new Link("linkreg") {
            @Override
            public void onClick() {
                setResponsePage(new LoginPage());
            }
        };
        registration.setVisible(false);
        loginpage.setVisible(false);
        userlogin = new Label("userpage", new Model(sessijaname.toString()));
        hotelname = new Label("hotelName", new Model<String>(hotel.getName()));
        stars = new Label("stars", new Model<Integer>(hotel.getStars()));
        country = new Label("country", new Model<String>(hotel.getCountry()));
        city = new Label("city", new Model<String>(hotel.getCity()));
        address = new Label("address", new Model<String>(hotel.getAddress()));
        rooms = new Label("rooms", new Model<Integer>(hotel.getRoomsQuantity()));
        pets = new Label("pets", new Model<Boolean>(hotel.getPets()));
        foodanddrink = new Label("foodAndDrink", new Model<String>(hotel.getFoodAndDrink()));
        activities = new Label("activities", new Model<String>(hotel.getActivities()));
        description = new Label("description", new Model<String>(hotel.getDescription()));
        services = new Label("services", new Model<String>(hotel.getServices()));
        parking = new Label("parking", new Model<Boolean>(hotel.getParking()));

         final List<Rooms> roomes = roomsRepository.loadRooms(hotel);
        form.add(new ListView<Rooms>("roomslist", roomes) {
            @Override
            protected void populateItem(final ListItem<Rooms> item) {
                final Rooms room = item.getModelObject();
                item.add(new Label("roomTypes", new Model<String>(room.getRoomsType())));
                item.add(new Label("price", new Model<Double>(room.getPrice())));
                item.add(new Label("oneprebed", new Model<Integer>(room.getOnePerBedQuant())));
                item.add(new Label("twoprebed", new Model<Integer>(room.getTwoPerBedQuant())));
                item.add(new Label("room", new Model<Integer>(room.getRooms())));
            }
        });

        telephone = new Label("tel", new Model<String>(hotel.getTelephone()));
        wifi = new Label("wifi", new Model<Boolean>(hotel.getWifi()));
        book = new Button("book"){
            @Override
            public void onSubmit() {
                setResponsePage(new BookingPage(hotel.getHotelId()));
            }
        };

        delete = new Button("delete"){
            @Override
            public void onSubmit() {
                Integer hotelid;
                hotelid = hotel.getHotelId();
                Hotel hoteldel = hotelRepository.deleteHotel(hotel.getHotelId());
                List<Rooms> listRooms = roomsRepository.loadRooms(hotel);
                for (Integer i=0 ; i < listRooms.size(); i++){
                    roomsRepository.deleteRooms(listRooms.get(i).roomsID);
                }
                setResponsePage(new AdminPage());
            }
        };
        changedata = new Button("changeData"){
            @Override
            public void onSubmit() {

            }
        };

        Link userLink = new Link("login") {
            public void onClick() {
                setResponsePage(new UserProfilePage());
            }
        };
        if(sessijaname.equals("null")){
            registration.setVisible(true);
            loginpage.setVisible(true);
            userLink.setVisible(false);
            userlogin.setVisible(false);
            book.setVisible(false);
            delete.setVisible(false);
            changedata.setVisible(false);

        }
        else{
            if(sessijaname.equals("admin")){
                book.setVisible(false);
                delete.setVisible(true);
                changedata.setVisible(true);
            }
            else {
                delete.setVisible(false);
                changedata.setVisible(false);
                book.setVisible(true);
            }
        }
        if(sessijaname.toString().equals("admin"))
        {
            homelink = new Link("home") {
                public void onClick() {
                    setResponsePage(new AdminPage());
                }
            };
        }
        else{
            if((sessijaname.toString().equals("null")))
            {
                homelink = new Link("home") {
                    public void onClick() {
                        setResponsePage(new HomePage());
                    }
                };
            }
            else{
            homelink = new Link("home") {
                public void onClick() {
                    setResponsePage(new UserPage());
                }
            };
            }
        }
        userLink.add(userlogin);
        form.add(userLink);
        form.add(hotelname);
        form.add(homelink);
        form.add(stars);
        form.add(country);
        form.add(city);
        form.add(address);
        form.add(rooms);
        form.add(pets);
        form.add(foodanddrink);
        form.add(activities);
        form.add(description);
        form.add(services);
        form.add(parking);
        form.add(telephone);
        form.add(wifi);
        form.add(book);
        form.add(delete);
        form.add(changedata);
        form.add(registration);
        form.add(loginpage);
        add(form);
    }
}
