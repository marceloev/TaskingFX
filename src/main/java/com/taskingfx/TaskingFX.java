package com.taskingfx;

import com.taskingfx.model.dialogs.ModelDialog;
import com.taskingfx.model.dialogs.ModelDialogType;
import com.taskingfx.util.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TaskingFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(TaskingFX.class.getResource(Paths.FXMLs.concat("login.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image(Paths.Imgs.concat("Tasking.png")));
            stage.setTitle("Sistema TaskingFX");
            stage.show();
        } catch (Exception ex) {
            new ModelDialog(ModelDialogType.Erro)
                    .show("Erro ao tentar construir tela de login",
                            ex);
        }
    }

}
