package war;


import com.google.inject.Inject;
import org.apache.wicket.Session;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import war.domain.Hotel;
import war.domain.HotelRepository;
import war.domain.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserPage extends WebPage {

    @Inject
    UserRepository userRepository;
    HotelRepository hotelRepository;

    private TextField search;
    private Label userlogin;
    private DateTextField tfDateIn;
    private DateTextField tfDateOut;
    private Integer one;
    private Integer zero;
    private DropDownChoice selectrooms;
    private DropDownChoice selectadults;
    private DropDownChoice selectchildren;
    private Button btnSearch;
    private Date dateIn = new Date();
    private Date dateOut = new Date();
    private Object sessijaname;

    private static final List<Integer> listFive = Arrays.asList(
            1, 2, 3, 4, 5);
    private static final List<Integer> listZero = Arrays.asList(
            0, 1,2, 3, 4, 5 );

    public UserPage (){
         Form form = new Form("form");
        one = new Integer(1);
        zero = new Integer(0);
        sessijaname = (((MySession)Session.get()).getMyObject());
        userlogin = new Label("userpage", new Model(sessijaname.toString()));

        search = new TextField("search", new Model(""));

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

        selectrooms = new DropDownChoice("rooms", new PropertyModel<Integer> (this, "one"), listFive);
        selectadults = new DropDownChoice("adults", new PropertyModel<Integer>(this, "one"),listFive);
        selectchildren = new DropDownChoice("children",new PropertyModel<Integer>(this,"zero"),listZero );


//        List<Hotel> hotels = hotelRepository.loadHotels();
//        Hotel hotel = hotels.get(0);

        form.add(new Label("hotelname", new Model<String>("test")));
        form.add(new Label("country", new Model<String>("test")));
        form.add(new Label("city", new Model<String>("test")));
        form.add(new Label("stars", new Model<Integer>(1)));
        form.add(new Link("more") {
            public void onClick() {
                setResponsePage(new HotelPage());
            }
        });

        btnSearch = new Button("btnSearch");
        Link logout = new Link("logout") {
            public void onClick() {
                ((MySession)Session.get()).setMyObject(null);
                setResponsePage(new HomePage());
            }
        };
        Link userLink = new Link("login") {
            public void onClick() {
                setResponsePage(new UserProfilePage());
            }
        };

        userLink.add(userlogin);
        form.add(userLink);
        form.add(logout);
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
