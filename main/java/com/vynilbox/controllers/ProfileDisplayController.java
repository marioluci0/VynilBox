package com.vynilbox.controllers;

import com.vynilbox.artists.Artist;
import com.vynilbox.artists.ArtistService;
import com.vynilbox.review.Review;
import com.vynilbox.review.ReviewService;
import com.vynilbox.song.Song;
import com.vynilbox.song.SongService;
import com.vynilbox.user.User;
import com.vynilbox.user.UserService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is used to allow the communication
 * between the view and model, that is, when the user
 * it's at his profile display,
 * all the components of the screen and its methods
 * are implemented in this class.
 * It implements the Screen and Initializable interfaces
 * to assurance the implementation of necessary methods.
 */
public class ProfileDisplayController implements Screen, Initializable {

    //Images
    @FXML
    private ImageView imgProfile;
    @FXML
    private ImageView imgBack;
    @FXML
    private ImageView img1star;
    @FXML
    private ImageView img2star;
    @FXML
    private ImageView img3star;
    @FXML
    private ImageView img4star;
    @FXML
    private ImageView img5star;

    //Buttons
    @FXML
    private Button btnConfig;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnDelReview;
    @FXML
    private Button btnDelFavArtist;
    @FXML
    private Button btnDelFavSong;

    //Lists
    @FXML
    private ListView<Song> listFavSongs;
    @FXML
    private ListView<Artist> listFavArtists;
    @FXML
    private ListView<Review> listReviews;

    //Labels
    @FXML
    private Label lblSongName;
    @FXML
    private Label lblSongDescription;

    //Aux variables
    private static User user;
    File starfile = new File("C:\\Users\\User\\IdeaProjects\\ProjetoPooFX\\src\\main\\java\\com\\vynilbox\\controllers\\images\\star.png");
    private final Image star = new Image(starfile.toURI().toString());

    /**
     * Set the btnDelReview, btnDelFavArtist and btnDelFavSong to not visible,
     * get user favorite songs and favorite artists to listFavSongs and listFavArtists
     * and give to listReviews the user reviews
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnDelReview.setVisible(false);
        btnDelFavArtist.setVisible(false);
        btnDelFavSong.setVisible(false);

        listReviews.setItems(FXCollections.observableArrayList(new ReviewService().getAllReviews(user)));
        listFavArtists.setItems(FXCollections.observableArrayList(new ArtistService().getAllArtists(user)));
        listFavSongs.setItems(FXCollections.observableArrayList(new SongService().getAllSongs(user)));
    }

    /**
     * Just go back to home screen
     */
    @FXML
    protected void onImgBackClick(){
        goToScreen(Tela.HOME, btnConfig);
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
     * Go to config screen of the user that is logged
     */
    @FXML
    protected void onBtnConfigClick(){
        ConfigDisplayController.setUser(user);
        goToScreen(Tela.CONFIG, btnConfig);
    }

    /**
     * Just go to the login screen
     */
    @FXML
    protected void onBtnLogoutClick(){
        goToScreen(Tela.LOGIN, btnLogout);
    }

    /**
     * When clicks on the review list, this method is called
     * setting to visible the review in a resumed format and
     * showing btnDelReview
     */
    @FXML
    protected void onReviewListClick() {
        Review tempReview = listReviews.getSelectionModel().getSelectedItem();
        lblSongName.setText(new SongService().getSongById(tempReview.getSongId()).getName());
        lblSongDescription.setText(tempReview.getText());
        btnDelReview.setVisible(true);
        switch(tempReview.getStars()) {
            case 1:
                img1star.setImage(star);
                img2star.setImage(null);
                img3star.setImage(null);
                img4star.setImage(null);
                img5star.setImage(null);
                break;
            case 2:
                img1star.setImage(star);
                img2star.setImage(star);
                img3star.setImage(null);
                img4star.setImage(null);
                img5star.setImage(null);
                break;
            case 3:
                img1star.setImage(star);
                img2star.setImage(star);
                img3star.setImage(star);
                img4star.setImage(null);
                img5star.setImage(null);
                break;
            case 4:
                img1star.setImage(star);
                img2star.setImage(star);
                img3star.setImage(star);
                img4star.setImage(star);
                img5star.setImage(null);
                break;
            case 5:
                img1star.setImage(star);
                img2star.setImage(star);
                img3star.setImage(star);
                img4star.setImage(star);
                img5star.setImage(star);
                break;
            default:
                img1star.setImage(null);
                img2star.setImage(null);
                img3star.setImage(null);
                img4star.setImage(null);
                img5star.setImage(null);
        }
    }

    /**
     * Show the button btnDelFavSong
     */
    @FXML
    protected void onSongListClick() {
        btnDelFavSong.setVisible(true);
    }

    /**
     * show the button btnDelFavArtist
     */
    @FXML
    protected void onArtistListClick() {
        btnDelFavArtist.setVisible(true);
    }

    /**
     * call ReviewService to delete the selected review at
     * the listReviews selected and refresh the profile screen
     */
    @FXML
    protected void onBtnDelReviewClick() {
        new ReviewService().delete(listReviews.getSelectionModel().getSelectedItem().getId());
        listReviews.refresh();
        goToScreen(Tela.PROFILE, btnDelReview);
    }

    /**
     * call UserService to delete the selected favorite song at
     * the listFavSongs selected and refresh the profile screen
     */
    @FXML
    protected void onBtnDelFavSongClick() {
        new UserService().removeFavSong(listFavSongs.getSelectionModel().getSelectedItem().getId(), user.getId());
        user.removeFavSong(listFavSongs.getSelectionModel().getSelectedItem().getId());
        listFavSongs.refresh();
        goToScreen(Tela.PROFILE, btnDelFavSong);
    }

    /**
     * call UserService to delete the selected favorite artist at
     * the listFavArtists selected and refresh the profile screen
     */
    @FXML
    protected void onBtnDelFavArtistClick() {
        new UserService().removeFavArtist(listFavArtists.getSelectionModel().getSelectedItem().getId(), user.getId());
        user.removeFavArtist(listFavArtists.getSelectionModel().getSelectedItem().getId());
        listFavArtists.refresh();
        goToScreen(Tela.PROFILE, btnDelFavArtist);
    }

    //Getter and setter of user
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ProfileDisplayController.user = user;
    }

}
