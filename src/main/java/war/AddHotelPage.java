package war;


import com.google.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import war.domain.Hotel;
import war.domain.HotelRepository;

import java.util.Arrays;
import java.util.List;


public class AddHotelPage extends WebPage {
    @Inject
    HotelRepository hotelRepository;

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
        roomquant = new TextField("roomsQuant", new Model(""));
        price = new TextField("price", new Model(""));
        rooms = new TextField("rooms", new Model(""));
        oneperbed = new TextField("onePerBedQuant", new Model(""));
        twoperbed = new TextField("twoPerBedQuant", new Model(""));

        more = new Button("more");
        create = new Button("create"){
            @Override
            public void onSubmit(){
                String namev = (String) name.getModelObject();
                String countryv = (String) country.getModelObject();
                String cityv = (String) city.getModelObject();
                String addressv = (String) address.getModelObject();
                String telephonev = (String) telephone.getModelObject();
                String starsv =  stars.getModelObject().toString();
                Integer str = Integer.parseInt(starsv);
                String roomsqv =  roomsq.getModelObject().toString();
                Boolean ro = roomsqv.matches("^[0-9]+$");

                if(!ro){
                    System.out.print(">>>return>>>");
                   return;
                }
                Integer rom = Integer.parseInt(roomsqv);

                String  parkingv =  group.getModelObject().toString();
                Boolean pr = false;
                if(parkingv.equals("1")){
                    pr = true;
                }
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
            }
        };

        form.add(new Link("login") {
            public void onClick() {
                setResponsePage(new UserProfilePage());
            }
        });
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
        add(form);
    }
}
