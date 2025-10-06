package main.models;


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

    public static String formatarString(String str) {
        return str.toLowerCase().replace(" ", "");
    }

    public void adicionarLivro(Livro livroAdicionado) {
        livrosPorId.put(livroAdicionado.getIdLivro(), livroAdicionado);

        indiceLivrosPorTitulo.computeIfAbsent(
                formatarString(livroAdicionado.getTitulo()), (k) -> new ArrayList<>()).add(livroAdicionado);

        indiceLivrosPorAutor.computeIfAbsent(
                formatarString(livroAdicionado.getAutor()), (k) -> new ArrayList<>()).add(livroAdicionado);
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
        System.out.println("Livro devolvido com sucesso.");
    }

    public List<Livro> buscarPorAutor(String autorParcial) {
        String autorBuscado = formatarString(autorParcial);
        List<Livro> livrosEncontrados =  new ArrayList<>();
        for (Livro livro : livrosPorId.values()) {
            String autorLivro = formatarString(livro.getAutor());
            if (autorLivro.contains(autorBuscado)) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> buscarPorTitulo(String tituloParcial) {
        String tituloBuscado = formatarString(tituloParcial);
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : livrosPorId.values()) {
            String tituloLivro = formatarString(livro.getTitulo());
            if (tituloLivro.contains(tituloBuscado)) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
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

    public void listarUsuarios() {
        System.out.println("\n--- Usuarios ---");
        usuariosPorId.values().forEach(System.out::println);
    }

}
