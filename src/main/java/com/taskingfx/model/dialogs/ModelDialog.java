package com.taskingfx.model.dialogs;

import com.taskingfx.util.Paths;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

public class ModelDialog {

    private ModelDialogType modelDialogType;
    private String titulo, mensagem, tip;
    private Throwable throwable;

    public ModelDialog(ModelDialogType modelDialogType) {
        setModelDialogType(modelDialogType);
    }

    public void show() {
        Alert dialogo = new Alert(getDialogType());
        dialogo.setTitle("Sistema TaskingFX");
        dialogo.setHeaderText(titulo);
        dialogo.setContentText(mensagem);
        createExpandableContent(dialogo);
        Stage stage = (Stage) dialogo.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Paths.Imgs.concat("tasking.png")));
        dialogo.show();
    }

    public void show(String mensagem) {
        setMensagem(mensagem);
        show();
    }

    public void show(String mensagem, String tip) {
        setMensagem(mensagem);
        setTip(tip);
        show();
    }

    public void show(String mensagem, Throwable throwable) {
        setMensagem(mensagem);
        setThrowable(throwable);
        show();
    }

    private void createExpandableContent(Alert dialogo) {
        if (getThrowable() != null || getTip() != null) {
            GridPane gridPane = GridPaneBuilder.create()
                    .maxWidth(Double.MAX_VALUE)
                    .maxHeight(Double.MAX_VALUE)
                    .build();
            Label label = LabelBuilder.create()
                    .text("Veja aqui o caminho completo do erro:")
                    .textFill(Color.BLACK)
                    .font(Font.font("System", FontPosture.ITALIC, 14))
                    .build();
            TextArea textArea = TextAreaBuilder.create()
                    .text("Pendente")
                    .maxWidth(Double.MAX_VALUE)
                    .maxHeight(Double.MAX_VALUE)
                    .editable(false)
                    .build();
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            gridPane.add(label, 0, 0);
            gridPane.add(textArea, 0, 1);
            dialogo.getDialogPane().setExpandableContent(gridPane);
            dialogo.getDialogPane().maxWidth(Double.MAX_VALUE);
            if (getThrowable() != null) {
                CharArrayWriter charArrayWriter = new CharArrayWriter();
                PrintWriter printWriter = new PrintWriter(charArrayWriter);
                getThrowable().printStackTrace(printWriter);
                printWriter.close();
                textArea.setText(charArrayWriter.toString());
            } else if (getTip() != null) {
                textArea.setText(getTip());
            }
        }
    }

    public ModelDialogType getModelDialogType() {
        return modelDialogType;
    }

    public void setModelDialogType(ModelDialogType modelDialogType) {
        this.modelDialogType = modelDialogType;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public Alert.AlertType getDialogType() {
        switch (getModelDialogType()) {
            case Info:
                return Alert.AlertType.INFORMATION;
            case Alerta:
                return Alert.AlertType.WARNING;
            case Erro:
                return Alert.AlertType.ERROR;
            default:
                return null;
        }
    }
}
