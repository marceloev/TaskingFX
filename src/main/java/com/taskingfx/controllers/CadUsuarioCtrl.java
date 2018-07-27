package com.taskingfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.taskingfx.DAO.UserDAO;
import com.taskingfx.entitys.User;
import com.taskingfx.impls.JpaValidator;
import com.taskingfx.impls.TskController;
import com.taskingfx.model.dialogs.ModelDialog;
import com.taskingfx.model.dialogs.ModelDialogType;
import com.taskingfx.util.log.TaskingLog;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.xml.bind.ValidationException;

public class CadUsuarioCtrl implements TskController {

    UserDAO service = new UserDAO();
    User user = new User();
    JpaValidator<User> userJpaValidator = new JpaValidator<>(user);

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
            userJpaValidator.validate();
            if (service.findIfAlreadyExists(user.getLogin(), user.getCodigo()))
                throw new ValidationException("Já existe um usuário cadastrado para este login");
            service.save(user);
            new ModelDialog(ModelDialogType.Info).show("Usuário cadastrado com sucesso");
            exit();
        } catch (ValidationException ex) {
            new ModelDialog(ModelDialogType.Erro).show(ex.getMessage());
            TaskingLog.gravaErro(this.getClass(), "Erro de validação em " + user.getClass().getName(), ex);
        } catch (Exception ex) {
            new ModelDialog(ModelDialogType.Erro).show(ex.getMessage());
            TaskingLog.gravaErro(this.getClass(), "Erro de validação em " + user.getClass().getName(), ex);
        }
    }

    private void exit() {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
}
