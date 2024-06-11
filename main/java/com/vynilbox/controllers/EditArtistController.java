package com.vynilbox.controllers;

import com.vynilbox.artists.Artist;
import com.vynilbox.artists.ArtistRegisterData;
import com.vynilbox.artists.ArtistService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is used to allow the communication
 * between the view and model, that is, when the admin
 * it's at the screen to edit artist information,
 * all the components of the screen and its methods
 * are implemented in this class.
 * It implements the Screen and Initializable interfaces
 * to assurance the implementation of necessary methods.
 */
public class EditArtistController implements Screen, Initializable {

    //Text Fields
    @FXML
    private TextField txtArtistName;
    @FXML
    private TextField txtArtistDescription;

    //Buttons
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    //Aux variables
    private static Artist artist;

    /**
     * Set the txtArtistName text with artist name
     * and set the txtArtistDescription with artists description
     * both from database
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtArtistName.setText(artist.getName());
        txtArtistDescription.setText(artist.getDescription());
    }

    /**
     * get the String on txtArtistName and txtArtists and
     * pass how parameters to ArtistService to alter this artist at
     * the database, then back to the homeadmin screen
     */
    @FXML
    protected void onBtnSaveClick() {
        new ArtistService().alterArtist(new ArtistRegisterData(
                txtArtistName.getText(),
                txtArtistDescription.getText(),
                null
                ),
                artist.getId()
        );
        goToScreen(Tela.ADMHOME,btnSave);
    }

    /**
     * Go to homeadmin screen, don't save any change
     */
    @FXML
    protected void onBtnCancelClick() {
        goToScreen(Tela.ADMHOME, btnCancel);
    }

    //Getter and setter of artist
    public static Artist getArtist() {
        return artist;
    }

    public static void setArtist(Artist artist) {
        EditArtistController.artist = artist;
    }
}
