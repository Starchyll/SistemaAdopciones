/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import DTO.MascotaDTO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import util.BotonRedondeado;
import util.Colores;

/**
 *
 * @author mmax2
 */
public class PnlMascotaItem extends JPanel {

    private MascotaDTO mascota;
    private Consumer<MascotaDTO> listenerAdopcion;
    private boolean expandido = false;

    // Componentes
    private JLabel LblImagen;
    private JPanel PnlDetalles;
    private JLabel LblNombre;
    private JLabel LblEdad;
    
    // Componentes expandidos
    private JLabel LblEspecieRaza;
    private JLabel LblEnfermedad;
    private BotonRedondeado BtnAdoptar;

    public PnlMascotaItem(MascotaDTO mascota, Consumer<MascotaDTO> listenerAdopcion) {
        this.mascota = mascota;
        this.listenerAdopcion = listenerAdopcion;
        initComponentes();
    }

    private void initComponentes() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.BORDE_PANEL), // Linea divisoria
            new EmptyBorder(20, 20, 20, 20) // Margen interno
        ));
        setBackground(Colores.FONDO_BLANCO);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // --- Imagen ---
        LblImagen = new JLabel();
        LblImagen.setOpaque(true);
        LblImagen.setBackground(Colores.BORDE_PANEL); // Fondo mientras carga
        LblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        LblImagen.setVerticalAlignment(SwingConstants.CENTER);
        actualizarTamanoImagen(); // Ajusta el tamano inicial

        // Cargar imagen de forma asincrona (buena practica)
        SwingWorker<ImageIcon, Void> loader = new SwingWorker<>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                ImageIcon icon = new ImageIcon(getClass().getResource(mascota.getRutaImagen()));
                // Re-escalar imagen
                int width = expandido ? 200 : 100;
                Image img = icon.getImage().getScaledInstance(width, -1, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
            @Override
            protected void done() {
                try {
                    LblImagen.setIcon(get());
                } catch (Exception e) {
                    LblImagen.setText("Error");
                    e.printStackTrace();
                }
            }
        };
        loader.execute();
        
        add(LblImagen, BorderLayout.WEST);

        // --- Panel de Detalles (Centro) ---
        PnlDetalles = new JPanel();
        PnlDetalles.setLayout(new BoxLayout(PnlDetalles, BoxLayout.Y_AXIS));
        PnlDetalles.setOpaque(false);

        LblNombre = new JLabel(mascota.getNombre());
        LblNombre.setFont(new Font("Inter", Font.BOLD, 24));
        PnlDetalles.add(LblNombre);

        LblEdad = new JLabel(mascota.getEdad() + " anos");
        LblEdad.setFont(new Font("Inter", Font.PLAIN, 16));
        PnlDetalles.add(LblEdad);

        // --- Detalles Expandidos (ocultos inicialmente) ---
        LblEspecieRaza = new JLabel("Especie: " + mascota.getEspecie() + " | Raza: " + mascota.getRaza());
        LblEspecieRaza.setFont(new Font("Inter", Font.PLAIN, 14));
        LblEspecieRaza.setVisible(false);
        PnlDetalles.add(Box.createVerticalStrut(10));
        PnlDetalles.add(LblEspecieRaza);

        LblEnfermedad = new JLabel("Salud: " + mascota.getEnfermedadActual());
        LblEnfermedad.setFont(new Font("Inter", Font.PLAIN, 14));
        LblEnfermedad.setVisible(false);
        PnlDetalles.add(LblEnfermedad);
        
        add(PnlDetalles, BorderLayout.CENTER);

        // --- Boton Adoptar (Este) ---
        JPanel pnlBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBoton.setOpaque(false);
        
        BtnAdoptar = new BotonRedondeado("Adoptar", Colores.BOTON_ROSA);
        BtnAdoptar.setVisible(false);
        BtnAdoptar.addActionListener(e -> {
            listenerAdopcion.accept(this.mascota);
        });
        pnlBoton.add(BtnAdoptar);
        add(pnlBoton, BorderLayout.EAST);
        
        // --- Listener para expandir ---
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // No expandir si el clic fue en el boton
                Component source = findComponentAt(e.getPoint());
                if (source == BtnAdoptar || SwingUtilities.isDescendingFrom(source, BtnAdoptar)) {
                    return;
                }
                toggleExpandir();
            }
        });
    }
    
    private void actualizarTamanoImagen() {
        int tamano = expandido ? 200 : 100;
        LblImagen.setMinimumSize(new Dimension(tamano, tamano));
        LblImagen.setPreferredSize(new Dimension(tamano, tamano));
        LblImagen.setMaximumSize(new Dimension(tamano, tamano));
    }

    private void toggleExpandir() {
        expandido = !expandido;
        
        // Actualizar visibilidad
        LblEspecieRaza.setVisible(expandido);
        LblEnfermedad.setVisible(expandido);
        BtnAdoptar.setVisible(expandido);
        
        // Actualizar tamano de imagen
        actualizarTamanoImagen();
        // Recargar la imagen con el nuevo tamano
        (new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                ImageIcon icon = new ImageIcon(getClass().getResource(mascota.getRutaImagen()));
                int width = expandido ? 200 : 100;
                int height = expandido ? 200 : 100;
                Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
            @Override
            protected void done() { try { LblImagen.setIcon(get()); } catch (Exception e) {e.printStackTrace();} }
        }).execute();

        // Forzar al contenedor (JScrollPane) a recalcular el layout
        // Usamos SwingUtilities.invokeLater para asegurar que se haga en el EDT
        // y despues de que los cambios de visibilidad se procesen.
        SwingUtilities.invokeLater(() -> {
            Container parent = getParent();
            if (parent != null) {
                parent.revalidate();
                parent.repaint();
            }
        });
    }
}
