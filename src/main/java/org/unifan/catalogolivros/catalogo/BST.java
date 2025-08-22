package org.unifan.catalogolivros.catalogo;
import java.util.LinkedList;
import java.util.Queue;

// Classe que representa uma Árvore Binária de Busca (BST) para armazenar livros
public class BST {
    // Nó raiz da árvore
    private No raiz;

    // Construtor: inicializa a árvore vazia
    public BST() {
        this.raiz = null;
    }

    /**
     * Insere um novo livro na árvore.
     * Chama o método recursivo auxiliar passando a raiz atual.
     */
    public void inserir(Livro livro) {
        raiz = inserirRec(raiz, livro);
    }

    /**
     * Método recursivo para inserir um livro na posição correta da árvore.
     * - Se o nó atual for nulo, cria um novo nó com o livro.
     * - Se o ID do livro for menor que o do nó atual, insere à esquerda.
     * - Se maior, insere à direita.
     * - Se igual, não insere e avisa.
     * Sempre retorna o nó atual para manter a ligação da árvore.
     */
    private No inserirRec(No raiz, Livro livro) {
        // Caso base: se o nó atual é nulo, chegou no lugar certo para inserir o novo livro
        if (raiz == null) {
            return new No(null, null, livro);
        }
        // Só permite inserir IDs positivos
        if (livro.getId() > 0) {
            // Se o ID do novo livro for menor que o ID do nó atual,
            // chama recursivamente para a subárvore da esquerda
            if (livro.getId() < raiz.getLivro().getId()) {
                raiz.setEsq(inserirRec(raiz.getEsq(), livro));
            // Se o ID do novo livro for maior que o ID do nó atual,
            // chama recursivamente para a subárvore da direita
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

    /**
     * Busca um livro pelo ID na árvore.
     * Retorna o livro encontrado ou null se não existir.
     */
    public Livro buscar(int id) {
        No noEncontrado = buscarRec(raiz, id);
        return (noEncontrado != null) ? noEncontrado.getLivro() : null;
    }

    /**
     * Busca recursiva por ID.
     * - Se o nó atual for nulo, retorna null (não achou).
     * - Se o ID do nó atual for igual ao procurado, retorna o nó (achou).
     * - Se o ID procurado for menor, busca à esquerda.
     * - Se for maior, busca à direita.
     */
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

    /**
     * Percorre a árvore em pré-ordem (raiz, esquerda, direita) e imprime os livros.
     * Útil para visualizar a estrutura da árvore.
     */
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

    /**
     * Percorre a árvore em pós-ordem (esquerda, direita, raiz) e imprime os livros.
     * Útil para liberar memória ou processar filhos antes do pai.
     */
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

    /**
     * Percorre a árvore em ordem (esquerda, raiz, direita) e imprime os livros em ordem crescente de ID.
     * Útil para listar os livros ordenados.
     */
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

    /**
     * Percorre a árvore em largura (nível por nível) e imprime os livros.
     * Usa uma fila para visitar todos os nós de cada nível antes de passar para o próximo.
     */
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

    /**
     * Remove um livro da árvore pelo ID.
     * Chama o método recursivo auxiliar passando a raiz atual.
     */
    public void remover(int id) {
        raiz = removerRec(raiz, id);
    }

    /**
     * Método recursivo para remover um nó da árvore.
     * - Se não encontrar, retorna null.
     * - Se encontrar, trata os casos:
     *   - Sem filho: retorna null.
     *   - Um filho: retorna o filho.
     *   - Dois filhos: substitui pelo menor da subárvore direita.
     */
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

    /**
     * Retorna o nó com menor valor (mais à esquerda) da subárvore.
     * Usado para encontrar o sucessor na remoção.
     */
    private No menorNo(No no) {
        while (no.getEsq() != null) no = no.getEsq();
        return no;
    }
}