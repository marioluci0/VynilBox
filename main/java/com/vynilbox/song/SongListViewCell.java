package com.vynilbox.song;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SongListViewCell extends ListCell<Song> {

    @FXML
    private Label lblName;

    @FXML
    private Label lblArtists;

    @FXML
    private Label lblReleaseYear;

    @FXML
    private ImageView imgCape;

    @FXML
    private Button btnMakeReview;

    @FXML
    private AnchorPane apPanel;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Song song, boolean empty) {
        super.updateItem(song, empty);

        if(empty || song == null) {

            setText(null);
            setGraphic(null);

        } else {
            if(mLLoader == null)
            {
                try {
                    mLLoader = FXMLLoader.load(getClass().getResource("/com/vynilbox/ListCell.fxml"));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }

            lblName.setText(song.getName());
            lblReleaseYear.setText(song.getReleaseYear());
            imgCape.setImage(new Image("https://musicainstantanea.com.br/wp-content/uploads/2023/06/Olivia-Rodrigo-Guts.jpg"));
            btnMakeReview.setText("Make a Review");
            setText(null);
            setGraphic(apPanel);
        }
    }
}
