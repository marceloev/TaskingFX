package com.taskingfx.model.dialogs;

import com.taskingfx.util.Paths;
import com.taskingfx.util.log.TaskingLog;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class ModelDialog extends Alert {

    private String tip;
    private Throwable throwable;
    private ImageView icone = new ImageView(new Image(Paths.Imgs.concat("tasking.png")));

    public ModelDialog(AlertType alertType) {
        super(alertType);
    }

    public ModelDialog(AlertType alertType, String titulo, String mensagem, String tip, ImageView icone) {
        super(alertType);
        setTip(tip);
        setTitle("Sistema TaskingFX");
        setHeaderText(titulo);
        setContentText(mensagem);
        setGraphic(icone);
        if (icone != null)
            setIcone(icone);
    }

    public void raise() {
        if (getTip() != null) {
            GridPane gridPane = GridPaneBuilder.create()
                    .maxWidth(Double.MAX_VALUE)
                    .maxHeight(Double.MAX_VALUE)
                    .build();
            Label label = LabelBuilder.create()
                    .text("Veja aqui dicas de como solucionar o problema:")
                    .textFill(Color.BLACK)
                    .font(Font.font("System", FontPosture.ITALIC, 14))
                    .build();
            TextArea textArea = TextAreaBuilder.create()
                    .text(getTip())
                    .maxWidth(Double.MAX_VALUE)
                    .maxHeight(Double.MAX_VALUE)
                    .editable(false)
                    .build();
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            gridPane.add(label, 0, 0);
            gridPane.add(textArea, 0, 1);
            this.getDialogPane().setExpandableContent(gridPane);
            this.getDialogPane().maxWidth(Double.MAX_VALUE);
        }
        gravaNoLog();
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(this.getIcone().getImage());
        this.show();
    }

    public void gravaNoLog() {
        switch (this.getAlertType()) {
            case NONE:
            case INFORMATION:
            case CONFIRMATION:
                if (getTip() != null)
                    TaskingLog.gravaInfo(this.getClass(), this.getContentText(), this.getTip());
                else
                    TaskingLog.gravaInfo(this.getClass(), this.getContentText());
                break;
            case WARNING:
                if (getTip() != null)
                    TaskingLog.gravaAlerta(this.getClass(), this.getContentText(), this.getTip());
                else
                    TaskingLog.gravaAlerta(this.getClass(), this.getContentText());
                break;
            case ERROR:
                if (getThrowable() != null)
                    TaskingLog.gravaErro(this.getClass(), this.getContentText(), this.getThrowable());
                else
                    TaskingLog.gravaErro(this.getClass(), this.getContentText());
                break;
        }
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

    public ImageView getIcone() {
        return icone;
    }

    public void setIcone(ImageView icone) {
        this.icone = icone;
    }
}
