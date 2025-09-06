/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.Libro;
import modelo.Usuario;
import servicio.Biblioteca;

/**
 *
 * @author USERJUL20
 */
public class Main {
      public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Registrar usuarios
        biblioteca.registrarUsuario(new Usuario("U1", "Sofia"));
        biblioteca.registrarUsuario(new Usuario("U2", "Luis"));

        // Añadir libros
        biblioteca.añadirLibro(new Libro("ISBN-001", "Clean Code", "Carlos S. Martin", "Software"));
        biblioteca.añadirLibro(new Libro("ISBN-002", "Effective Java", "Joshua Bloch", "Java"));

        // Prestar libros
        biblioteca.prestarLibro("U1", "ISBN-001");
        biblioteca.prestarLibro("U2", "ISBN-002");

        // Listar libros prestados de U1
        System.out.println("Libros prestados por Sofia:");
        biblioteca.listarPrestados("U1").forEach(System.out::println);

        // Devolver un libro
        biblioteca.devolverLibro("U1", "ISBN-001");

        // Buscar por autor
        System.out.println("\nLibros de Bloch:");
        biblioteca.buscarPorAutor("Bloch").forEach(System.out::println);

        // Buscar por categoría
        System.out.println("\nLibros de Software:");
        biblioteca.buscarPorCategoria("Software").forEach(System.out::println);
    }
    
}
