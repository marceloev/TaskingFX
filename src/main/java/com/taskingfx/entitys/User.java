package com.taskingfx.entitys;

import javax.persistence.*;

@Entity(name = "User")
@NamedQueries({
        @NamedQuery(name = "User.findByLogin",
                query = "SELECT U FROM User U WHERE U.login = :P_LOGIN")
})
@Table(name = "TUSER", uniqueConstraints =
@UniqueConstraint(columnNames = {"CODUSU"}, name = "empresa_uk"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TUSER")
    @Column(name = "CODUSU")
    private int codigo;

    @Column(name = "ATIVO", nullable = false)
    private Character ativo = 'S';

    @Column(name = "LOGIN", length = 20, nullable = false)
    private String login;

    @Column(name = "SENHA", length = 80, nullable = false)
    private String senha;

    @Column(name = "NOME", length = 25, nullable = false, unique = true)
    private String nome;

    @Column(name = "SOBRENOME", length = 60)
    private String sobrenome;

    @Column(name = "EMAIL", length = 60, nullable = false)
    private String email;

    @Column(name = "TELEFONE", length = 11, nullable = false)
    private String telefone;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public User() {

    }

    public User(Character ativo, String login, String senha, String nome, String sobrenome, String email, String telefone) {
        this.ativo = ativo;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
    }

    public User(int codigo, Character ativo, String login, String senha, String nome, String sobrenome, String email, String telefone) {
        this.codigo = codigo;
        this.ativo = ativo;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "User{" +
                "codigo=" + codigo +
                ", ativo=" + ativo +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
