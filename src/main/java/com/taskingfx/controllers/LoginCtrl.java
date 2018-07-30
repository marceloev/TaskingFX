package com.taskingfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.taskingfx.DAO.UserDAO;
import com.taskingfx.TaskingFX;
import com.taskingfx.impls.TskController;
import com.taskingfx.model.dialogs.ModelDialog;
import com.taskingfx.model.dialogs.ModelDialogType;
import com.taskingfx.util.Paths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginCtrl implements TskController {

    UserDAO userDAO = new UserDAO();

    @FXML
    public JFXTextField txfLogin;
    public JFXPasswordField pwfSenha;
    public JFXCheckBox ckbManterLogado;
    public JFXButton btnLogin;
    public JFXButton btnCadastrar;

    @Override
    public void appCalls() {
        txfLogin.setOnAction(e -> startLogin());
        pwfSenha.setOnAction(e -> startLogin());
        btnLogin.setOnAction(e -> startLogin());
        btnCadastrar.setOnAction(e -> showCadastroForm());
    }

    private void startLogin() {
        userDAO.authenticateUser(txfLogin.getText(),
                pwfSenha.getText());
    }

    public static void showCadastroForm() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(TaskingFX.class.getResource(Paths.FXMLs.concat("cadUsuario.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image(Paths.Imgs.concat("Tasking.png")));
            stage.setTitle("Sistema TaskingFX");
            stage.show();
        } catch (Exception ex) {
            new ModelDialog(ModelDialogType.Erro)
                    .show("Erro ao tentar construir tela de cadastro de usuário",
                            ex);
        }
    }
}
