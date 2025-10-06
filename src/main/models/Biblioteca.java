package main;

import main.models.Emprestimo;
import main.models.Livro;
import main.models.Usuario;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Biblioteca {

    private Map<String, Livro> livrosPorId;
    private Map<String, Usuario> usuariosPorId;
    private List<Emprestimo> emprestimosAtivos;

    private Map<String, List<Livro>> indiceLivrosPorAutor;
    private Map<String, List<Livro>> indiceLivrosPorTitulo;

    public Biblioteca() {
        this.livrosPorId = new HashMap<>();
        this.usuariosPorId = new HashMap<>();
        this.emprestimosAtivos = new ArrayList<>();
        this.indiceLivrosPorAutor = new HashMap<>();
        this.indiceLivrosPorTitulo = new HashMap<>();
    }

    public void adicionarLivro(Livro livroAdicionado) {
        livrosPorId.put(livroAdicionado.getIdLivro(), livroAdicionado);

        String tituloFormatado = livroAdicionado.getTitulo().toLowerCase().replace(" ", "");
        indiceLivrosPorTitulo.computeIfAbsent(tituloFormatado, (k) -> new ArrayList<>()).add(livroAdicionado);

        String autorFormatado = livroAdicionado.getAutor().toLowerCase().replace(" ", "");
        indiceLivrosPorAutor.computeIfAbsent(autorFormatado, (k) -> new ArrayList<>()).add(livroAdicionado);
    }

    public void adicionarUsuario(Usuario usuarioAdicionado) {
        usuariosPorId.put(usuarioAdicionado.getIdUsuario(), usuarioAdicionado);
    }

    public void realizarEmprestimo(String idLivro, String idUsuario) {
        Livro livroEmprestado = livrosPorId.get(idLivro);
        Usuario usuarioEmprestimo = usuariosPorId.get(idUsuario);

        if (livroEmprestado == null || usuarioEmprestimo == null) {
            System.out.println("Livro ou usuário não encontrado.");
            return;
        }

        if (livroEmprestado.getStatus()) {
            System.out.println("Livro já emprestado.");
            return;
        }

        livroEmprestado.setStatus(true);
        Emprestimo emprestimo = new Emprestimo(livroEmprestado, usuarioEmprestimo);
        emprestimosAtivos.add(emprestimo);
        System.out.println("Emprestimo realizado com sucesso.");
    }

    public void realizarDevolucao(String idLivro) {
        Livro livroDevolvido = livrosPorId.get(idLivro);

        if (livroDevolvido == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (!livroDevolvido.getStatus()) {
            System.out.println("Livro já devolvido.");
            return;
        }

        livroDevolvido.setStatus(false);
        emprestimosAtivos.removeIf(e -> e.getLivro().getIdLivro().equals(idLivro));
    }

    public List<Livro> buscarPorAutor(String autor) {
        return indiceLivrosPorAutor.getOrDefault(autor.toLowerCase() , new ArrayList<>());

    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return indiceLivrosPorTitulo.getOrDefault(titulo.toLowerCase(), new ArrayList<>());
    }

    public void listarLivros() {
        System.out.println("\n--- Catálogo de Livros ---");
        livrosPorId.values().forEach(System.out::println);
    }

    public void listarLivrosDisponiveis() {
        System.out.println("\n--- Livros Disponiveis ---");
        livrosPorId.values().stream().filter(l -> !l.getStatus()).forEach(System.out::println);
    }

    public void listarEmprestimos() {
        System.out.println("\n--- Empréstimos Ativos ---");
        emprestimosAtivos.forEach(System.out::println);
    }
}
