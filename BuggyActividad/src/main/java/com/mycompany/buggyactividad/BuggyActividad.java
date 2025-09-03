/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.buggyactividad;

import java.util.*;



public class BuggyActividad {

    public static void main(String[] args) {

        // LISTA de nombres

        List<String> nombres = new ArrayList<>();

        nombres.add("Ana");

        nombres.add("Luis");

        nombres.add("Ana");



        // Error: índice fuera de rango

        System.out.println("Elemento en posición 1: " + nombres.get(1));



        // Error: comparación de cadenas con == en lugar de equals

        String buscado = ("Ana");

        if (buscado.equals ("Ana")) {

            System.out.println("Encontrado");

        }



        // MAPA de teléfonos

        Map<String, List<String>> telefonos = new HashMap<>();

        telefonos.put("Ana", new ArrayList<> (Arrays.asList("0991111111")));

        telefonos.put("Luis", new ArrayList<> (Arrays.asList("0992222222")));

        telefonos.put("Ana", new ArrayList<> (Arrays.asList("0993333333"))); 



        // Error: obtener clave inexistente sin validación

        System.out.println("Bea: " + telefonos.get("Bea"));
        
        List<String> numero = telefonos.get("Bea");
        
        if (numero != null) {
            
            System.out.println("Bea: " + numero);
            
        } else {
            
            System.out.println("Bea no encontrada");
            
        }



        // SET de inscritos (debería no permitir duplicados lógicos)

        Set<Alumno> inscritos = new HashSet<>();

        inscritos.add(new Alumno(1, "Ana"));

        inscritos.add(new Alumno(2, "Luis"));

        inscritos.add(new Alumno(1, "Ana")); // duplicado lógico



        System.out.println("Tamaño del Set: " + inscritos.size());

        System.out.println(inscritos);

    }

}



class Alumno {

    int id;

    String nombre;

    Alumno(int id, String nombre) { this.id = id; this.nombre = nombre; }
    
    @Override

    public String toString() {

        return "Alumno{id=" + id + ", nombre='" + nombre + "'}";

    }
    
    @Override
    
    public boolean equals (Object o) {
        
        if (this == o) return true;
        
        if (!(o instanceof Alumno)) return false;
        
        Alumno alumno = (Alumno) o;
        
        return id == alumno.id && Objects.equals(nombre, alumno.nombre);
        
    }
    
    @Override
    
    public int hashCode() {
        
        return Objects.hash(id, nombre);
        
    }

}