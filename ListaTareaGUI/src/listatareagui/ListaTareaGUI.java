/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package listatareagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author USERJUL20
 */
public class ListaTareaGUI extends JFrame {
    
    // Componentes 
    private DefaultListModel<Tarea> modeloLista; // Modelo de datos para la lista
    private JList<Tarea> listaTareas;            // Lista visual de tareas
    private JTextField campoTarea;               // Campo para escribir nuevas tareas
    private JButton botonAgregar, botonCompletar, botonEliminar; // Botones de acción

    // Constructor
    public ListaTareaGUI() {
        // Título de la ventana
        super("Aplicación - Lista de Tareas de Ashley");

        // Configuración básica de ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Centrar en pantalla
        setLayout(new BorderLayout(10, 10));

        // Panel superior con campo de texto + botón agregar
        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        campoTarea = new JTextField();
        botonAgregar = new JButton("Añadir Tarea");

        panelSuperior.add(campoTarea, BorderLayout.CENTER);
        panelSuperior.add(botonAgregar, BorderLayout.EAST);

        // Modelo de lista y JList personalizada
        modeloLista = new DefaultListModel<>();
        listaTareas = new JList<>(modeloLista);

        // Usamos un renderer personalizado para mostrar tachado cuando está completada
        listaTareas.setCellRenderer(new TareaRenderer());

        JScrollPane scroll = new JScrollPane(listaTareas);

        // Panel inferior con botones de acciones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botonCompletar = new JButton("Marcar como Completada");
        botonEliminar = new JButton("Eliminar Tarea");

        panelInferior.add(botonCompletar);
        panelInferior.add(botonEliminar);

        // Agregar paneles al Frame
        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // === EVENTOS ===

        // 1) Añadir tarea con botón
        botonAgregar.addActionListener(e -> agregarTarea());

        // 2) Añadir tarea con tecla ENTER en el campo de texto
        campoTarea.addActionListener(e -> agregarTarea());

        // 3) Marcar como completada
        botonCompletar.addActionListener(e -> marcarCompletada());

        // 4) Para eleminar la tarea que sea seleccionada
        botonEliminar.addActionListener(e -> eliminarTarea());

        // 5) Doble clic marca como completa
        listaTareas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic
                    marcarCompletada();
                }
            }
        });

        setVisible(true);
    }

    // Método para añadir tarea
    private void agregarTarea() {
        String texto = campoTarea.getText().trim();
        if (!texto.isEmpty()) {
            modeloLista.addElement(new Tarea(texto));
            campoTarea.setText(""); // limpiar campo
        } else {
            JOptionPane.showMessageDialog(this, "Escribe una tarea primero.");
        }
    }

    // Método para marcar como completada
    private void marcarCompletada() {
        int index = listaTareas.getSelectedIndex();
        if (index != -1) {
            Tarea tarea = modeloLista.get(index);
            tarea.setCompletada(!tarea.isCompletada()); // Alternar estado
            listaTareas.repaint(); // Redibujar la lista para reflejar el cambio
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para marcar.");
        }
    }

    // Método para eliminar tarea
    private void eliminarTarea() {
        int index = listaTareas.getSelectedIndex();
        if (index != -1) {
            modeloLista.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para eliminar.");
        }
    }

    // Clase interna para representar una Tarea
    static class Tarea {
        private String descripcion;
        private boolean completada;

        public Tarea(String descripcion) {
            this.descripcion = descripcion;
            this.completada = false;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public boolean isCompletada() {
            return completada;
        }

        public void setCompletada(boolean completada) {
            this.completada = completada;
        }

        @Override
        public String toString() {
            return descripcion;
        }
    }

    // Para mostrar tareas completadas tachadas
    static class TareaRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (c instanceof JLabel && value instanceof Tarea) {
                Tarea tarea = (Tarea) value;
                JLabel label = (JLabel) c;

                if (tarea.isCompletada()) {
                    label.setText("<html><strike>" + tarea.getDescripcion() + "</strike></html>");
                    label.setForeground(Color.GRAY);
                } else {
                    label.setText(tarea.getDescripcion());
                    label.setForeground(Color.BLACK);
                }
            }
            return c;
        }
    }

    // Método main para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListaTareaGUI());
    }
}