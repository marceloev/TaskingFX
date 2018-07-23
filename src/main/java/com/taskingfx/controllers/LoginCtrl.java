package com.taskingfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.taskingfx.DAO.UserDAO;
import com.taskingfx.impls.TskController;
import javafx.fxml.FXML;

public class LoginCtrl implements TskController {

    UserDAO userDAO = new UserDAO();

    @FXML
    public JFXTextField txfLogin;
    public JFXPasswordField pwfSenha;
    public JFXCheckBox ckbManterLogado;
    public JFXButton btnLogin;

    @Override
    public void appCalls() {
        txfLogin.setOnAction(e -> startLogin());
        pwfSenha.setOnAction(e -> startLogin());
        btnLogin.setOnAction(e -> startLogin());
    }

    private void startLogin() {
        userDAO.authenticateUser(txfLogin.getText(),
                pwfSenha.getText());
    }
}
