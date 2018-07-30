package com.taskingfx.model.fields;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextFormatter;

public class ModelField extends JFXTextField {

    int fieldSize = -1;
    ModelFieldType modelFieldType = ModelFieldType.Texto;

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
        if (fieldSize > -1) {
            this.lengthProperty().addListener((obs, oldV, newV) -> {
                if (newV.intValue() > fieldSize)
                    this.deleteText(fieldSize, this.getLength());
            });
        }
    }

    public ModelFieldType getModelFieldType() {
        return modelFieldType;
    }

    public void setModelFieldType(ModelFieldType modelFieldType) {
        this.modelFieldType = modelFieldType;
        switch (modelFieldType) {
            case Upper:
                this.setTextFormatter(upperFormatter());
                break;
            case Inteiro:
                break;
            case Texto:
                break;
            case Texto_Grande:
                break;
            case Data:
                break;
            case Data_Hora:
                break;
            case Email:
                break;
        }
    }

    public TextFormatter upperFormatter() {
        return new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        });
    }
}
