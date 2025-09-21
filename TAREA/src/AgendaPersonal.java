/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaPersonal extends JFrame {

    // Componentes
    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JSpinner spinnerFecha;
    private JSpinner spinnerHora;
    private JTextField campoDescripcion;
    private JButton botonAgregar, botonEliminar, botonSalir;

    public AgendaPersonal() {
        
        // Configuración de la ventana
        setTitle("Agenda Personal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar
        setLayout(new BorderLayout(10, 10));

        // === Panel superior: Entrada de datos ===
        JPanel panelEntrada = new JPanel(new GridLayout(3, 2, 5, 5));

        // Fecha
        panelEntrada.add(new JLabel("Fecha (dd/MM/yyyy):"));
        spinnerFecha = new JSpinner(new SpinnerDateModel());
        spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
        panelEntrada.add(spinnerFecha);

        // Hora
        panelEntrada.add(new JLabel("Hora (HH:mm):"));
        spinnerHora = new JSpinner(new SpinnerDateModel());
        spinnerHora.setEditor(new JSpinner.DateEditor(spinnerHora, "HH:mm"));
        panelEntrada.add(spinnerHora);

        // Descripción
        panelEntrada.add(new JLabel("Descripción:"));
        campoDescripcion = new JTextField();
        panelEntrada.add(campoDescripcion);

        add(panelEntrada, BorderLayout.NORTH);

        // Panel central: Tabla con scroll 
        String[] columnas = {"Fecha", "Hora", "Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEventos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaEventos);
        add(scrollTabla, BorderLayout.CENTER);

        //  Panel inferior: Botones
        JPanel panelBotones = new JPanel();
        botonAgregar = new JButton("Agregar");
        botonEliminar = new JButton("Eliminar seleccionado");
        botonSalir = new JButton("Salir");

        panelBotones.add(botonAgregar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonSalir);

        add(panelBotones, BorderLayout.SOUTH);

        // Eventos de los botones 
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEvento();
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEvento();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // cerrar ventana
            }
        });
    }

    // Método para agregar evento
    private void agregarEvento() {
        String descripcion = campoDescripcion.getText().trim();

        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "La descripción no puede estar vacía",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener fecha y hora
        Date fecha = (Date) spinnerFecha.getValue();
        Date hora = (Date) spinnerHora.getValue();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

        String fechaStr = formatoFecha.format(fecha);
        String horaStr = formatoHora.format(hora);

        // Insertar en la tabla
        modeloTabla.addRow(new Object[]{fechaStr, horaStr, descripcion});

        // Limpiar campo descripción y volver a enfocar
        campoDescripcion.setText("");
        campoDescripcion.requestFocus();
    }

    // Para eliminar evento
    private void eliminarEvento() {
        int filaSeleccionada = tablaEventos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona un evento primero",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el evento seleccionado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            modeloTabla.removeRow(filaSeleccionada);
        }
    }

    // Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AgendaPersonal().setVisible(true);
        });
    }
}
