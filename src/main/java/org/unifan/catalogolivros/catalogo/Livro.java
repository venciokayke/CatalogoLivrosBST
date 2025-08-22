package org.unifan.catalogolivros.catalogo;

public class Livro {

    private int id;
    private String titulo;
    private String autor;

    public Livro(String autor, int id, String titulo) {
        this.autor = autor;
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}