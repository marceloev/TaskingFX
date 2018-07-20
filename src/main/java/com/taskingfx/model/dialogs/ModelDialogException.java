package com.taskingfx.model.dialogs;

import com.taskingfx.util.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ModelDialogException extends ModelDialog {

    private final ImageView defaultErroIcon = new ImageView(new Image(Paths.Imgs.concat("error.png")));

    public ModelDialogException(String mensagem) {
        super(AlertType.ERROR);
        setTitle("Sistema TaskingFX");
        setGraphic(defaultErroIcon);
        setHeaderText(null);
        setContentText(mensagem);
        setTip(null);
    }

    public ImageView resolveDefaultImage(ImageView imageView) {
        if (imageView == null)
            return defaultErroIcon;
        else
            return imageView;
    }
}
