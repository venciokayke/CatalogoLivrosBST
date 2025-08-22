package org.unifan.catalogolivros.catalogo;

public class No {

    Livro livro;
    No esq, dir;

    public No(No dir, No esq, Livro livro) {
        this.dir = dir;
        this.esq = esq;
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public No getEsq() {
        return esq;
    }

    public void setEsq(No esq) {
        this.esq = esq;
    }

    public No getDir() {
        return dir;
    }

    public void setDir(No dir) {
        this.dir = dir;
    }
}