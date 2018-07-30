package com.taskingfx.model.fields;

public @interface JpaObject {

    String descricao() default "<Campo Sem Descrição>";

    ModelFieldType tipo() default ModelFieldType.Texto;

}
