package org.unifan.catalogolivros;
import java.util.Scanner;

import org.unifan.catalogolivros.catalogo.BST;
import org.unifan.catalogolivros.catalogo.Livro;

public class CatalogoLivros {

    public static void main(String[] args) {
        BST arvore = new BST();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Catálogo de Livros!");
        System.out.println("Você pode inserir livros e buscar por ID.");
        System.out.println("Para inserir um livro, informe o ID, título e autor.");
        System.out.println("Para buscar um livro, informe o ID do livro.");

        while (true) {
            System.out.println("\n------------------Escolha a operação------------------");
            System.out.println("1. Inserir livro");
            System.out.println("2. Inserir livros automaticamente (testes)");
            System.out.println("3. Buscar livro por ID");
            System.out.println("4. Listar livros em ordem");
            System.out.println("5. Listar livros em pré-ordem");
            System.out.println("6. Listar livros em pós-ordem");
            System.out.println("7. Listar livros em largura");
            System.out.println("8. Remover livro por ID");
            System.out.println("9. Sair");

            System.out.print("Digite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (escolha) {
                case 1 -> {

                    System.out.println("------------------------------------------------------");
                    int n = -1;

                    while (true) {
                        System.out.print("Informe a quantidade de livros para inserir: ");

                        if (scanner.hasNextInt()){
                            n = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Quantidade inválida, deve ser um número inteiro positivo! Tente novamente.");
                            scanner.nextLine(); // consumir entrada inválida
                        }
                    }


                    for (int i = 0; i < n; i++) {
                        while (true) {
                            int id = -1;
                            System.out.print("Informe o ID do livro(" + (i + 1) + "): ");
                            if (scanner.hasNextInt()) {
                                id = scanner.nextInt();
                                scanner.nextLine();
                            } else {
                                System.out.println("ID inválido, deve ser um número inteiro positivo! Tente novamente.");
                                scanner.nextLine(); // consumir entrada inválida
                                continue;
                            }

                            if (id > 0 && arvore.buscar(id) == null) {
                                // ID válido e único
                                System.out.print("Informe o título do livro: ");
                                String titulo = scanner.nextLine();

                                System.out.print("Informe o autor do livro: ");
                                String autor = scanner.nextLine();

                                Livro livro = new Livro(autor, id, titulo);
                                arvore.inserir(livro);
                                break; // sai do while se for válido e único
                            } else {
                                System.out.println("ID inválido ou já existente. Tente novamente.");
                            }
                        }
                    }
                }

                case 2 -> {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Inserindo livros automaticamente...");
                    arvore.inserirLivrosAuto();
                }

                case 3 -> {
                    System.out.println("------------------------------------------------------");
                    while (true) {
                        int idBusca = -1;
                        System.out.print("Informe o ID do livro a ser buscado: ");
                        if (scanner.hasNextInt()) {
                            idBusca = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("ID inválido, deve ser um número inteiro positivo! Tente novamente.");
                            System.out.println("Caso queira sair, digite 0.");
                            scanner.nextLine(); // consumir entrada inválida
                            continue;
                        }

                        Livro livroEncontrado = arvore.buscar(idBusca);

                        if (livroEncontrado != null) {
                            System.out.println("Livro encontrado: " + livroEncontrado.getTitulo() + " de " + livroEncontrado.getAutor());
                            break;
                        } else {
                            System.out.println("Livro não encontrado.");
                        }
                    }
                }

                case 4 -> {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Livros em ordem:");
                    arvore.emOrdem();
                }

                case 5 -> {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Livros em pré-ordem:");
                    arvore.preOrdem();
                }

                case 6 -> {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Livros em pós-ordem:");
                    arvore.posOrdem();
                }

                case 7 -> {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Livros em largura:");
                    arvore.emLargura();
                }

                case 8 -> {
                    System.out.println("------------------------------------------------------");
                    System.out.print("Informe o ID do livro a ser removido: ");
                    int idRemover = scanner.nextInt();
                    scanner.nextLine();
                    arvore.remover(idRemover);
                    System.out.println("Livro removido, se existia.");
                }

                case 9 -> {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.out.println("Obrigado por usar o Catálogo de Livros!");
                    return;
                }

                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}