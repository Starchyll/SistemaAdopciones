/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import DTO.MascotaDTO;
import Interfaz.IControlPresentacion;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import util.Colores;
import util.PlaceholderIcon;

/**
 *
 * @author mmax2
 */
public class FrmPrincipal extends JFrame {

    private IControlPresentacion control;
    
    // Paneles principales
    private JPanel PnlMenuLateral;
    private JPanel PnlContenido; // Panel con CardLayout
    private CardLayout cardLayoutContenido;
    
    // Paneles especificos
    private JScrollPane PnlListaMascotas;
    private PnlGeneraCita PnlCita;

    // IDs de las vistas
    private static final String VISTA_LISTA = "VISTA_LISTA";
    private static final String VISTA_CITA = "VISTA_CITA";

    public FrmPrincipal(IControlPresentacion control) {
        this.control = control;
        initComponentes();
        cargarMascotas();
    }

    private void initComponentes() {
        setTitle("Sistema de Adopcion de Mascotas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1024, 768)); // Tamano minimo
        setLocationRelativeTo(null); // Centrar en pantalla
        
        // Layout principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Colores.FONDO_BLANCO);

        // --- 1. Menu Lateral (OESTE) ---
        PnlMenuLateral = crearMenuLateral();
        contentPane.add(PnlMenuLateral, BorderLayout.WEST);

        // --- 2. Contenido Principal (CENTRO) ---
        cardLayoutContenido = new CardLayout();
        PnlContenido = new JPanel(cardLayoutContenido);
        PnlContenido.setOpaque(false);

        // Vista 1: Lista de Mascotas
        PnlListaMascotas = crearPanelListaMascotas();
        PnlContenido.add(PnlListaMascotas, VISTA_LISTA);
        
        // Vista 2: Formulario de Cita (se crea cuando se necesita)
        
        contentPane.add(PnlContenido, BorderLayout.CENTER);
        
        cardLayoutContenido.show(PnlContenido, VISTA_LISTA);
    }

    private JPanel crearMenuLateral() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Colores.PANEL_LATERAL_AZUL);
        panel.setPreferredSize(new Dimension(250, 0)); // Ancho fijo, alto flexible
        panel.setBorder(new EmptyBorder(20, 15, 20, 15)); // Margen interno
        
        // Foto de Perfil
        JLabel LblFotoPerfil = new JLabel(new PlaceholderIcon(100, 100, Color.WHITE));
        LblFotoPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(LblFotoPerfil);

        panel.add(Box.createVerticalStrut(10));

        // Nombre de Usuario
        JLabel LblNombreUsuario = new JLabel("Nombre de Usuario");
        LblNombreUsuario.setFont(new Font("Inter", Font.BOLD, 18));
        LblNombreUsuario.setForeground(Color.WHITE);
        LblNombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(LblNombreUsuario);
        
        // Relleno flexible para empujar todo lo demas hacia abajo
        panel.add(Box.createVerticalGlue()); 
        
        // Aqui irian los botones de navegacion (rosa claro)
        
        return panel;
    }

    private JScrollPane crearPanelListaMascotas() {
        JPanel panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(Colores.FONDO_BLANCO);
        
        JScrollPane scrollPane = new JScrollPane(panelLista);
        scrollPane.setBorder(null); // Sin borde
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Scroll mas suave
        
        return scrollPane;
    }
    
    private void cargarMascotas() {
        List<MascotaDTO> mascotas = control.getMascotasDisponibles();
        
        // Obtenemos el panel interno del JScrollPane
        JPanel panelLista = (JPanel) PnlListaMascotas.getViewport().getView();
        panelLista.removeAll(); // Limpiar lista anterior
        
        for (MascotaDTO mascota : mascotas) {
            // Pasamos un "Consumer" (lambda) como listener
            // Esto le dice al item que hacer cuando se presione "Adoptar"
            PnlMascotaItem item = new PnlMascotaItem(mascota, (mascotaSeleccionada) -> {
                iniciarProcesoAdopcion(mascotaSeleccionada);
            });
            panelLista.add(item);
        }
        
        panelLista.revalidate();
        panelLista.repaint();
    }
    
    /**
     * Llamado por PnlMascotaItem cuando se hace clic en "Adoptar".
     */
    private void iniciarProcesoAdopcion(MascotaDTO mascota) {
        // Creamos el panel de cita (wizard)
        // Le pasamos un "Runnable" (lambda) para cuando termine
        PnlCita = new PnlGeneraCita(control, mascota, () -> {
            volverAlInicio();
        });
        
        PnlContenido.add(PnlCita, VISTA_CITA);
        cardLayoutContenido.show(PnlContenido, VISTA_CITA);
    }
    
    /**
     * Llamado por PnlGeneraCita cuando el proceso finaliza.
     */
    private void volverAlInicio() {
        // Mostramos la lista
        cardLayoutContenido.show(PnlContenido, VISTA_LISTA);
        // Removemos el panel de cita para que se cree uno nuevo la proxima vez
        if (PnlCita != null) {
            PnlContenido.remove(PnlCita);
            PnlCita = null;
        }
    }
}
