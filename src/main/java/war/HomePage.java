package war;

import com.google.inject.Inject;
import org.apache.wicket.Session;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.time.TimeFrame;
import war.domain.Hotel;
import war.domain.HotelRepository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Homepage
 */
public class HomePage extends WebPage {

    @Inject
    HotelRepository hotelRepository;

    private TextField search;
    private Button btnSearch;
    private DropDownChoice selectrooms;
    private DropDownChoice selectadults;
    private DropDownChoice selectchildren;
    private DateTextField tfDateIn;
    private DateTextField tfDateOut;

    private Date dateIn = new Date();
    private Date dateOut = new Date();
    private Integer one=1;
    private Integer zero=0;

    private static final List<Integer> listFive = Arrays.asList(
            1,2, 3, 4, 5);
    private static final List<Integer> listZero = Arrays.asList(
            0, 1,2, 3, 4, 5 );

    public HomePage() {

        Form form = new Form("form");

        ((MySession) Session.get()).setMyObject("null");

        selectrooms = new DropDownChoice("rooms", new PropertyModel<Integer> (this, "one"), listFive);
        selectadults = new DropDownChoice("adults", new PropertyModel<Integer>(this, "one"),listFive);
        selectchildren = new DropDownChoice("children",new PropertyModel<Integer>(this,"zero"),listZero );
        search = new TextField("search", new Model(""));
        btnSearch = new Button("btnSearch"){
            @Override
            public void onSubmit(){

            }
        };

        tfDateIn = new DateTextField("dateIn", new PropertyModel<Date>(
                this, "dateIn"), new StyleDateConverter("S-", true));
        DatePicker datePicker = new DatePicker();
        datePicker.setShowOnFieldClick(true);
        datePicker.setAutoHide(true);
        tfDateIn.add(datePicker);

        tfDateOut = new DateTextField("dateOut", new PropertyModel<Date>(
                this, "dateIn"), new StyleDateConverter("S-", true));
        DatePicker datePicker2 = new DatePicker();
        datePicker2.setShowOnFieldClick(true);
        datePicker2.setAutoHide(true);
        tfDateOut.add(datePicker2);

        form.add(new Link("link") {
            public void onClick() {
                setResponsePage(new RegistrationPage());
            }
        });
        form.add(new Link("linkreg") {
            public void onClick() {
                setResponsePage(new LoginPage());
            }
        });

        int hotelLimits = 3;
        final List<Hotel> hotels = hotelRepository.loadHotels(hotelLimits);
        form.add(new ListView<Hotel>("tableHotelRow", hotels) {
            @Override
            protected void populateItem(final ListItem<Hotel> item) {
                final Hotel hotel = item.getModelObject();
                item.add(new Label("hotelname", new Model<String>(hotel.getName())));
                item.add(new Label("country", new Model<String>(hotel.getCountry())));
                item.add(new Label("city", new Model<String>(hotel.getCity())));
                item.add(new Label("stars", new Model<Integer>(hotel.getStars())));
                item.add(new Link("more") {
                    public void onClick() {
                        setResponsePage(new HotelPage(hotels.get(item.getIndex()).getHotelId()));
                    }
                });
            }
        });

        form.add(selectrooms);
        form.add(selectadults);
        form.add(selectchildren);
        form.add(search);
        form.add(tfDateIn);
        form.add(tfDateOut );
        form.add(btnSearch);
        add(form);
    }
}