package com.taskingfx.impls;

import javax.persistence.*;
import java.lang.reflect.Field;

public class JpaValidator {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("TaskingFX");
    private static final EntityManager entityManager =
            factory.createEntityManager();

    public static Boolean checkIfUnique(Field field, String value) {
        Query query = entityManager
                .createQuery(String.format("FROM %s\nWHERE 1 = 0",
                        field.getClass().getName()));
        return !query.getResultList().isEmpty();
    }

    public static JpaValidation getFieldErrors(Field field, String value) {
        /*Column column = User.class.getDeclaredField("email").getAnnotation(Column.class);
            System.out.print(column.name() + " | " + column.columnDefinition());*/
        Column column = field.getAnnotation(Column.class);
        JpaValidation jpaValidation = new JpaValidation(field);
        if (column == null) {
            System.out.println(String.format("Coluna JPA não definida para field: %s.%s",
                    field.getClass().getName(),
                    field.getName()));
            return jpaValidation;
        } else {
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
            if (column.unique()) {
                if (!checkIfUnique(field, value)) {
                    jpaValidation.getErrors()
                            .add(String.format("Valor duplicado para %s", column.columnDefinition()));
                }
            }
        }
        return jpaValidation;
    }


}
