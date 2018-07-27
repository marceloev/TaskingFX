package com.taskingfx.impls;

import com.taskingfx.util.log.TaskingLog;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JpaValidator<JpaEntity> {

    public List<JpaValidation> getClassFieldErrors(JpaEntity jpaEntity) {
        List<JpaValidation> validationList = new ArrayList<>();
        for (Field field : jpaEntity.getClass().getDeclaredFields()) {
            JpaValidation jpaValidation = getFieldErrors(field, jpaEntity);
            if (!jpaValidation.getErrors().isEmpty())
                validationList.add(jpaValidation);

        }
        return validationList;
    }

    public String getClassFieldErrosStringBuilder(List<JpaValidation> jpaValidations) {
        StringBuilder strBuilder = new StringBuilder();
        for (JpaValidation jpaValidation : jpaValidations) {
            if (!jpaValidation.getErrors().isEmpty()) {
                jpaValidation.getErrors().forEach(erro -> strBuilder.append(erro + "\n"));
            }
        }
        return strBuilder.toString();
    }

    public JpaValidation getFieldErrors(Field field, JpaEntity jpaEntity) {
        Column column = field.getAnnotation(Column.class);
        JpaValidation jpaValidation = new JpaValidation(field);
        if (column == null) {
            System.out.println(String.format("Coluna JPA não definida para field: %s.%s",
                    field.getClass().getName(),
                    field.getName()));
            return jpaValidation;
        } else {
            try {
                field.setAccessible(true);
                String value = NVL(field.get(jpaEntity));
                if (value == null || value.isEmpty()) {
                    if (!column.nullable()) {
                        jpaValidation.getErrors()
                                .add(String.format("%s não pode ser vazio", column.columnDefinition()));
                    }
                }
                if (value != null && value.length() > column.length()) {
                    jpaValidation.getErrors()
                            .add(String.format("%s deve conter até %d dígitos",
                                    column.columnDefinition(),
                                    column.length()));
                }
            } catch (Exception ex) {
                jpaValidation.getErrors().clear();
                TaskingLog.gravaErro(jpaEntity.getClass(),
                        String.format("Erro ao tentar mapear entidade JPA: %s", field.toString(), ex));
                ex.printStackTrace();
            }
        }
        return jpaValidation;
    }

    public final String NVL(Object value) {
        if (value == null)
            return "";
        else
            return value.toString();
    }
}
