package com.taskingfx.impls;

import com.taskingfx.util.log.TaskingLog;

import javax.persistence.Column;
import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JpaValidator<JpaEntity> {

    private JpaEntity jpaEntity;
    private List<JpaValidation> jpaValidationList;

    public JpaValidator() {

    }

    public JpaValidator(JpaEntity jpaEntity) {
        this.setJpaEntity(jpaEntity);
    }

    public void validate() throws ValidationException {
        if (getJpaEntity() == null)
            throw new ValidationException("Não foi definido entidade para validação");
        getClassErrors(getJpaEntity());
        if (!getJpaValidationList().isEmpty())
            throw new ValidationException(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        if (getJpaEntity() == null ||
                getJpaValidationList() == null ||
                getJpaValidationList().isEmpty())
            return "";
        for (JpaValidation jpaValidation : getJpaValidationList())
            if (!jpaValidation.getErrors().isEmpty())
                jpaValidation.getErrors().forEach(erro -> strBuilder.append(erro + "\n"));
        return strBuilder.toString();
    }

    private void getClassErrors(JpaEntity jpaEntity) {
        List<JpaValidation> validationList = new ArrayList<>();
        for (Field field : jpaEntity.getClass().getDeclaredFields()) {
            JpaValidation jpaValidation = getFieldErrors(field, jpaEntity);
            if (!jpaValidation.getErrors().isEmpty())
                validationList.add(jpaValidation);
        }
        this.setJpaEntity(jpaEntity);
        this.setJpaValidationList(validationList);
    }


    private JpaValidation getFieldErrors(Field field, JpaEntity jpaEntity) {
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

    private final String NVL(Object value) {
        if (value == null)
            return "";
        else
            return value.toString();
    }

    public JpaEntity getJpaEntity() {
        return jpaEntity;
    }

    public void setJpaEntity(JpaEntity jpaEntity) {
        this.jpaEntity = jpaEntity;
    }

    public List<JpaValidation> getJpaValidationList() {
        return jpaValidationList;
    }

    public void setJpaValidationList(List<JpaValidation> jpaValidationList) {
        this.jpaValidationList = jpaValidationList;
    }
}
