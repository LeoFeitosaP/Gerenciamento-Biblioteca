package models;

import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = this.dataDevolucao.plusDays(2);
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setLivro(Livro livro) {
        this.livro.setEmprestado(true);
    }

    @Override
    public String toString() {
        return "Empréstimo ->" +
                " | Livro: " + livro.getTitulo() +
                " | Usuário: " + usuario.getNome() +
                " | Data do Empréstimo: " + dataEmprestimo +
                " | Data de Devolução: " + dataDevolucao;
    }
}


