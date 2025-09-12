/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicaciongui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author USERJUL20
 */
public class AplicacionGUI extends JFrame {
    
    // Componentes
    private JTextField campoTexto;
    private final JButton botonAgregar;
    private final JButton botonLimpiar;
    private final JList<String> listaDatos;
    private DefaultListModel<String> modeloLista;

    public AplicacionGUI() {
        
        // Configuración de la ventana
        setTitle("Gestión de Datos GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con etiqueta, campo y botones
        JPanel panelSuperior = new JPanel(new FlowLayout());
        JLabel etiqueta = new JLabel("Ingrese un dato:");
        campoTexto = new JTextField(15);
        botonAgregar = new JButton("Agregar");
        botonLimpiar = new JButton("Limpiar");

        panelSuperior.add(etiqueta);
        panelSuperior.add(campoTexto);
        panelSuperior.add(botonAgregar);
        panelSuperior.add(botonLimpiar);

        // Modelo y lista
        modeloLista = new DefaultListModel<>();
        listaDatos = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaDatos);

        // Agregar componentes al frame
        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Eventos
        botonAgregar.addActionListener((ActionEvent e) -> {
            String texto = campoTexto.getText().trim();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede agregar un campo vacío.");
            } else {
                modeloLista.addElement(texto);
                campoTexto.setText(""); // limpiar campo
            }
        });

        botonLimpiar.addActionListener((ActionEvent e) -> {
            campoTexto.setText("");
            modeloLista.clear(); // limpia toda la lista
        });
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplicacionGUI ventana = new AplicacionGUI();
            ventana.setVisible(true);
        });
    }
}
