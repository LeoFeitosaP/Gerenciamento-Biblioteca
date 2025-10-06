package main.models;

public class Livro {
    private static int proximoId = 1;

    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean emprestado;
    private int idLivro;

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.emprestado = false;
        this.idLivro = proximoId;
        proximoId++;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIdLivro() {
        return String.valueOf(idLivro);
    }

    public boolean getStatus() {
        return emprestado;}

    public void setStatus(boolean emprestado) {
        this.emprestado = emprestado;
    }

    @Override
    public String toString() {
        String mensagemStatus = "";
        if (emprestado) {
            mensagemStatus = "Emprestado";
        } else {
            mensagemStatus = "Disponível";
        }

        return "Livro -> " +
                "ID: " + idLivro +
                " | Titulo: " + titulo +
                " | Autor: " + autor +
                " | Ano de publicação: " + anoPublicacao +
                " | Status: " + mensagemStatus;
    }
}
