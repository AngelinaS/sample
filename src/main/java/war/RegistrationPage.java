package war;

import com.google.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import war.domain.User;
import war.domain.UserRepository;

import javax.swing.*;
import java.util.*;

public class RegistrationPage extends WebPage {

    @Inject
    UserRepository userRepository;

    private TextField name;
    private TextField login;
    private TextField surname;
    private PasswordTextField  password1;
    private PasswordTextField password2;
    private Button registration;
    private EmailTextField email;

    public RegistrationPage () {
        Form form = new Form ("form");
        name = new TextField("name", new Model(""));
        login = new TextField("ulogin" , new Model(""));
        surname = new TextField("surname" , new Model(""));
        password1 = new PasswordTextField("pass1", new Model(""));
        password2 = new PasswordTextField("pass2", new Model(""));
        email = new EmailTextField("email", new Model<String>(""));
        registration = new Button("registration")
        {
            @Override
            public void onSubmit () {
                String loginvalue = (String) login.getModelObject();
                String namevalue = (String) name.getModelObject();
                String surnamevalue = (String) surname.getModelObject();
                String passwordvalue = (String) password1.getModelObject();
                String passwordvalue2 = (String) password2.getModelObject();
                String emailvalue = (String) email.getModelObject();
                loginvalue = loginvalue.toLowerCase();
                login.setModelObject(loginvalue);
                name.setModelObject(namevalue);
                surname.setModelObject(surnamevalue);
                password1.setModelObject(passwordvalue);
                password2.setModelObject(passwordvalue2);
                email.setModelObject(emailvalue);
                User loguser = userRepository.loadUserByLogin(loginvalue);
                if (loguser == null){
                       if(passwordvalue.length()>6)
                        {
                            if (passwordvalue.equals(passwordvalue2))
                            {
                            User user = userRepository.addUser(loginvalue, namevalue, surnamevalue, passwordvalue, emailvalue);
                            }
                            else
                            {
                            JFrame frame = new JFrame();
                            JOptionPane.showMessageDialog(frame, "incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                        {
                            JFrame frame = new JFrame();
                            JOptionPane.showMessageDialog(frame, "password is very small", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                }
                else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "incorrect user login", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        };


        add(form);
        form.add(name);
        form.add(surname);
        form.add(login);
        form.add(password1);
        form.add(password2);
        form.add(registration);
        form.add(email);


    }



}
