package main;


import main.models.Biblioteca;
import main.models.Livro;
import main.models.Usuario;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicarInformacoes();

        int opcao;

        do {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        adicionarLivro();
                        break;
                    case 2:
                        adicionarUsuario();
                        break;
                    case 3:
                        realizarEmprestimo();
                        break;
                    case 4:
                        realizarDevoluao();
                        break;
                    case 5:
                        buscarPorTitulo();
                        break;
                    case 6:
                        buscarPorAutor();
                        break;
                    case 7:
                        listarLivrosDisponiveis();
                        break;
                    case 8:
                        listarLivros();
                        break;
                    case 9:
                        listarEmprestimos();
                        break;
                    case 10:
                        listarUsuarios();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
                opcao = -1; // Define uma opção inválida para continuar o loop
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu da Biblioteca ===");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Adicionar Usuário");
        System.out.println("3. Realizar Empréstimo");
        System.out.println("4. Realizar Devolução");
        System.out.println("5. Buscar Livro por Título");
        System.out.println("6. Buscar Livro por Autor");
        System.out.println("7. Listar Livros Disponíveis");
        System.out.println("8. Listar Livros");
        System.out.println("9. Listar Empréstimos Ativos");
        System.out.println("10. Listar Usuários");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarLivro() {
        System.out.println("\n--- Adicionar Novo Livro ---");

        System.out.print("Digite o Título: ");
        scanner.nextLine();
        String titulo = scanner.nextLine();

        System.out.print("Digite o Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Digite o ano de publicação: ");
        int anoPublicacao = scanner.nextInt();
        scanner.nextLine();

        biblioteca.adicionarLivro(new Livro(titulo, autor, anoPublicacao));
        System.out.println("Livro adicionado com sucesso!");

    }

    private static void adicionarUsuario() {
        System.out.println("\n--- Adicionar Novo Usuário ---");

        System.out.print("Digite o seu nome: ");
        scanner.nextLine();
        String nome = scanner.nextLine();

        System.out.print("Digite o seu email: ");
        String email = scanner.nextLine();

        System.out.print("Digite o seu telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite a sua idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        biblioteca.adicionarUsuario(new Usuario(nome, email, telefone, idade));
        System.out.println("Usuario adicionado com sucesso!");

    }

    private static void realizarEmprestimo() {
        System.out.println("\n--- Realizar Empréstimo ---");

        System.out.print("Digite o ID do Livro: ");
        scanner.nextLine();
        String idLivro = scanner.nextLine();

        System.out.print("Digite o ID do Usuário: ");
        String idUsuario = scanner.nextLine();

        biblioteca.realizarEmprestimo(idLivro, idUsuario);

    }

    private static void realizarDevoluao() {
        System.out.println("\n--- Realizar Devolução ---");

        System.out.println("Digite o ID do Livro: ");
        scanner.nextLine();
        String idLivro = scanner.nextLine();

        biblioteca.realizarDevolucao(idLivro);
    }

    private static void buscarPorTitulo() {
        System.out.println("\n--- Buscar livro por título ---");

        System.out.print("Digite o titulo do livro: ");
        scanner.nextLine();
        String tituloLivro = scanner.nextLine();

        List<Livro> resultados = biblioteca.buscarPorTitulo(tituloLivro);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            System.out.println("Livros encontrados: ");
            resultados.forEach(System.out::println);
        }
    }

    private static void buscarPorAutor() {
        System.out.println("\n--- Buscar livro por autor ---");

        System.out.print("Digite o nome do autor: ");
        scanner.nextLine();
        String nomeAutor = scanner.nextLine();

        List<Livro> resultados = biblioteca.buscarPorAutor(nomeAutor);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            System.out.println("Livros encontrados: ");
            resultados.forEach(System.out::println);
        }
    }

    private static void listarLivrosDisponiveis() {
        biblioteca.listarLivrosDisponiveis();
    }

    private static void listarLivros() {
        biblioteca.listarLivros();
    }

    private static void listarEmprestimos() {
        biblioteca.listarEmprestimos();
    }

    private static void listarUsuarios() {
        biblioteca.listarUsuarios();
    }

    //Apenas para facilitar os testes
    private static void inicarInformacoes() {
        biblioteca.adicionarLivro(new Livro("O Código Da Vinci", "Dan Brown", 2003));
        biblioteca.adicionarLivro(new Livro("O Hobbit", "J.R.R. Tolkien", 1937));
        biblioteca.adicionarLivro(new Livro("1984", "George Orwell", 1949));
        biblioteca.adicionarLivro(new Livro("A Revolução dos Bichos", "George Orwell", 1945));
        biblioteca.adicionarLivro(new Livro("Alice no País das Maravilhas", "Lewis Carroll", 1865));

        biblioteca.adicionarUsuario(new Usuario("Leonardo Feitosa Peres", "leonardofeitosa2007@gmail.com", "(15) 99662-1461", 18));
        biblioteca.adicionarUsuario(new Usuario("Julia Bernini Santana", "juhbernini86@gmail.com", "(15) 98839-7955", 19));
    }

}