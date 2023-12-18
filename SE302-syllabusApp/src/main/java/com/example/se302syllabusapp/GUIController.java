package com.example.se302syllabusapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    @FXML
    private VBox parentVBox;
    @FXML
    Stage popup = new Stage();



    FileManager fileManager;
    // Todo bunlari dosyala
    private Stage primaryStage;
    Controllers controllers;
    VersionController versionController;

    public GUIController(){
    }



    public void importSyllabus(ActionEvent event){


        File storagePath = new File("storage");
        if (!storagePath.exists()) {
            System.out.println("Storage file does not exist!");
            return;
        }

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose JSON File");
        chooser.setInitialDirectory(storagePath);
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
        File selectedFile = chooser.showOpenDialog(new Popup());

        controllers.fileImport("en","CE45",selectedFile);

    }

    public void addingSyllabus() {

        FXMLLoader syllabusLoader = new FXMLLoader(getClass().getResource("SyllabusSheet.fxml"));

        try {
            Node syllabusSheet = syllabusLoader.load();

            if (!parentVBox.getChildren().isEmpty())
                parentVBox.getChildren().remove(1);

            parentVBox.getChildren().add(syllabusSheet);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void compareVersions(){
        if (popup != null) {
            System.out.println(popup.getTitle());
            popup.close();
        }
        FXMLLoader compareLoader = new FXMLLoader(getClass().getResource("ComparePage.fxml"));
        System.out.println(parentVBox);

        try {
            Node comparePage = compareLoader.load();

            if (!parentVBox.getChildren().isEmpty())
                parentVBox.getChildren().remove(1);

            parentVBox.getChildren().add(comparePage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void compareVersionsPopup(){
        FXMLLoader popCompare = new FXMLLoader(getClass().getResource("comparePopup.fxml"));
        try {
            Parent comparePopup = popCompare.load();
            popup.initOwner(getPrimaryStage());
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Compare Versions");
            popup.setResizable(false);
            popup.setScene(new Scene(comparePopup));


            popup.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }





    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }
    // Todo bunlari dosyala


    public Controllers getControllers() {
        return controllers;
    }

    public void setControllers(Controllers controllers) {
        this.controllers = controllers;
    }

    public VersionController getVersionController() {
        return versionController;
    }

    public void setVersionController(VersionController versionController) {
        this.versionController = versionController;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
