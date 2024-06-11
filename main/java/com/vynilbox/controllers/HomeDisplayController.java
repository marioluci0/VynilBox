package com.vynilbox.controllers;

import com.vynilbox.artists.Artist;
import com.vynilbox.artists.ArtistService;
import com.vynilbox.exceptions.AlreadyHaveArtistException;
import com.vynilbox.exceptions.AlreadyHaveSongException;
import com.vynilbox.exceptions.NoResultsException;
import com.vynilbox.song.Song;
import com.vynilbox.song.SongService;
import com.vynilbox.user.User;
import com.vynilbox.user.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import org.controlsfx.control.textfield.TextFields;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is used to allow the communication
 * between the view and model, that is, when the user
 * it's at the home screen,
 * all the components of the screen and its methods
 * are implemented in this class.
 * It implements the Screen and Initializable interfaces
 * to assurance the implementation of necessary methods.
 */
public class HomeDisplayController implements Initializable, Screen {

    //Fields (Elements of screen)
    //label
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblWarning;
    @FXML
    private Label lblNoResult;

    //Text Field
    @FXML
    private TextField txtSearch;

    //Buttons
    @FXML
    private Button btnMakeReview;
    @FXML
    private Button btnFavoriteSong;
    @FXML
    private Button btnFavoriteArtist;

    //Image
    @FXML
    private ImageView imgHome;
    @FXML
    private ImageView imgProfile;
    @FXML
    private ImageView imgSearch;

    //Listview
    @FXML
    private ListView<Song> lsvListSongs;
    @FXML
    private ListView<Artist> lsvListArtists;

    //Aux variables
    private ObservableList<Song> songObservableList;
    private static User user;

    /**
     * This method is called when the homeDisplay-view.fxml are loaded
     * and set the suggestions to the txtSearch and set the objects on
     * the ListViews of Songs and Artists respectively. Also set the
     * btnFavoriteArtist, btnMakeReview and btnFavoriteSong invisible
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Adding songs and artists to lisvViews
        lsvListSongs.setItems(FXCollections.observableArrayList(new SongService().getAllSongs()));
        lsvListArtists.setItems(FXCollections.observableArrayList(new ArtistService().getAllArtists()));

        lblUsername.setText(user.getName());
        btnFavoriteArtist.setVisible(false);
        btnFavoriteSong.setVisible(false);
        btnMakeReview.setVisible(false);

        TextFields.bindAutoCompletion(txtSearch, new SongService().getAllSongsToString());
    }

    /**
     * This method is called when Profile image are clicked
     * and set the object user to the profile screen
     * and go to it
     */
    @FXML
    protected void onProfileImageClick(){
        ProfileDisplayController.setUser(user);
        goToScreen(Tela.PROFILE, txtSearch);
    }

    /**
     * This method just refresh the home screen
     */
    @FXML
    protected void onHomeImageClick(){
        goToScreen(Tela.HOME, txtSearch);
    }

    /**
     * This method is called when have a click on
     * the lsvListSongs then set visible the btnMakeReview
     * and btnFavoriteSong
     */
    @FXML
    protected void onListSongsClick() {
        btnMakeReview.setVisible(true);
        btnFavoriteSong.setVisible(true);
    }

    /**
     * this method is called when have a click on the lsvListArtist
     * then set visible the button btnFavoriteArtist
     */
    @FXML
    protected void onListArtistsClick() {
        btnFavoriteArtist.setVisible(true);
    }

    /**
     * When the btnReview are clicked this method is called,
     * on this way set selected song and go to Review screen of this song.
     * Case no one song are selected set text of lblWarning to "Selecione
     * uma música"
     */
    @FXML
    protected void onBtnMakeReviewClick(){
        ObservableList<Song> selectedItem = lsvListSongs.getSelectionModel().getSelectedItems();
        if(selectedItem.isEmpty()) {
            lblWarning.setTextFill(Paint.valueOf("#b24444"));
            lblWarning.setText("Selecione uma música");
        }
        else {
            ReviewDisplayController.setUser(user);
            ReviewDisplayController.setSong(selectedItem.getFirst());
            goToScreen(Tela.REVIEW, btnMakeReview);
        }
    }

