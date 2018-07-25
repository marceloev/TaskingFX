package com.taskingfx;

import com.taskingfx.entitys.User;
import com.taskingfx.impls.JpaValidation;
import com.taskingfx.impls.JpaValidator;
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
            User user = new User('S',
                    "MARCELOEV",
                    "marcelo26",
                    "teste",
                    "teste",
                    "teste",
                    "teste");
            JpaValidation validation = JpaValidator
                    .getFieldErrors(user.getClass().getDeclaredField("nome"),
                            user.getNome());
            validation.getErrors().forEach(error -> System.out.print(error));
        } catch (Exception ex) {
            new ModelDialog(ModelDialogType.Erro)
                    .show("Erro ao tentar construir tela de login",
                            ex);
        }
    }

}
