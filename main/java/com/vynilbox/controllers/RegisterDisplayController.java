package com.vynilbox.controllers;

import com.vynilbox.exceptions.RegisterErrorException;
import com.vynilbox.user.UserRegisterData;
import com.vynilbox.user.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

/**
 * This class is used to allow the communication
 * between the view and model, that is, when the user
 * it's at the register screen,
 * all the components of the screen and its methods
 * are implemented in this class.
 * It implements the Screen and Initializable interfaces
 * to assurance the implementation of necessary methods.
 */
public class RegisterDisplayController implements Screen {

    //Labels
    @FXML
    private Label lblInvalidRegister;

    //Buttons
    @FXML
    private Button btnRegister;

    //Hyperlinks
    @FXML
    private Hyperlink linkLogin;

    //Text fields
    @FXML
    private TextField txtNameRegister;
    @FXML
    private TextField txtUserRegister;
    @FXML
    private TextField txtEmailRegister;
    @FXML
    private TextField txtPasswordRegister;

    /**
     * This method is called when RegisterButton is clicked
     * and gets the strings of txtNameRegister, txtUserRegister,
     * txtEmailRegister and txtPasswordRegister.
     * Case some of that fields are empty, set text of lblInvalidRegister
     * to inform the user.
     */
    @FXML
    protected void onRegisterButtonClick(){
        if(!txtNameRegister.getText().isBlank() && !txtUserRegister.getText().isBlank() && !txtEmailRegister.getText().isBlank() && !txtPasswordRegister.getText().isBlank()){
            validateRegister();
        } else {
            lblInvalidRegister.setText("Preencha todos os campos de cadastro.");
        }
    }

    /**
     * This method is responsible to change the screen,
     * going to Login screen when the LinkLogin are
     * clicked
     */
    @FXML
    protected void onLinkLoginClick(){
        goToScreen(Tela.LOGIN, btnRegister);
    }

    /**
     * This method checks if are an user with the informed
     * email or username the information registered
     * on the fields and add to the database system.
     */
    public void validateRegister(){
        try{
            new UserService().save(new UserRegisterData(
                    txtNameRegister.getText(),
                    txtUserRegister.getText(),
                    txtEmailRegister.getText(),
                    txtPasswordRegister.getText()
            ));
            new Alert(Alert.AlertType.INFORMATION, "Novo usuário cadastrado com sucesso!").show();
        }catch(SQLException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }catch(RegisterErrorException e){
            new Alert(Alert.AlertType.ERROR, "Este Email ou usuário ja está em uso. Por favor use outro").show();
        }
    }
}
