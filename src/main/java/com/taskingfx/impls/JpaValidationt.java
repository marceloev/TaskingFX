package com.taskingfx.impls;

import javafx.geometry.Bounds;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.Set;

public class JpaValidationt<JpaEntity> {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public void valid(JpaEntity jpaEntity) {
        try {
            Field field = jpaEntity.getClass().getDeclaredFields()[1];
            field.setAccessible(true);
            Object value = field.get(jpaEntity.getClass());
            Character typeValue = (Character) value;
            System.out.println("Foi: " + typeValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Set<ConstraintViolation<JpaEntity>> validation = validator.validate(jpaEntity);
        System.out.println("Val size = " + validation.size());
        validation.forEach(validation1 -> System.out.println(validation1.getMessage()));
        if (!validation.isEmpty()) {
            showValidationErrors(validation);
        } else {
            //validateAndSaveSubscriber();
        }

    }

    /*private void validateAndSaveSubscriber() {
        // we could validate the object as well....
        Subscriber subscriber = getSubscriberFromFields();
        String message;
        AlertType alertType;
        Set<Subscriber> subscribers = subscriberService.list();
        Predicate<Subscriber> subscriberFilter = s -> s.getEmail().get().equals(subscriber.getEmail().get());
        Optional<Subscriber> existingSubscriber = subscribers.stream().filter(subscriberFilter).findAny();
        if (existingSubscriber.isPresent()) {
            alertType = AlertType.ERROR;
            message = "It seems that you already subscribed to this. Try again with a diffent email.";
        } else {
            subscriberService.save(subscriber);
            alertType = AlertType.CONFIRMATION;
            message = "Congratulations! You are the " + (subscribers.size() + 1) + "° subscriber!";
        }
        showMessage(message, alertType);
    }

    private void showMessage(String message, AlertType alertType) {
        Alert dialogoErro = new Alert(alertType);
        dialogoErro.setTitle("Response from the system");
        dialogoErro.setContentText(message);
        dialogoErro.showAndWait();

    }*/

    private void showValidationErrors(Set<ConstraintViolation<JpaEntity>> validation) {
        // we are using reflection to get the field related to the validation error and show a messge
        // make sure the validated fields are public or the reflection won't work
        // or you can create public get methods etc...
        validation.forEach(v -> {
            String property = v.getPropertyPath().toString();
            Object object = v.getLeafBean();
            try {
                Field field = object.getClass().getField(property);
                Control control = (Control) field.get(object);
                configureToolTipForControl(control, v.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void configureToolTipForControl(Control control, String message) {
        control.setTooltip(new Tooltip(message));
        Bounds boundsInScene = control.localToScreen(control.getBoundsInLocal());
        control.getTooltip().show(control, boundsInScene.getMinX(), boundsInScene.getMaxY());
        control.getTooltip().setAutoHide(true);
    }
}
