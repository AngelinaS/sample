package war;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.util.*;

public class RegistrationPage extends WebPage {
    private TextField name;
    private TextField surname;
    private DropDownChoice selectyear;
    private DropDownChoice selectmonth;
    private DropDownChoice selectday;
    private PasswordTextField  password1;
    private PasswordTextField password2;
    private Button submit;
    private EmailTextField email;
    private static final java.util.List<Integer> listYears = Arrays.asList(
    );
    private static final java.util.List<Integer> listMonth = Arrays.asList(
    );
    private static final java.util.List<Integer> listDays = Arrays.asList(
    );
    public RegistrationPage () {
        Form form = new Form ("form");
        name = new TextField("name", new Model(""));
        surname = new TextField("surname" , new Model(""));
        password1 = new PasswordTextField("pass1", new Model(""));
        password2 = new PasswordTextField("pass2", new Model(""));
        selectyear = new DropDownChoice("year", new PropertyModel<Integer>(this, ""), listYears);
        selectmonth = new DropDownChoice("month", new PropertyModel<Integer>(this, ""),listMonth);
        selectday = new DropDownChoice("day",new PropertyModel<Integer>(this,""),listDays );
        submit = new Button("search")
        {

        public void OnSubmit () {
                String value = (String) name.getModelObject();
                String value2 = (String) surname.getModelObject();
            }
        };
        email = new EmailTextField("email", new Model<String>(""));
        add(form);
        form.add(name);
        form.add(surname);
        form.add(password1);
        form.add(password2);
        form.add(selectday);
        form.add(selectmonth);
        form.add(selectyear);
        form.add(submit);
        form.add(email);


    }



}
