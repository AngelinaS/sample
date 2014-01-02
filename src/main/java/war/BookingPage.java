package war;


import org.apache.wicket.Session;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BookingPage extends WebPage {
    private Label userlogin;
    private Object sessijaname;
    private Label hotelname;
    private DateTextField tfDateIn;
    private DateTextField tfDateOut;
    private Date dateIn = new Date();
    private Date dateOut = new Date();
    private DropDownChoice rommstype;
    private String zero="";

    private static final List<String> listFive = Arrays.asList(
            "1", "2", "3", "4", "5");

    public BookingPage (){
        Form form = new Form("form");
        sessijaname = (((MySession) Session.get()).getMyObject());
        userlogin = new Label("userpage", new Model(sessijaname.toString()));
        hotelname = new Label("hotelName", new Model(""));
        rommstype = new DropDownChoice("roomTypes", new PropertyModel<String> (this, "one"), listFive);
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
        add(form);
    }
}
