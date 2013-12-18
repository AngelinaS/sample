package war;

import com.google.inject.Inject;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import war.domain.User;
import war.domain.UserRepository;



public class LoginPage extends WebPage {
    @Inject
    UserRepository userRepository;

    private TextField name;
    private PasswordTextField pass;
    private Label info;
    private Button login;
    private String password;



    public LoginPage (){
        Form form = new Form("form");

        name = new TextField<String>("login", new Model(""));
        pass = new PasswordTextField("passw", new Model(""));
        info = new Label("info","Login or password is incorrect!");
        info.setVisible(false);
        login = new Button("send") {
            @Override
            public void onSubmit (){
                info.setVisible(false);
                String namevalue = (String) name.getModelObject();
                String passvalue = (String) pass.getModelObject();
                namevalue = namevalue.toLowerCase();
                name.setModelObject(namevalue);
                pass.setModelObject(passvalue);
                User loguser = userRepository.loadUserByLogin(namevalue);
                if(loguser != null) {
                    password = loguser.getPassword();
                    if(passvalue.equals(password)){
                        ((MySession)Session.get()).setMyObject(namevalue);
                        if(namevalue.equals("admin")){
                         setResponsePage(new AdminPage());
                        }
                        else {
                         setResponsePage(new UserPage());
                        }

                    }
                     else {
                        info.setVisible(true);
                    }
                }
                else {
                    info.setVisible(true);
                }
            }

        };
        form.add(new Link("link") {
            public void onClick() {
                setResponsePage(new RegistrationPage());
            }
        });

       add(form);
       form.add(name);
       form.add(pass);
       form.add(info);
       form.add(login);

    }
}
