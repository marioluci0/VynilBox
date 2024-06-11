package com.vynilbox.controllers;

import com.vynilbox.song.Song;
import com.vynilbox.song.SongRegisterData;
import com.vynilbox.song.SongService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class is used to allow the communication
 * between the view and model, that is, when the admin
 * it's at the screen to edit song information,
 * all the components of the screen and its methods
 * are implemented in this class.
 * It implements the Screen and Initializable interfaces
 * to assurance the implementation of necessary methods.
 */
public class EditSongController implements Screen, Initializable {

    //Labels
    @FXML
    private Label lblSongArtists;

    //ImageViews
    @FXML
    private ImageView imgSongCape;

    //Text Fields
    @FXML
    private TextField txtSongDescription;
    @FXML
    private TextField txtSongName;

    //Buttons
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    //Aux variables
    private static Song song;

    /**
     * this method set the image of the song on the imgSongCape
     * set the name of artist on the txtSongName
     * set the description of song on txtSongDescription
     * and get the artists to show on the lblSongArtists the artists that
     * make this song
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File songCapeFile = new File("C:\\Users\\User\\IdeaProjects\\ProjetoPooFX\\src\\main\\java\\com\\vynilbox\\controllers\\songImages\\"+ song.getId() +"#songImage.jpg");
        imgSongCape.setImage(new Image(songCapeFile.toURI().toString()));
        txtSongName.setText(song.getName());
        txtSongDescription.setText(song.getDescription());

        ArrayList<String> artistsNames = new SongService().getArtistsNames(song);
        lblSongArtists.setText(artistsNames.toString());
    }

    /**
     * get the String on txtSongName and txtSongDescription and
     * pass how parameters to SongService to alter this song at
     * the database, then back to the homeadmin screen
     */
    @FXML
    protected void onBtnSaveClick() {
        new SongService().alterSong(
                new SongRegisterData(
                        txtSongName.getText(),
                        txtSongDescription.getText(),
                        null,
                        null,
                        null),
                song.getId());
        goToScreen(Tela.ADMHOME, btnSave);
    }

    /**
     * Go to homeadmin screen, dont save any change
     */
    @FXML
    protected void onBtnCancelClick() {
        goToScreen(Tela.ADMHOME, btnCancel);
    }

    //Getter and setter of song
    public static Song getSong() {
        return song;
    }

    public static void setSong(Song song) {
        EditSongController.song = song;
    }
}
