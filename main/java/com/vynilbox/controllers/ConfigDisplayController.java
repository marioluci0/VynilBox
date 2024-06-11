package com.vynilbox.controllers;

import com.vynilbox.user.User;
import com.vynilbox.user.UserRegisterData;
import com.vynilbox.user.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is used to allow the communication
 * between the view and model, that is, when the user
 * it's at the screen to configure them information,
 * all the components of the screen and its methods
 * are implemented in this class.
 * It implements the Screen and Initializable interfaces
 * to assurance the implementation of necessary methods.
 */
public class ConfigDisplayController implements Screen, Initializable {

    //Text Fields
    @FXML
    private TextField txtUsernameConfig;
    @FXML
    private TextField txtNameConfig;
    @FXML
    private TextField txtEmailConfig;
    @FXML
    private TextField txtPasswordConfig;
    @FXML
    private TextField txtPasswordConfirm;

    //Buttons
    @FXML
    private Button btnSave;

    //Image View
    @FXML
    private ImageView imgProfile;
    @FXML
    private ImageView imgBack;

    //Aux variables
    private static User user;

    /**
     * Set the txtUsernameConfig with username from user,
     * set the txtEmailConfig with email from user
     * and set the txtNameConfig with name form user
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUsernameConfig.setText(user.getUsername());
        txtEmailConfig.setText(user.getEmail());
        txtNameConfig.setText(user.getName());
    }

    /**
     * Method to show to te user when the cursor is on the
     * imgBack
     */
    @FXML
    protected void mouseBackEntered(){
        imgBack.setOpacity(0.5);
    }

    /**
     * Method to show to te user when the cursor is not on the
     * imgBack
     */
    @FXML
    protected void mouseBackExited(){
        imgBack.setOpacity(1);
    }

    /**
     * Go back to home screen
     */
    @FXML
    protected void onImgBackClick(){
        goToScreen(Tela.HOME, btnSave);
    }

    /**
     * check if strings on txtPasswordConfig and txtPasswordConfirm are the same
     * and get the string on txtNameConfig, txtUsernameConfig, txtEmailConfig and
     * txtPasswordConfig to save to database table users.
     * else show an alert to user "Digite a mesma senha em ambos os campos de senha"
     */
    @FXML
    protected void onBtnSaveClick(){
        if(txtPasswordConfig.getText().equals(txtPasswordConfirm.getText())) {
            new UserService().alterUser(new UserRegisterData(
                    txtNameConfig.getText(),
                    txtUsernameConfig.getText(),
                    txtEmailConfig.getText(),
                    txtPasswordConfig.getText()),
                    user.getId());
            new Alert(Alert.AlertType.INFORMATION, "Alteracoes salvas!").show();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Digite a mesma senha em ambos os campos de senha").show();
        }
    }

    //getter and setter of user
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ConfigDisplayController.user = user;
    }
}
