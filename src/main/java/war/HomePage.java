package war;

import com.google.inject.Inject;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.time.TimeFrame;
import war.domain.Hotel;
import war.domain.HotelRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Homepage
 */
public class HomePage extends WebPage {

    @Inject
    HotelRepository hotelRepository;

    private TextField searchLabel;
    private Button btnSearch;
    private TextField name;
    private TextField sname;
    private TimeFrame time;
    private DropDownChoice selectrooms;
    private DropDownChoice selectadults;
    private DropDownChoice selectchildren;
    private DateTextField tfDateIn;
    private DateTextField tfDateOut;
    private Button submit;

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
        name = new TextField<String>("name", new Model(""));
        sname = new PasswordTextField("sname", new Model(""));
        selectrooms = new DropDownChoice("rooms", new PropertyModel<Integer> (this, "one"), listFive);
        selectadults = new DropDownChoice("adults", new PropertyModel<Integer>(this, "one"),listFive);
        selectchildren = new DropDownChoice("children",new PropertyModel<Integer>(this,"zero"),listZero );
        searchLabel = new TextField("search");
        btnSearch = new Button("btnSearch");
        submit = new Button("button") {
            @Override
            public void onSubmit() {
                String value = (String) name.getModelObject();
                String value2 = (String) sname.getModelObject();
//                label.setObject(value);
//                label2.setModelObject(value2);
//                name.setModelObject("");
//                sname.setModelObject("");
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

        form.add(name);
        form.add(sname);
        form.add(selectrooms);
        form.add(selectadults);
        form.add(selectchildren);
        form.add(submit);
        add(form);
        form.add(searchLabel);
        form.add(tfDateIn);
        form.add(tfDateOut );
        form.add(btnSearch);


        List<Hotel> hotels = hotelRepository.loadHotels();
        Hotel hotel = hotels.get(0);

        name.setModel(new Model<String>(hotel.getName()));
    }
}