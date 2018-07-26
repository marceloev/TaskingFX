package com.taskingfx.impls;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JpaValidator<JpaEntity> {

    public List<JpaValidation> getClassFieldErrors(JpaEntity jpaEntity) {
        List<JpaValidation> validationList = new ArrayList<>();
        for (Field field : jpaEntity.getClass().getFields()) {
            JpaValidation jpaValidation = getFieldErrors(field);
            if (!jpaValidation.getErrors().isEmpty())
                validationList.add(jpaValidation);
        }
        return validationList;
    }

    public static JpaValidation getFieldErrors(Field field) {
        Column column = field.getAnnotation(Column.class);
        JpaValidation jpaValidation = new JpaValidation(field);
        if (column == null) {
            System.out.println(String.format("Coluna JPA não definida para field: %s.%s",
                    field.getClass().getName(),
                    field.getName()));
            return jpaValidation;
        } else {
            if (field.toString() == null || field.toString().isEmpty()) {
                if (!column.nullable()) {
                    jpaValidation.getErrors()
                            .add(String.format("%s não pode ser vazio", column.columnDefinition()));
                }
            }
            if (field.toString() != null && field.toString().length() > column.length()) {
                jpaValidation.getErrors()
                        .add(String.format("%s deve conter até %d dígitos",
                                column.columnDefinition(),
                                column.length()));
            }
        }
        return jpaValidation;
    }


}
