package war;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.time.TimeFrame;

import java.util.Date;

/**
 * Homepage
 */
public class HomePage extends WebPage {

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

    public HomePage() {
        Form form = new Form("form");
        name = new TextField<String>("name", new Model(""));
        sname = new PasswordTextField("sname", new Model(""));
        selectrooms = new DropDownChoice("rooms");
        selectadults = new DropDownChoice("adults");
        selectchildren = new DropDownChoice("children");
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
//        select.add()

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
    }
}