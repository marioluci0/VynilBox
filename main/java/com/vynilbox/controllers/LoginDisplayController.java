package com.vynilbox.controllers;

import com.vynilbox.abstracts.Account;
import com.vynilbox.admin.Admin;
import com.vynilbox.admin.AdminService;
import com.vynilbox.exceptions.AccountNotFoundException;
import com.vynilbox.user.User;
import com.vynilbox.user.UserRegisterData;
import com.vynilbox.user.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * This class is used to allow the communication
 * between the view and model, that is, when the user
 * it's at the login screen, all the components of the
 * screen and its methods are implemented in this class.
 * It implements the Screen and Initializable interfaces
 * to assurance the implementation of necessary methods.
 */
public class LoginDisplayController implements Screen {

    //Labels
    @FXML
    private Label lblInvalidLogin;

    //Buttons
    @FXML
    private Button btnLogin;

    //Hyperlinks
    @FXML
    private Hyperlink linkRegister;

    //Text Fields
    @FXML
    private TextField txtPasswordLogin;
    @FXML
    private TextField txtEmailLogin;

    /**
     * This method is called when the LoginButton is clicked.
     * Then call validateLogin to verify if Strings on txtPasswordLogin
     * and txtEmailLogin is of a user with this respects email and password
     */
    @FXML
    protected void onLoginButtonClick(){
        if(!txtEmailLogin.getText().isBlank() && !txtPasswordLogin.getText().isBlank()){
            validateLogin();
        }
        else {
            lblInvalidLogin.setText("Preencha todos os campos de login.");
        }
    }

    /**
     * This method is responsible to change the screen,
     * going to register screen when the LinkRegister are
     * clicked
     */
    @FXML
    protected void onLinkRegisterClick(){
        goToScreen(Tela.REGISTER, btnLogin);
    }

    /**
     * When this method is called it verify if
     * the login are made for a user or an admin.
     * Then go to the Home screen case is user
     * and go to the HomeAdmin screen case is admin
     */
    public void validateLogin(){
        UserService userService = new UserService();
        AdminService adminService = new AdminService();

        try{
            Account account = adminService.validate(
                    txtEmailLogin.getText(),
                    txtPasswordLogin.getText()
            );

            if (account == null) {
                account = userService.validate(new UserRegisterData(
                        null,
                        null,
                        txtEmailLogin.getText(),
                        txtPasswordLogin.getText()
                ));
            }

            if(account instanceof User){
                HomeDisplayController.setUser((User) account);
                goToScreen(Tela.HOME, btnLogin);
            }
            else{
                HomeAdminController.setUser((Admin) account);
                goToScreen(Tela.ADMHOME, btnLogin);
            }
        } catch(AccountNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Email ou senha inválidos!").show();
        } catch(RuntimeException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
