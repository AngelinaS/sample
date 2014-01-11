package war;


import com.google.inject.Inject;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import war.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BookingPage extends WebPage {
    @Inject
    HotelRepository hotelRepository;
    @Inject
    RoomsRepository roomsRepository;
    @Inject
    BookingRepository bookingRepository;
    @Inject
    UserRepository userRepository;

    private Label userlogin;
    private Object sessijaname;
    private Label hotelname;
    private DateTextField tfDateIn;
    private DateTextField tfDateOut;
    private Date dateIn = new Date();
    private Date dateOut = new Date();
    private DropDownChoice rommstype;
    private DropDownChoice roomsquantity;
    private String zero="";
    private Label price;
    private Button book;
    public Double allprice;
    private String one = "1";
    private Date today;

    private static final List<String> listFive = Arrays.asList(
           "1", "2", "3", "4", "5");

    public BookingPage (Integer hotelsid){
        final Hotel hotel = hotelRepository.hotelbyId(hotelsid);

        Form form = new Form("form");

        sessijaname = (((MySession) Session.get()).getMyObject());
        userlogin = new Label("userpage", new Model(sessijaname.toString()));
        hotelname = new Label("hotelName", new Model(hotel.getName()));

        List<Rooms> room = roomsRepository.loadRooms(hotel);

        List<String> roomsname = new ArrayList<String>(room.size());
        for(Integer i=0; i<room.size(); i++){
            String n =  room.get(i).roomsType;
            roomsname.add(i,n);
        }
        allprice = 0.0;

        price = new Label("price", new Model(allprice));
        price.setOutputMarkupId(true);
        rommstype = new DropDownChoice("roomTypes", new PropertyModel<String> (this, "zero"), roomsname);
        rommstype.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                String nameroom = rommstype.getModelObject().toString();
                Rooms oneroom = roomsRepository.loadbyname(nameroom);
                String quant = roomsquantity.getModelObject().toString();
                allprice = Double.parseDouble(quant)*oneroom.price;
                price.setDefaultModelObject(allprice);
                target.addComponent(price);
            }
        });
        roomsquantity = new DropDownChoice("roomQuantity",new PropertyModel<String>(this, "one"), listFive);
        roomsquantity.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                String nameroom = rommstype.getModelObject().toString();
                Rooms oneroom = roomsRepository.loadbyname(nameroom);
                String quant = roomsquantity.getModelObject().toString();
                allprice = Double.parseDouble(quant)*oneroom.price;
                price.setDefaultModelObject(allprice);
                target.addComponent(price);
            }
        });

        final Date[] date1 = new Date[1];
        final Date[] date2 = new Date[1];

        tfDateIn = new DateTextField("dateIn", new PropertyModel<Date>(
                this, "dateIn"), new StyleDateConverter("S-", true));
        DatePicker datePicker = new DatePicker();
        datePicker.setShowOnFieldClick(true);
        datePicker.setAutoHide(true);
        tfDateIn.add(datePicker);
        tfDateIn.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                date1[0] = tfDateIn.getModelObject();
            }
        });
        today = tfDateIn.getModelObject();
        tfDateOut = new DateTextField("dateOut", new PropertyModel<Date>(
                this, "dateIn"), new StyleDateConverter("S-", true));
        DatePicker datePicker2 = new DatePicker();
        datePicker2.setShowOnFieldClick(true);
        datePicker2.setAutoHide(true);
        tfDateOut.add(datePicker2);
        tfDateOut.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                date2[0] = tfDateIn.getModelObject();
            }
        });


        book = new Button("book"){
            @Override
            public void onSubmit(){
                String nameroom = rommstype.getModelObject().toString();
                Rooms oneroom = roomsRepository.loadbyname(nameroom);
                String quant = roomsquantity.getModelObject().toString();
                User user = userRepository.loadUserByLogin(sessijaname.toString());
                Booking book = bookingRepository.addBooking(date1[0],date2[0],today,Integer.parseInt(quant), user, oneroom);
                setResponsePage(new UserPage());
            }
        };
        Link userLink = new Link("login") {
            public void onClick() {
                setResponsePage(new UserProfilePage());
            }
        };

        userLink.add(userlogin);
        form.add(userLink);
        form.add(hotelname);
        form.add(tfDateIn);
        form.add(tfDateOut );
        form.add(rommstype);
        form.add(roomsquantity);
        form.add(price);
        form.add(book);
        add(form);
    }
}
