package com.vynilbox.controllers;

import com.vynilbox.admin.Admin;
import com.vynilbox.artists.Artist;
import com.vynilbox.artists.ArtistService;
import com.vynilbox.exceptions.NoResultsException;
import com.vynilbox.song.Song;
import com.vynilbox.song.SongService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is used to allow the communication
 * between the view and model, that is, when the admin
 * it's at the home screen,
 * all the components of the screen and its methods
 * are implemented in this class.
 * It implements the Screen and Initializable interfaces
 * to assurance the implementation of necessary methods.
 */
public class HomeAdminController implements Screen, Initializable {

    //Text Field
    @FXML
    private TextField txtSearch;

    //Labels
    @FXML
    private Label lblNoResult;
    @FXML
    private Label lblUsername;

    //Image
    @FXML
    private ImageView imgHome;
    @FXML
    private ImageView imgSearch;

    //Buttons
    @FXML
    private Button btnEditSong;
    @FXML
    private Button btnEditArtist;
    @FXML
    private Button btnLogout;

    //ListViews
    @FXML
    private ListView<Song> lsvListSongs;
    @FXML
    private ListView<Artist> lsvListArtists;

    //Aux variables
    private ObservableList<Song> observableList;
    private static Admin adm;

    /**
     * This method is for when the homeDisplay-Admin.fxml is loaded.
     * Set the initials configurations to lsvListSongs and lsvListArtists
     * giving to it their respectives lists of Songs and Artists and
     * set the Buttuns btnEditArtists and btnEditSong invisible.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lsvListSongs.setItems(FXCollections.observableArrayList(new SongService().getAllSongs()));
        lsvListArtists.setItems(FXCollections.observableArrayList(new ArtistService().getAllArtists()));

        lblUsername.setText(adm.getUsername());
        btnEditArtist.setVisible(false);
        btnEditSong.setVisible(false);

        TextFields.bindAutoCompletion(txtSearch, new SongService().getAllSongsToString());
    }

    /**
     * Go to login screen and
     */
    @FXML
    protected void onBtnLogoutClick() {
        goToScreen(Tela.LOGIN,btnLogout);
    }

    /**
     * Just reload the admin home page
     */
    @FXML
    protected void onHomeImageClick(){
        goToScreen(Tela.ADMHOME, btnEditSong);
    }

    /**
     * Gets the text on the txtSearch and go to editsong screen
     * of song with the most similar name.
     * If dont found any song with similar name, just set text of
     * lblNoResult "Música não encontrada"
     */
    @FXML
    protected void onSearchClick() {
        Song tempSong = null;
        try {
            tempSong = new SongService().getSongByName(txtSearch.getText());
            EditSongController.setSong(tempSong);
            goToScreen(Tela.EDITSONG, btnEditArtist);
        } catch (NoResultsException e) {
            lblNoResult.setText("Música não encontrada");
        }
    }

    /**
     * Go to editsong screen of the selected song on the lsvListSongs
     */
    @FXML
    protected void onBtnEditSongClick() {
        EditSongController.setSong(lsvListSongs.getSelectionModel().getSelectedItem());
        goToScreen(Tela.EDITSONG, btnEditArtist);
    }

    /**
     * Go to editartist screen of the selected artist on the lsvListArtists
     */
    @FXML
    protected void onBtnEditArtistClick() {
        EditArtistController.setArtist(lsvListArtists.getSelectionModel().getSelectedItem());
        goToScreen(Tela.EDIT_ARTIST, btnEditArtist);
    }

    /**
     * Show btnEditSong when some song at the lsvListSongs is clicked
     */
    @FXML
    protected void onListSongsClick() {
        btnEditSong.setVisible(true);
    }

    /**
     * Show btnEditArtist when some artist at the lsvListArtists is clicked
     */
    @FXML
    protected void onListArtistsClick() {
        btnEditArtist.setVisible(true);
    }

    /**
     * Method to show to te user when the cursor is on the
     * imgHome
     */
    @FXML
    protected void mouseHomeEntered() {
        imgHome.setOpacity(0.5);
    }

    /**
     * Method to show to te user when the cursor is not on the
     * imgHome
     */
    @FXML
    protected void mouseHomeExited() {
        imgHome.setOpacity(1);
    }

    /**
     * Method to show to te user when the cursor is on the
     * imgSearch
     */
    @FXML
    protected void mouseSearchEntered() {
        imgSearch.setOpacity(0.5);
    }

    /**
     * Method to show to te user when the cursor is not on the
     * imgSearch
     */
    @FXML
    protected void mouseSearchExited() {
        imgSearch.setOpacity(1);
    }

    //Getters and setters of user
    public static Admin getUser() {
        return adm;
    }

    public static void setUser(Admin adm) {
        HomeAdminController.adm = adm;
    }
}
