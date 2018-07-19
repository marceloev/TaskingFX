package com.taskingfx.entitys;

import javax.persistence.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@Entity(name = "Anexo")
@Table(name = "TANX")
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TANX")
    @Column(name = "CODANX")
    private int codigo;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, targetEntity = User.class)
    //@NotNull(message = "Usuário do Anexo deve ser informado")
    @JoinColumn(name = "CODUSU", nullable = false,
            foreignKey = @ForeignKey(name = "FK_TANX_CODUSU"))
    private User usuario;

    @ManyToOne(cascade = CascadeType.ALL, optional = true, targetEntity = Task.class)
    @JoinColumn(name = "CODTASK", nullable = true,
            foreignKey = @ForeignKey(name = "FK_TANX_CODTASK"))
    private Task task;

    //@Length(max = 25, message = "Descrição do Anexo deve conter até 25 dígitos")
    //@NotNull(message = "Descrição do Anexo deve ser informado")
    @Column(name = "DESCRANX", length = 25, nullable = false)
    private String descricao;

    //@Length(max = 10, message = "Tipo do Anexo deve conter até 10 dígitos")
    //@NotNull(message = "Tipo do Anexo deve ser informado (Ex.: jpg, jpeg,...)")
    @Column(name = "TIPOANX", length = 10, nullable = false)
    private String tipo;

    //@NotNull(message = "Anexo deve ser informado")
    @Column(name = "ANEXO", nullable = false)
    private Byte[] anexo;

    //@Length(max = 1000, message = "Observação do Anexo deve conter até 1000 dígitos")
    @Column(name = "OBSANX", length = 1000)
    private String observacao;

    //@NotNull(message = "Data de Anexamento deve ser informada")
    @Column(name = "DHANX", nullable = false)
    private Date data = Date.from(Instant.now());

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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Byte[] getAnexo() {
        return anexo;
    }

    public void setAnexo(Byte[] anexo) {
        this.anexo = anexo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Anexo{" +
                "codigo=" + codigo +
                ", usuario=" + usuario +
                ", task=" + task +
                ", descricao='" + descricao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", anexo=" + Arrays.toString(anexo) +
                ", observacao='" + observacao + '\'' +
                ", data=" + data +
                '}';
    }
}
