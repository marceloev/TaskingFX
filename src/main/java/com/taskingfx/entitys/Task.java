package com.taskingfx.entitys;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity(name = "Task")
@NamedQueries({
        @NamedQuery(name = "Task.findPendentes",
                query = "SELECT T FROM Task T WHERE T.usuario.codigo = :P_CODUSU AND T.resolvido = 'N'"),
        @NamedQuery(name = "Task.findAll",
                query = "SELECT T FROM Task T WHERE T.usuario.codigo = :P_CODUSU")
})
@Table(name = "TTASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TTASK")
    @Column(name = "CODTASK")
    private int codigo;

    @ManyToOne(optional = false, targetEntity = User.class)
    //@NotNull(message = "Usuário da Tarefa deve ser informado")
    @JoinColumn(name = "CODUSU", nullable = false,
            foreignKey = @ForeignKey(name = "FK_TTASK_CODUSU"))
    private User usuario;

    //@NotNull(message = "Tarefa deve conter resolvido S ou N")
    @Column(name = "RESOLVIDO", nullable = false)
    private Character resolvido = 'N';

    //@NotNull(message = "Data/Hora da Tarefa deve ser informado")
    @Column(name = "DHTASK", nullable = false)
    private Date data = Date.from(Instant.now());

    //@Length(max = 30, message = "Descrição da Tarefa deve conter até 30 dígitos")
    //@NotNull(message = "Descrição da Tarefa deve ser informado")
    @Column(name = "DESCRTASK", length = 30, nullable = false)
    private String descricao;

    //@Length(max = 4000, message = "Observação da Tarefa deve conter até 4000 dígitos")
    @Column(name = "OBSTASK", length = 4000)
    private String observacao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Character getResolvido() {
        return resolvido;
    }

    public void setResolvido(Character resolvido) {
        this.resolvido = resolvido;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Task{" +
                "codigo=" + codigo +
                ", usuario=" + usuario +
                ", resolvido=" + resolvido +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
