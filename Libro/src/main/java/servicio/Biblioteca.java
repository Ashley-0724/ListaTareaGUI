/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import modelo.Libro;
import modelo.Usuario;

/**
 *
 * @author USERJUL20
 */
public class Biblioteca {
     private final Map<String, Libro> catalogoPorIsbn;
    private final Map<String, Usuario> usuariosPorId;
    private final Set<String> isbnsPrestados;

    public Biblioteca() {
        catalogoPorIsbn = new HashMap<>();
        usuariosPorId = new HashMap<>();
        isbnsPrestados = new HashSet<>();
    }

    // ---- Gestión Libros ----
    public void añadirLibro(Libro libro) {
        catalogoPorIsbn.put(libro.getIsbn(), libro);
    }

    public void quitarLibro(String isbn) {
        if (isbnsPrestados.contains(isbn)) {
            throw new IllegalStateException("El libro está prestado, no se puede eliminar.");
        }
        catalogoPorIsbn.remove(isbn);
    }

    // ---- Gestión Usuarios ----
    public void registrarUsuario(Usuario u) {
        usuariosPorId.put(u.getId(), u);
    }

    public void darBajaUsuario(String id) {
        Usuario u = usuariosPorId.get(id);
        if (u != null && !u.getIsbnsPrestados().isEmpty()) {
            throw new IllegalStateException("El usuario aún tiene libros prestados.");
        }
        usuariosPorId.remove(id);
    }

    // ---- Préstamos ----
    public void prestarLibro(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        Libro l = catalogoPorIsbn.get(isbn);

        if (u == null) throw new IllegalArgumentException("Usuario no encontrado.");
        if (l == null) throw new IllegalArgumentException("Libro no encontrado.");
        if (isbnsPrestados.contains(isbn)) throw new IllegalStateException("Libro ya prestado.");

        u.prestar(isbn);
        isbnsPrestados.add(isbn);
    }

    public void devolverLibro(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        if (u == null) throw new IllegalArgumentException("Usuario no encontrado.");
        if (!u.getIsbnsPrestados().contains(isbn)) throw new IllegalStateException("El usuario no tiene este libro.");

        u.devolver(isbn);
        isbnsPrestados.remove(isbn);
    }

    // ---- Búsquedas ----
    public List<Libro> buscarPorTitulo(String texto) {
        return catalogoPorIsbn.values().stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(texto.toLowerCase()))
                .toList();
    }

    public List<Libro> buscarPorAutor(String texto) {
        return catalogoPorIsbn.values().stream()
                .filter(l -> l.getAutor().toLowerCase().contains(texto.toLowerCase()))
                .toList();
    }

    public List<Libro> buscarPorCategoria(String texto) {
        return catalogoPorIsbn.values().stream()
                .filter(l -> l.getCategoria().toLowerCase().contains(texto.toLowerCase()))
                .toList();
    }

    // ---- Listado ----
    public List<Libro> listarPrestados(String idUsuario) {
        Usuario u = usuariosPorId.get(idUsuario);
        if (u == null) throw new IllegalArgumentException("Usuario no encontrado.");
        List<Libro> result = new ArrayList<>();
        for (String isbn : u.getIsbnsPrestados()) {
            result.add(catalogoPorIsbn.get(isbn));
        }
        return result;
    }
    
}
