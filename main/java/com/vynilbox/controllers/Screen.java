package com.vynilbox.controllers;

import com.vynilbox.abstracts.BasicFields;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public interface Screen<T extends javafx.scene.control.Control, O extends BasicFields> {

    public enum Tela {
        REGISTER,
        LOGIN,
        HOME,
        PROFILE,
        CONFIG,
        REVIEW,
        ADMHOME,
        EDITSONG,
        EDIT_ARTIST
    }

    //Go to scene specified in String
    //Should give an object in the actual screen
    public default void goToScreen(Tela to, T elem){
        Parent loader = null;
        try {
            if(to == Tela.REGISTER) loader = FXMLLoader.load(getClass().getResource("/com/vynilbox/registerDisplay-view.fxml"));
            else if(to == Tela.LOGIN) loader = FXMLLoader.load(getClass().getResource("/com/vynilbox/loginDisplay-view.fxml"));
            else if(to == Tela.HOME)loader = FXMLLoader.load(getClass().getResource("/com/vynilbox/homeDisplay-view.fxml"));
            else if(to == Tela.PROFILE) loader = FXMLLoader.load(getClass().getResource("/com/vynilbox/profileDisplay-view.fxml"));
            else if(to == Tela.CONFIG) loader = FXMLLoader.load(getClass().getResource("/com/vynilbox/configDisplay-view.fxml"));
            else if(to == Tela.ADMHOME) loader = FXMLLoader.load(getClass().getResource("/com/vynilbox/homeDisplay-Admin.fxml"));
            else if(to == Tela.REVIEW) loader = FXMLLoader.load(getClass().getResource("/com/vynilbox/reviewDisplay-view.fxml"));
            else if(to == Tela.EDITSONG) loader = FXMLLoader.load(getClass().getResource("/com/vynilbox/editSongDisplay-Admin.fxml"));
            else if(to == Tela.EDIT_ARTIST) loader = FXMLLoader.load(getClass().getResource("/com/vynilbox/editArtistDisplay-Admin.fxml"));
            else throw new IOException();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) elem.getScene().getWindow();
        Scene scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }

    public default void goToScreen(Tela to, T elem, O obj) {

    }
}
