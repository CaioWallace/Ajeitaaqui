/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.entity;

/**
 *
 * @author Edvaldo
 */
public class Usuario {
    
    private int id;
    private String usuario;
    private String cpf;
    private String cep;
    private String email;
    private String fone;
    private String login;
    private String senha;
    private String perfil;

    private Usuario() { }
    
    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }

    public String getFone() {
        return fone;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public static class Builder {
        private Usuario usuario = new Usuario();
        
        public Builder setId(int id) {
            this.usuario.id = id;
            return this;
        }

        public Builder setUsuario(String usuario) {
            this.usuario.usuario = usuario;
            return this;
        }

        public Builder setCpf(String cpf) {
            this.usuario.cpf = cpf;
            return this;
        }

        public Builder setCep(String cep) {
            this.usuario.cep = cep;
            return this;
        }

        public Builder setEmail(String email) {
            this.usuario.email = email;
            return this;
        }

        public Builder setFone(String fone) {
            this.usuario.fone = fone;
            return this;
        }

        public Builder setLogin(String login) {
            this.usuario.login = login;
            return this;
        }

        public Builder setSenha(String senha) {
            this.usuario.senha = senha;
            return this;
        }

        public Builder setPerfil(String perfil) {
            this.usuario.perfil = perfil;
            return this;
        }
        
        public Usuario build() {
            if (usuario.getId() == 0 ||
                usuario.getUsuario() == null || usuario.getUsuario().isEmpty() ||
                usuario.getCpf()== null || usuario.getCpf().isEmpty() ||
                usuario.getCep()== null || usuario.getCep().isEmpty() ||
                usuario.getEmail()== null || usuario.getEmail().isEmpty() ||
                usuario.getFone()== null || usuario.getFone().isEmpty() ||
                usuario.getLogin()== null || usuario.getLogin().isEmpty() ||
                usuario.getSenha()== null || usuario.getSenha().isEmpty() ||
                usuario.getPerfil()== null || usuario.getPerfil().isEmpty()) {
                throw new RuntimeException("Preencha todos os campos de Usuário");
            }
            return usuario;
        }
    }
    
}
