package main.models;

public class Usuario {
    private static int proximoId = 1;

    private String nome;
    private String email;
    private String telefone;
    private int idade;
    private int idUsuario;

    public Usuario(String nome, String email, String telefone, int idade) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
        this.idUsuario = proximoId;
        proximoId++;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getIdade() {
        return idade;
    }

    public String getIdUsuario() {
        return String.valueOf(idUsuario);
    }

    @Override
    public String toString() {
        return "Usuário ->" +
                "ID: " + idUsuario +
                " | Nome: " + nome +
                " | Email: " + email +
                " | Telefone: " + telefone +
                " | Idade: " + idade;
    }
}


