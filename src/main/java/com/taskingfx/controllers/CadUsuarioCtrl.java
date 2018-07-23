package com.taskingfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.taskingfx.DAO.UserDAO;
import com.taskingfx.entitys.User;
import com.taskingfx.impls.TskController;
import com.taskingfx.model.dialogs.ModelDialog;
import com.taskingfx.model.dialogs.ModelDialogType;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CadUsuarioCtrl implements TskController {

    UserDAO service = new UserDAO();
    User user = new User();

    @FXML
    public JFXTextField txfLogin;
    public JFXTextField txfNome;
    public JFXTextField txfSobrenome;
    public JFXPasswordField pwfSenha;
    public JFXTextField txfEmail;
    public JFXTextField txfTelefone;
    public JFXButton btnCadastrar;
    public JFXButton btnVoltar;

    @Override
    public void appCalls() {
        btnCadastrar.setOnAction(e -> cadastrar());
        btnVoltar.setOnAction(e -> exit());
    }

    @Override
    public void properties() {
        user.setAtivo('S');
        txfLogin.textProperty().addListener((obs, oldV, newV) -> user.setLogin(newV));
        pwfSenha.textProperty().addListener((obs, oldV, newV) -> user.setSenha(newV));
        txfNome.textProperty().addListener((obs, oldV, newV) -> user.setNome(newV));
        txfSobrenome.textProperty().addListener((obs, oldV, newV) -> user.setSobrenome(newV));
        txfEmail.textProperty().addListener((obs, oldV, newV) -> user.setEmail(newV));
        txfTelefone.textProperty().addListener((obs, oldV, newV) -> user.setTelefone(newV));
    }

    private void cadastrar() {
        try {
            service.save(user);
        } catch (Exception ex) {
            new ModelDialog(ModelDialogType.Erro)
                    .show(ex.getMessage(), ex);
        }
    }

    private void exit() {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
}
