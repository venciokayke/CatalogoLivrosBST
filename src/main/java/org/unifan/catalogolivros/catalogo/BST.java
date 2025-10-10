package org.unifan.catalogolivros.catalogo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BST {
    // Nó raiz da árvore
    private No raiz;


    public BST() {
        this.raiz = null;
    }


    public void inserir(Livro livro) {
        raiz = inserirRec(raiz, livro);
    }


    private No inserirRec(No raiz, Livro livro) {
        // Caso base: se o nó atual é nulo, chegou no lugar certo para inserir o novo livro
        if (raiz == null) {
            return new No(null, null, livro);
        }
        // Só permite inserir IDs positivos
        if (livro.getId() > 0) {
            // Se o ID do novo livro for menor que o ID do nó atual,
            if (livro.getId() < raiz.getLivro().getId()) {
                raiz.setEsq(inserirRec(raiz.getEsq(), livro));

            // Se o ID do novo livro for maior que o ID do nó atual,
            } else if (livro.getId() > raiz.getLivro().getId()) {
                raiz.setDir(inserirRec(raiz.getDir(), livro));
                
            // Se o ID for igual ao do nó atual, não insere e avisa que já existe
            } else {
                System.out.println("ID já existe!");
            }
        } else {
            // Se o ID não for positivo, exibe mensagem de erro
            System.out.println("ID inválido! Deve ser um número positivo.");
        }
        // Retorna o nó atual para manter a ligação da árvore
        return raiz;
    }


    public Livro buscar(int id) {
        No noEncontrado = buscarRec(raiz, id);
        return (noEncontrado != null) ? noEncontrado.getLivro() : null;
    }


    private No buscarRec(No raiz, int id) {
        // Se o nó atual for nulo, ou se o ID do livro do nó atual for igual ao procurado, retorna o nó (achou ou chegou ao fim)
        if (raiz == null || raiz.getLivro().getId() == id) {
            return raiz;
        }
        // Se o ID procurado for menor que o do nó atual, busca recursivamente na subárvore da esquerda
        if (id < raiz.getLivro().getId()) {
            return buscarRec(raiz.getEsq(), id);
        }
        // Se não for igual nem menor, só pode ser maior: busca recursivamente na subárvore da direita
        return buscarRec(raiz.getDir(), id);
    }


    public void preOrdem() {
        preOrdemRec(raiz);
    }

    // Função auxiliar recursiva para pré-ordem
    private void preOrdemRec(No no) {
        if (no != null) {
            // Imprime o livro do nó atual
            System.out.println(no.getLivro().getId() + " - " + no.getLivro().getTitulo() + " (" + no.getLivro().getAutor() + ")");
            // Percorre a subárvore esquerda
            preOrdemRec(no.getEsq());
            // Percorre a subárvore direita
            preOrdemRec(no.getDir());
        }
    }


    public void posOrdem() {
        posOrdemRec(raiz);
    }

    // Função auxiliar recursiva para pós-ordem
    private void posOrdemRec(No no) {
        if (no != null) {
            // Percorre a subárvore esquerda
            posOrdemRec(no.getEsq());
            // Percorre a subárvore direita
            posOrdemRec(no.getDir());
            // Imprime o livro do nó atual
            System.out.println(no.getLivro().getId() + " - " + no.getLivro().getTitulo() + " (" + no.getLivro().getAutor() + ")");
        }
    }


    public void emOrdem() {
        emOrdemRec(raiz);
    }

    // Função auxiliar recursiva para em ordem
    private void emOrdemRec(No no) {
        if (no != null) {
            // Percorre a subárvore esquerda
            emOrdemRec(no.getEsq());
            // Imprime o livro do nó atual
            System.out.println(no.getLivro().getId() + " - " + no.getLivro().getTitulo() + " (" + no.getLivro().getAutor() + ")");
            // Percorre a subárvore direita
            emOrdemRec(no.getDir());
        }
    }


    public void emLargura() {
        if (raiz == null) return;
        // Fila para armazenar os nós a serem visitados
        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            // Remove o próximo nó da fila
            No atual = fila.poll();
            // Imprime o livro do nó atual
            System.out.println(atual.getLivro().getId() + " - " + atual.getLivro().getTitulo() + " (" + atual.getLivro().getAutor() + ")");
            // Adiciona os filhos à fila para visitar depois
            if (atual.getEsq() != null) fila.add(atual.getEsq());
            if (atual.getDir() != null) fila.add(atual.getDir());
        }
    }


    public void remover(int id) {
        raiz = removerRec(raiz, id);
    }


    private No removerRec(No no, int id) {
        if (no == null) return null;
        // Se o ID a remover for menor, busca na esquerda
        if (id < no.getLivro().getId()) {
            no.setEsq(removerRec(no.getEsq(), id));
        // Se o ID a remover for maior, busca na direita
        } else if (id > no.getLivro().getId()) {
            no.setDir(removerRec(no.getDir(), id));
        } else {
            // Achou o nó a remover
            // Caso com um ou nenhum filho
            if (no.getEsq() == null) return no.getDir();
            if (no.getDir() == null) return no.getEsq();
            // Caso com dois filhos: pega o menor da subárvore direita
            No sucessor = menorNo(no.getDir());
            no.setLivro(sucessor.getLivro());
            no.setDir(removerRec(no.getDir(), sucessor.getLivro().getId()));
        }
        return no;
    }

    private No menorNo(No no) {
        while (no.getEsq() != null) no = no.getEsq();
        return no;
    }

    public void inserirLivrosAuto(){

        String[] titulos = {
                "Torto arado", "O avesso da pele", "Lord Jim", "A verdade e outras mentiras", "O homem que sabia demais",
                "O sol é para todos", "1984", "O Banquete", "As Viagens de Gulliver", "Os Sofrimentos do Jovem Werther",
                "Frankenstein", "O Corcunda de Notre Dame", "Um Conto de Natal", "Histórias Extraordinárias", "Moby Dick",
                "Madame Bovary", "As Flores do Mal", "Senhora", "O Alienista", "O Cão dos Baskervilles", "Parque Industrial",
                "Morte no Nilo", "A Revolução dos Bichos", "A Peste", "Clara dos Anjos", "Memórias de Adriano", "Incidente em Antares",
                "On the Road", "Crônica da Casa Assassinada", "Quarto de Despejo", "Um Homem Só", "Felicidade Clandestina",
                "Carrie, a Estranha", "Bagagem", "Terra Sonâmbula", "O Sol na Cabeça", "Te Vejo na Final"
        };

        String[] autores = {
                "Itamar Vieira Junior", "Jeferson Tenório", "Joseph Conrad", "Sasha Arang", "Gilbert Keith Chesterton",
                "Harper Lee", "George Orwell", "Platão", "Jonathan Swift", "Johann Wolfgang von Goethe", "Mary Shelley",
                "Victor Hugo", "Charles Dickens", "Edgar Allan Poe", "Herman Melville", "Gustave Flaubert",
                "Charles Baudelaire", "José de Alencar", "Machado de Assis", "Arthur Conan Doyle", "Pagu", "Agatha Christie",
                "George Orwell", "Albert Camus", "Lima Barreto", "Marguerite Yourcenar", "Erico Verissimo", "Jack Kerouac",
                "Lúcio Cardoso", "Carolina Maria de Jesus", "Christopher Isherwood", "Clarice Lispector", "Stephen King",
                "Adélia Prado", "Mia Couto", "Geovane Martins", "Ayslan Monteiro"
        };



        int totalLivros = titulos.length;
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= totalLivros; i++) {
            ids.add(i);
        }
        Collections.shuffle(ids);

        for (int i = 0; i < titulos.length; i++) {


            String autor = autores[i];
            int id = ids.get(i);
            String titulo = titulos[i];

            inserir(new Livro(autor, id, titulo));
        }
        System.out.println("37 livros inseridos automaticamente.");

    }
}