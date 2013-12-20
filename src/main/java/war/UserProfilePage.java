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

public class UserProfilePage extends WebPage {

    @Inject
    UserRepository userRepository;
    private Button changedata;
    private Button changepass;
    private Button delete;
    private Button savepass;
    private Button deleteaccount;
    private Button savedata;
    private Button cancel;
    private Label userlogin;
    private String login;
    private TextField name;
    private TextField surname;
    private TextField email;
    private Label text1;
    private Label text2;
    private Label text3;
    private Label passtext1;
    private Label passtext2;
    private Label passtext3;
    private PasswordTextField oldpass;
    private PasswordTextField newpass1;
    private PasswordTextField newpass2;
    private Label info;
    private Label deletetext;
    private Integer userid;
    private String pass;

    public UserProfilePage(){
        Form form = new Form("form");
        login = ((MySession) Session.get()).getMyObject().toString();
        User loguser = userRepository.loadUserByLogin(login);
        pass = loguser.getPassword().toString();
        userid = loguser.getUserID();
        userlogin = new Label("userLogin", new Model(login));
        name = new TextField("name", new Model(loguser.getName()));
        name.setEnabled(false);
        surname = new TextField("surname", new Model(loguser.getSurname()));
        surname.setEnabled(false);
        email = new TextField("email", new Model(loguser.getEmail()));
        email.setEnabled(false);
        oldpass = new PasswordTextField("oldpass" , new Model(""));
        oldpass.setVisible(false);
        newpass1 = new PasswordTextField("newpass1", new Model(""));
        newpass1.setVisible(false);
        newpass2 = new PasswordTextField("newpass2", new Model(""));
        newpass2.setVisible(false);
        info = new Label("info","Еnter your password on all margin to cancel");
        info.setVisible(false);
        text1 = new Label("text1","Name");
        text2 = new Label("text2","Surnamne");
        text3 = new Label("text3","E-mail");
        deletetext= new Label("deletetext", "Enter password");
        deletetext.setVisible(false);
        passtext1 = new Label("passtext1","Old password");
        passtext2 = new Label("passtext2","New password");
        passtext3 = new Label("passtext3","Repeat  new password ");
        passtext1.setVisible(false);
        passtext2.setVisible(false);
        passtext3.setVisible(false);

        changedata = new Button("changeData"){
            @Override
            public void onSubmit (){
                changedata.setVisible(false);
                changepass.setVisible(false);
                delete.setVisible(false);
                savedata.setVisible(true);
                name.setEnabled(true);
                surname.setEnabled(true);
                email.setEnabled(true);
                cancel.setVisible(true);

            }
        };
        savedata = new Button("savedata"){
            @Override
            public void onSubmit (){
                String namevalue = (String) name.getModelObject();
                String suranamevalue = (String) surname.getModelObject();
                String emailvalue = (String) email.getModelObject();
                name.setModelObject(namevalue);
                surname.setModelObject(suranamevalue);
                email.setModelObject(emailvalue);

                User user = userRepository.changeData(namevalue, suranamevalue, emailvalue, userid);
                name.setEnabled(false);
                surname.setEnabled(false);
                email.setEnabled(false);
                cancel.setVisible(false);
                savedata.setVisible(false);
                changedata.setVisible(true);
                changepass.setVisible(true);
                if (login.equals("admin")){
                    delete.setVisible(false);
                }
                else{
                    delete.setVisible(true);
                }
            }
        };
        savedata.setVisible(false);
        changepass = new Button("changePass"){
            @Override
            public void onSubmit (){
                changedata.setVisible(false);
                changepass.setVisible(false);
                delete.setVisible(false);
                savepass.setVisible(true);
                cancel.setVisible(true);
                passtext1.setVisible(true);
                passtext2.setVisible(true);
                passtext3.setVisible(true);
                text1.setVisible(false);
                text2.setVisible(false);
                text3.setVisible(false);
                oldpass.setVisible(true);
                newpass1.setVisible(true);
                newpass2.setVisible(true);
                name.setVisible(false);
                surname.setVisible(false);
                email.setVisible(false);
                info.setVisible(true);
            }
        };
        savepass = new Button("savepass"){
            @Override
            public void onSubmit (){
                String oldpassvalue = (String) oldpass.getModelObject();
                String newpass1value = (String) newpass1.getModelObject();
                String newpass2value = (String) newpass2.getModelObject();
                oldpass.setModelObject(oldpassvalue);
                newpass1.setModelObject(newpass1value);
                newpass2.setModelObject(newpass2value);
                if (oldpassvalue.equals(pass)){
                    if(newpass1value.length()>6){
                        if(newpass1value.equals(newpass2value)){
                            User user = userRepository.changePass(userid,newpass2value);
                            passtext1.setVisible(false);
                            passtext2.setVisible(false);
                            passtext3.setVisible(false);
                            oldpass.setVisible(false);
                            newpass1.setVisible(false);
                            newpass2.setVisible(false);
                            info.setVisible(false);
                            savepass.setVisible(false);
                            cancel.setVisible(false);
                            if (login.equals("admin")){
                                delete.setVisible(false);
                            }
                            else{
                                delete.setVisible(true);
                            }
                            text1.setVisible(true);
                            text2.setVisible(true);
                            text3.setVisible(true);
                            changedata.setVisible(true);
                            changepass.setVisible(true);
                            name.setVisible(true);
                            surname.setVisible(true);
                            email.setVisible(true);
                        }
                    }
                }
            }
        };
        savepass.setVisible(false);
        delete = new Button("delete"){
            @Override
            public void onSubmit (){
                changedata.setVisible(false);
                changepass.setVisible(false);
                delete.setVisible(false);
                text1.setVisible(false);
                text2.setVisible(false);
                text3.setVisible(false);
                name.setVisible(false);
                surname.setVisible(false);
                email.setVisible(false);
                deletetext.setVisible(true);
                deleteaccount.setVisible(true);
                cancel.setVisible(true);
                oldpass.setVisible(true);

            }
        };

        if (login.equals("admin")){
            delete.setVisible(false);
        }

        deleteaccount = new Button("deleteaccount"){
            @Override
            public void onSubmit (){
                String enterpass = (String) oldpass.getModelObject();
                oldpass.setModelObject(enterpass);
                if(enterpass.equals(pass)){
                    User user = userRepository.deleteAccount(userid);
                    ((MySession)Session.get()).setMyObject(null);
                    setResponsePage(new HomePage());
                }
            }
        };
        deleteaccount.setVisible(false);
        cancel = new Button("cancelbtn"){
            @Override
            public void onSubmit (){
                deletetext.setVisible(false);
                deleteaccount.setVisible(false);
                passtext1.setVisible(false);
                passtext2.setVisible(false);
                passtext3.setVisible(false);
                oldpass.setVisible(false);
                newpass1.setVisible(false);
                newpass2.setVisible(false);
                savepass.setVisible(false);
                cancel.setVisible(false);
                name.setEnabled(false);
                surname.setEnabled(false);
                email.setEnabled(false);
                text1.setVisible(true);
                text2.setVisible(true);
                text3.setVisible(true);
                changedata.setVisible(true);
                changepass.setVisible(true);
                name.setVisible(true);
                surname.setVisible(true);
                email.setVisible(true);
                savedata.setVisible(false);
                info.setVisible(false);
                if (login.equals("admin")){
                    delete.setVisible(false);
                }
                else{
                    delete.setVisible(true);
                }
            }
        };
        cancel.setVisible(false);

        form.add(new Link("home") {
            public void onClick() {
                setResponsePage(new UserPage());
            }
        });

        form.add(new Link("logout") {
            public void onClick() {
                ((MySession) Session.get()).setMyObject(null);
                setResponsePage(new HomePage());
            }
        });

        form.add(userlogin);
        form.add(name);
        form.add(surname);
        form.add(email);
        form.add(text1);
        form.add(text2);
        form.add(text3);
        form.add(changedata);
        form.add(changepass);
        form.add(delete);
        form.add(savepass);
        form.add(deleteaccount);
        form.add(savedata);
        form.add(cancel);
        form.add(passtext1);
        form.add(passtext2);
        form.add(passtext3);
        form.add(oldpass);
        form.add(newpass1);
        form.add(newpass2);
        form.add(info);
        form.add(deletetext);
        add(form);
    }


}
