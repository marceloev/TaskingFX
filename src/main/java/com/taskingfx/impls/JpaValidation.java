package com.taskingfx.impls;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JpaValidation {

    private Field field;

    private List<String> errors = new ArrayList<>();

    public JpaValidation(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