    /**
     * When the btnFavoriteSong is clicked this method is called.
     * This method gets the selected object on lsvListSongs and
     * call the UserService to add this Song to favorite list of user,
     * case this song already in the favorite list, a warning is showed
     */
    @FXML
    protected void onBtnFavoriteSongClick(){
        Song selectedItem = lsvListSongs.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            lblWarning.setTextFill(Paint.valueOf("#b24444"));
            lblWarning.setText("Selecione uma música");
        }
        else {
            try {
                new UserService().addFavSong(
                        lsvListSongs.getSelectionModel().getSelectedItem().getId(),
                        user.getId());
                lblWarning.setTextFill(Paint.valueOf("#00ff09"));
                lblWarning.setText("Música adicionada as favoritas!");
                user.addFavSong(lsvListSongs.getSelectionModel().getSelectedItem().getId());
            } catch (AlreadyHaveSongException e) {
                lblWarning.setTextFill(Paint.valueOf("#b24444"));
                lblWarning.setText("Essa música já é favorita");
            }
        }
    }

    /**
     * When the btnFavoriteArtist is clicked this method is called.
     * This method gets the selected object on lsvListArtists and
     * call the UserService to add this Artist to favorite list of user,
     * case this artist already in the favorite list, a warning is showed
     */
    @FXML
    protected void onBtnFavoriteArtistClick() {
        Artist selectedItem = lsvListArtists.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            lblWarning.setTextFill(Paint.valueOf("#b24444"));
            lblWarning.setText("Selecione um artista");
        }
        else {
            try {
                new UserService().addFavArtist(
                        lsvListArtists.getSelectionModel().getSelectedItem().getId(),
                        user.getId());
                lblWarning.setTextFill(Paint.valueOf("#00ff09"));
                lblWarning.setText("Artista adicionado aos favoritos!");
                user.addFavArtist(lsvListArtists.getSelectionModel().getSelectedItem().getId());
            } catch (AlreadyHaveArtistException e) {
                lblWarning.setTextFill(Paint.valueOf("#b24444"));
                lblWarning.setText("Esse artista já é favorito");
            }
        }
    }

    /**
     * This method is called when the imgSearch was clicked
     * getting the string on txtSearch and go to Review screen of
     * the song with the name more similar according to the string
     */
    @FXML
    protected void onSearchClick() {
        Song tempSong = null;
        try {
            tempSong = new SongService().getSongByName(txtSearch.getText());
            ReviewDisplayController.setUser(user);
            ReviewDisplayController.setSong(tempSong);
            goToScreen(Tela.REVIEW, btnMakeReview);
        } catch (NoResultsException e) {
            lblNoResult.setText("Música não encontrada");
        }
    }

    /**
     * Method to show to te user when the cursor is on the
     * imgProfile
     */
    @FXML
    protected void mouseProfileEntered(){
        imgProfile.setOpacity(0.5);
    }

    /**
     * Method to show to te user when the cursor is not on the
     * imgProfile
     */
    @FXML
    protected void mouseProfileExited(){
        imgProfile.setOpacity(1);
    }

    /**
     * Method to show to te user when the cursor is on the
     * imgSearch
     */
    @FXML
    protected void mouseSearchEntered(){
        imgSearch.setOpacity(0.5);
    }

    /**
     * Method to show to te user when the cursor is not on the
     * imgSearch
     */
    @FXML
    protected void mouseSearchExited(){
        imgSearch.setOpacity(1);
    }

    /**
     * Method to show to te user when the cursor is on the
     * imgHome
     */
    @FXML
    protected void mouseHomeEntered(){
        imgHome.setOpacity(0.5);
    }

    /**
     * Method to show to te user when the cursor is not on the
     * imgHome
     */
    @FXML
    protected void mouseHomeExited(){
        imgHome.setOpacity(1);
    }

    //Getters and setters of user
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        HomeDisplayController.user = user;
    }
}
