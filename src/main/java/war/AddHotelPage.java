package war;


import com.google.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import war.domain.Hotel;
import war.domain.HotelRepository;
import war.domain.Rooms;
import war.domain.RoomsRepository;

import java.util.Arrays;
import java.util.List;


public class AddHotelPage extends WebPage {
    @Inject
    HotelRepository hotelRepository;
    @Inject RoomsRepository roomsRepository;

    public TextField name;
    public TextField country;
    public TextField city;
    public TextField address;
    public TextField telephone;
    public DropDownChoice stars;
    public TextField roomsq;
    public TextArea description;
    public TextArea services;
    public TextArea fodanddrink;
    public TextArea activities;
    public TextField roomtype;
    public TextField roomquant;
    public TextField price;
    public TextField rooms;
    public TextField oneperbed;
    public TextField twoperbed;
    public Button more;
    public Button create;
    private Integer one=1;
    private Radio parking1;
    private Radio parking2;
    private Label errorrooms;
    private Label createhotel;
    private Integer hotelid;
    private Label addroom;
    public String namev;
    public Label roomscreate;


    private static final List<Integer> star = Arrays.asList(
            1, 2, 3, 4, 5);

    public  AddHotelPage (){
        Form form = new Form("form");
        name = new TextField("name", new Model(""));
        country = new TextField("country", new Model(""));
        city = new TextField("city", new Model(""));
        address = new TextField("address", new Model(""));
        telephone = new TextField("tel", new Model(""));
        stars = new DropDownChoice("stars", new PropertyModel<Integer>(this, "one"), star);
        roomsq = new TextField("roomsQuantity", new Model(""));
        errorrooms = new Label("soomsqerror","Only numbers");
        errorrooms.setVisible(false);
        createhotel = new Label("hotelcreate","Hotel is created");
        createhotel.setVisible(false);
        addroom = new Label("addroom","Please enter correct data");
        addroom.setVisible(false);
        roomscreate = new Label("roomscreate","Rooms is created");
        roomscreate.setVisible(false);
        IModel<String> selected = new Model<String>();
        final RadioGroup group = new RadioGroup("group", selected);
        form.add(group);
        parking1 = new Radio("parking1", new Model<String>("1"));
        parking2 = new Radio("parking2", new Model<String>("0"));
        group.add(parking1);
        group.add(parking2);

        IModel<String> selected2 = new Model<String>();
        final RadioGroup group2 = new RadioGroup("group2", selected2);
        form.add(group2);
        group2.add(new Radio("wifi1", new Model<String>("1")));
        group2.add(new Radio("wifi2", new Model<String>("0")));

        IModel<String> selected3 = new Model<String>();
        final RadioGroup group3 = new RadioGroup("group3", selected3);
        form.add(group3);
        group3.add(new Radio("pets1", new Model<String>("1")));
        group3.add(new Radio("pets2", new Model<String>("0")));

        description = new TextArea("description", new Model(""));
        services = new TextArea("services", new Model(""));
        fodanddrink = new TextArea("foodAndDrink", new Model(""));
        activities = new TextArea("activities", new Model(""));

        roomtype = new TextField("roomType", new Model(""));
        roomtype.setEnabled(false);
        roomquant = new TextField("roomsQuant", new Model(""));
        roomquant.setEnabled(false);
        price = new TextField("price", new Model(""));
        price.setEnabled(false);
        rooms = new TextField("rooms", new Model(""));
        rooms.setEnabled(false);
        oneperbed = new TextField("onePerBedQuant", new Model(""));
        oneperbed.setEnabled(false);
        twoperbed = new TextField("twoPerBedQuant", new Model(""));
        twoperbed.setEnabled(false);

        more = new Button("more"){
            @Override
            public void onSubmit(){
                roomscreate.setVisible(false);
                createhotel.setVisible(false);
                String roomtupev = (String) roomtype.getModelObject();
                String roomquantv =  roomquant.getModelObject().toString();
                Boolean st = roomquantv.matches("^[0-9]+$");
                if(!st){
                    addroom.setVisible(true);
                    return;
                }
                Integer str = Integer.parseInt(roomquantv);
                addroom.setVisible(false);
                String pricev =  price.getModelObject().toString();
                Double pr = Double.parseDouble(pricev);
                String roomsv =  rooms.getModelObject().toString();
                Boolean rm = roomsv.matches("^[0-9]+$");
                if(!rm){
                    addroom.setVisible(true);
                    return;
                }
                Integer rom = Integer.parseInt(roomsv);
                addroom.setVisible(false);
                String oneperbedv = oneperbed.getModelObject().toString();
                Boolean onep = oneperbedv.matches("^[0-9]+$");
                if(!onep){
                    addroom.setVisible(true);
                    return;
                }
                Integer oneper = Integer.parseInt(oneperbedv);
                addroom.setVisible(false);
                String twoperv = twoperbed.getModelObject().toString();
                Boolean twop = twoperv.matches("^[0-9]+$");
                if(!twop){
                    addroom.setVisible(true);
                    return;
                }
                Integer twobed = Integer.parseInt(twoperv);
                addroom.setVisible(false);
                Hotel hoteli = hotelRepository.loadbyname(namev);

                Rooms room = roomsRepository.addRooms(hoteli, roomtupev, str, pr, rom, oneper, twobed);
                roomscreate.setVisible(true);
                roomtype.setModelObject("");
                roomquant.setModelObject("");
                price.setModelObject("");
                rooms.setModelObject("");
                oneperbed.setModelObject("");
                twoperbed.setModelObject("");

            }
        };
        more.setEnabled(false);

        create = new Button("create"){
            @Override
            public void onSubmit(){
                namev = (String) name.getModelObject();
                String countryv = (String) country.getModelObject();
                String cityv = (String) city.getModelObject();
                String addressv = (String) address.getModelObject();
                String telephonev = (String) telephone.getModelObject();
                String starsv =  stars.getModelObject().toString();
                Integer str = Integer.parseInt(starsv);
                String roomsqv =  roomsq.getModelObject().toString();
                Boolean ro = roomsqv.matches("^[0-9]+$");

                if(!ro){
                    errorrooms.setVisible(true);
                    return;
                }
                Integer rom = Integer.parseInt(roomsqv);
                errorrooms.setVisible(false);
                String  parkingv =  group.getModelObject().toString();
                Boolean pr = parkingv.equals("1");
                String wifiv =  group2.getModelObject().toString();
                Boolean wi = false;
                if(wifiv.equals("1")){
                    wi = true;
                }
                String petsv = group3.getModelObject().toString();
                Boolean pe = false;
                if(petsv.equals("1")){
                    pe = true;
                }
                String descriptionv = (String) description.getModelObject();
                String servicesv = (String) services.getModelObject();
                String foodanddrinkv = (String) fodanddrink.getModelObject();
                String activitiesv = (String) activities.getModelObject();
                name.setModelObject(namev);
                country.setModelObject(countryv);
                city.setModelObject(cityv);
                address.setModelObject(addressv);
                telephone.setModelObject(telephonev);
                stars.setModelObject(starsv);
                roomsq.setModelObject(roomsqv);
                group.setModelObject(parkingv);
                group2.setModelObject(wifiv);
                group3.setModelObject(petsv);
                description.setModelObject(descriptionv);
                services.setModelObject(servicesv);
                fodanddrink.setModelObject(foodanddrinkv);
                activities.setModelObject(activitiesv);

                Hotel hotel  = hotelRepository.addHotel(namev, countryv, cityv, addressv, telephonev, str, rom, pr,
                        wi, pe, descriptionv, servicesv, foodanddrinkv, activitiesv);
                Hotel hoteli = hotelRepository.loadbyname(namev);
                hotelid = hoteli.getHotelId();
                createhotel.setVisible(true);
                name.setEnabled(false);
                country.setEnabled(false);
                city.setEnabled(false);
                address.setEnabled(false);
                telephone.setEnabled(false);
                stars.setEnabled(false);
                roomsq.setEnabled(false);
                group.setEnabled(false);
                group2.setEnabled(false);
                group3.setEnabled(false);
                description.setEnabled(false);
                services.setEnabled(false);
                fodanddrink.setEnabled(false);
                activities.setEnabled(false);
                create.setEnabled(false);
                roomtype.setEnabled(true);
                roomquant.setEnabled(true);
                price.setEnabled(true);
                rooms.setEnabled(true);
                oneperbed.setEnabled(true);
                twoperbed.setEnabled(true);
                more.setEnabled(true);
            }
        };

        form.add(new Link("login") {
            public void onClick() {
                setResponsePage(new UserProfilePage());

            }
        });
        form.add(new Link("home") {
            public void onClick() {
                setResponsePage(new AdminPage());
            }
        });
        form.add(errorrooms);
        form.add(name);
        form.add(country);
        form.add(city);
        form.add(address);
        form.add(telephone);
        form.add(stars);
        form.add(roomsq);
        form.add(services);
        form.add(description);
        form.add(fodanddrink);
        form.add(activities);
        form.add(roomtype);
        form.add(roomquant);
        form.add(price);
        form.add(rooms);
        form.add(oneperbed);
        form.add(twoperbed);
        form.add(create);
        form.add(more);
        form.add(roomscreate);
        form.add(createhotel);
        form.add(addroom);
        add(form);
    }
}
