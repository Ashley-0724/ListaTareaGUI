/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.*;
/**
 *
 * @author USERJUL20
 */

public class Libro {
    
     private final String isbn;
    private final String titulo;
    private final String autor;
    private final String categoria;

    public Libro(String isbn, String titulo, String autor, String categoria) {
        if (isbn == null || isbn.isBlank() ||
            titulo == null || titulo.isBlank() ||
            autor == null || autor.isBlank() ||
            categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("Ningún atributo puede ser nulo o vacío.");
        }
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s)", isbn, titulo, autor, categoria);
    }

}
