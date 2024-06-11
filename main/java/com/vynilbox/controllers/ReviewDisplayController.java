package com.vynilbox.controllers;

import com.vynilbox.review.ReviewRegisterData;
import com.vynilbox.review.ReviewService;
import com.vynilbox.song.Song;
import com.vynilbox.song.SongService;
import com.vynilbox.user.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class is used to allow the communication
 * between the view and model, that is, when the user
 * it's at the song review screen,
 * all the components of the screen and its methods
 * are implemented in this class.
 * It implements the Screen and Initializable interfaces
 * to assurance the implementation of necessary methods.
 */
public class ReviewDisplayController implements Screen, Initializable {

    //JavaFX content
    //Labels
    @FXML
    private Label lblSongName;
    @FXML
    private Label lblSongArtists;
    @FXML
    private Label lblSongDescription;

    //Images
    @FXML
    private ImageView imgSongCape;
    @FXML
    private ImageView imgBack;
    @FXML
    private ImageView img1Star;
    @FXML
    private ImageView img2Star;
    @FXML
    private ImageView img3Star;
    @FXML
    private ImageView img4Star;
    @FXML
    private ImageView img5Star;

    //Text Area
    @FXML
    private TextArea txtArea;

    //Buttons
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    //Aux variables
    private static Song song;
    private static User user;
    private int stars = 0;
    File starfile = new File("C:\\Users\\User\\IdeaProjects\\ProjetoPooFX\\src\\main\\java\\com\\vynilbox\\controllers\\images\\star.png");
    File blackStarFile = new File("C:\\Users\\User\\IdeaProjects\\ProjetoPooFX\\src\\main\\java\\com\\vynilbox\\controllers\\images\\blackStar.png");
    private final Image star = new Image(starfile.toURI().toString());
    private final Image blackStar = new Image(blackStarFile.toURI().toString());

    /**
     * Set the image of the song to imgSongCape,
     * give artists that make the song to lblSongArtists
     * and set the lblSongDescription with the song description
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File songCapeFile = new File("C:\\Users\\User\\IdeaProjects\\ProjetoPooFX\\src\\main\\java\\com\\vynilbox\\controllers\\songImages\\"+ song.getId() +"#songImage.jpg");
        imgSongCape.setImage(new Image(songCapeFile.toURI().toString()));
        lblSongName.setText(song.getName());

        ArrayList<String> artistsNames = new SongService().getArtistsNames(song);
        lblSongArtists.setText(artistsNames.toString());

        lblSongDescription.setText(song.getDescription());
    }

    /**
     * Go back to the home screen
     */
    @FXML
    protected void onImgBackClick(){
        goToScreen(Tela.HOME, btnCancel);
    }

    /**
     * Set the review to 1 star
     */
    @FXML
    protected void clicked1star(){
        img1Star.setImage(star);
        img2Star.setImage(blackStar);
        img3Star.setImage(blackStar);
        img4Star.setImage(blackStar);
        img5Star.setImage(blackStar);
        stars = 1;
    }

    /**
     * Set the review to 2 stars
     */
    @FXML
    protected void clicked2star(){
        img1Star.setImage(star);
        img2Star.setImage(star);
        img3Star.setImage(blackStar);
        img4Star.setImage(blackStar);
        img5Star.setImage(blackStar);
        stars = 2;
    }

    /**
     * Set the review to 3 stars
     */
    @FXML
    protected void clicked3star(){
        img1Star.setImage(star);
        img2Star.setImage(star);
        img3Star.setImage(star);
        img4Star.setImage(blackStar);
        img5Star.setImage(blackStar);
        stars = 3;
    }

    /**
     * Set the review to 4 stars
     */
    @FXML
    protected void clicked4star(){
        img1Star.setImage(star);
        img2Star.setImage(star);
        img3Star.setImage(star);
        img4Star.setImage(star);
        img5Star.setImage(blackStar);
        stars = 4;
    }

    /**
     * Set the review to 5 stars
     */
    @FXML
    protected void clicked5star(){
        img1Star.setImage(star);
        img2Star.setImage(star);
        img3Star.setImage(star);
        img4Star.setImage(star);
        img5Star.setImage(star);
        stars = 5;
    }

    /**
     * Call the ReviewService to add a new review,
     * show an alert to user notified that new review are created
     * and go back to home screen
     */
    @FXML
    protected void onBtnSaveClick(){
        new ReviewService().save(new ReviewRegisterData(stars, txtArea.getText(), song.getId(), user.getId()));
        new Alert(Alert.AlertType.INFORMATION, "Nova review criada!").show();
        goToScreen(Tela.HOME, btnSave);
    }

    /**
     * Just go back to home screen, dont save anything
     */
    @FXML
    protected void onBtnCancelClick(){
        goToScreen(Tela.HOME, btnCancel);
    }

    /**
     * Method to show to te user when the cursor is not on the
     * imgBack
     */
    @FXML
    protected void mouseBackEntered(){
        imgBack.setOpacity(0.5);
    }

    /**
     * Method to show to te user when the cursor is on the
     * imgBack
     */
    @FXML
    protected void mouseBackExited(){
        imgBack.setOpacity(1);
    }

    //Getter and setter of user and song
    public static Song getSong() {
        return song;
    }

    public static void setSong(Song song) {
        ReviewDisplayController.song = song;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ReviewDisplayController.user = user;
    }
}
