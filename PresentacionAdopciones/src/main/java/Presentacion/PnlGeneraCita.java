/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import DTO.InfoPersonalDTO;
import DTO.InfoViviendaDTO;
import DTO.MascotaDTO;
import DTO.Razones_AntecedentesDTO;
import DTO.SolicitudAdopcion;
import Interfaz.IControlPresentacion;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import util.BotonRedondeado;
import util.Colores;

/**
 *
 * @author mmax2
 */
public class PnlGeneraCita extends JPanel {

    private IControlPresentacion control;
    private MascotaDTO mascotaSeleccionada;
    private Runnable onVolverInicio; // Callback para volver al inicio

    // Paneles principales
    private CardLayout cardLayout;
    private JPanel PnlFormularios; // Panel que contiene las "cartas"
    private JPanel PnlNavegacion;

    // Botones de navegacion
    private BotonRedondeado BtnVolver;
    private BotonRedondeado BtnSiguiente;

    // DTOs para almacenar informacion
    private InfoPersonalDTO infoPersona;
    private InfoViviendaDTO infoVivienda;
    private Razones_AntecedentesDTO razones;
    private Date fechaCita;

    // Componentes del Paso 1: Info Personal
    private JCalendar CalFechaCita; // <--- MODIFICADO: JSpinner por JCalendar
    private JTextField TxtNombre;
    private JSpinner SpnEdad;
    private JTextField TxtDireccion;
    private JButton BtnSubirId;
    private JComboBox<String> CmbSexo;
    private JComboBox<String> CmbEstatusEmpleo;
    private JLabel LblSalario;
    private JTextField TxtSalario;

    // Componentes del Paso 2: Info Vivienda
    private JTextArea TxtDescripcionVivienda;
    private JComboBox<String> CmbTipoVivienda;
    private JTextField TxtEstadoVivienda;
    private JButton BtnSubirFotosVivienda;
    
    // Componentes del Paso 3: Razones
    private JTextField TxtRazones;
    private JTextField TxtAntecedentes;
    private JCheckBox ChkSeguimiento;
    private JTextArea TxtCartaCompromiso;

    // Componentes del Paso 4: Resumen
    private JLabel LblResumenMascota;
    private JLabel LblResumenCita;
    private JLabel LblResumenContacto;

    // IDs de los paneles
    private static final String PASO_1 = "PASO_1_INFO_PERSONAL";
    private static final String PASO_2 = "PASO_2_INFO_VIVIENDA";
    private static final String PASO_3 = "PASO_3_RAZONES";
    private static final String PASO_4 = "PASO_4_RESUMEN";
    private int pasoActual = 1;


    public PnlGeneraCita(IControlPresentacion control, MascotaDTO mascota, Runnable onVolverInicio) {
        this.control = control;
        this.mascotaSeleccionada = mascota;
        this.onVolverInicio = onVolverInicio;
        
        // Inicializar DTOs
        this.infoPersona = new InfoPersonalDTO();
        this.infoVivienda = new InfoViviendaDTO();
        this.razones = new Razones_AntecedentesDTO();

        initComponentes();
    }

    private void initComponentes() {
        setLayout(new BorderLayout(0, 15));
        setBackground(Colores.FONDO_BLANCO);
        // Margen exterior para que no pegue con el menu lateral
        setBorder(new EmptyBorder(20, 40, 20, 40)); 

        // --- Titulo ---
        JLabel LblTitulo = new JLabel("Solicitud de Adopcion para: " + mascotaSeleccionada.getNombre());
        LblTitulo.setFont(new Font("Inter", Font.BOLD, 28));
        add(LblTitulo, BorderLayout.NORTH);

        // --- Panel Central (CardLayout) ---
        cardLayout = new CardLayout();
        PnlFormularios = new JPanel(cardLayout);
        PnlFormularios.setOpaque(false);

        PnlFormularios.add(crearPaso1(), PASO_1);
        PnlFormularios.add(crearPaso2(), PASO_2);
        PnlFormularios.add(crearPaso3(), PASO_3);
        PnlFormularios.add(crearPaso4(), PASO_4);
        
        add(PnlFormularios, BorderLayout.CENTER);

        // --- Panel de Navegacion (Sur) ---
        PnlNavegacion = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        PnlNavegacion.setOpaque(false);

        BtnVolver = new BotonRedondeado("Volver", Color.GRAY);
        BtnVolver.setVisible(false); // Oculto en el primer paso
        BtnVolver.addActionListener(e -> navegarAnterior());

        BtnSiguiente = new BotonRedondeado("Siguiente", Colores.BOTON_ROSA);
        BtnSiguiente.addActionListener(e -> navegarSiguiente());

        PnlNavegacion.add(BtnVolver);
        PnlNavegacion.add(BtnSiguiente);
        add(PnlNavegacion, BorderLayout.SOUTH);
    }
    
    // --- Metodos de creacion de paneles ---
    
    private JPanel crearPaso1() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 0: Titulo Paso
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(new JLabel("Paso 1: Informacion Personal"), gbc);
        
        // Fila 1: Fecha Cita
        gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(new JLabel("Fecha deseada para la cita:"), gbc);
        gbc.gridx = 1;
        // <--- INICIO MODIFICACION ---
        // Se reemplaza JSpinner por JCalendar
        CalFechaCita = new JCalendar(); 
        panel.add(CalFechaCita, gbc);
        // <--- FIN MODIFICACION ---

        // Fila 2: Nombre
        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(new JLabel("Nombre Completo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        TxtNombre = new JTextField();
        panel.add(TxtNombre, gbc);
        
        // Fila 3: Edad y Sexo
        gbc.gridy = 3; gbc.gridx = 0; gbc.weightx = 0.0;
        panel.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        SpnEdad = new JSpinner(new SpinnerNumberModel(18, 18, 100, 1));
        panel.add(SpnEdad, gbc);

        gbc.gridy = 4; gbc.gridx = 0;
        panel.add(new JLabel("Sexo:"), gbc);
        gbc.gridx = 1;
        CmbSexo = new JComboBox<>(new String[]{"Hombre", "Mujer"});
        panel.add(CmbSexo, gbc);
        
        // Fila 5: Direccion
        gbc.gridy = 5; gbc.gridx = 0;
        panel.add(new JLabel("Direccion:"), gbc);
        gbc.gridx = 1;
        TxtDireccion = new JTextField();
        panel.add(TxtDireccion, gbc);
        
        // Fila 6: ID
        gbc.gridy = 6; gbc.gridx = 0;
        panel.add(new JLabel("Identificacion (Foto):"), gbc);
        gbc.gridx = 1;
        BtnSubirId = new JButton("Seleccionar archivo...");
        panel.add(BtnSubirId, gbc);
        // TODO: Agregar FileChooser al BtnSubirId

        // Fila 7: Empleo
        gbc.gridy = 7; gbc.gridx = 0;
        panel.add(new JLabel("Estatus laboral:"), gbc);
        gbc.gridx = 1;
        CmbEstatusEmpleo = new JComboBox<>(new String[]{"Sin Empleo", "Con Empleo"});
        panel.add(CmbEstatusEmpleo, gbc);
        
        // Fila 8: Salario (Condicional)
        gbc.gridy = 8; gbc.gridx = 0;
        LblSalario = new JLabel("Salario Mensual (Aprox):");
        LblSalario.setVisible(false);
        panel.add(LblSalario, gbc);
        
        gbc.gridx = 1;
        TxtSalario = new JTextField();
        TxtSalario.setVisible(false);
        panel.add(TxtSalario, gbc);
        
        CmbEstatusEmpleo.addItemListener(e -> {
            boolean empleado = e.getStateChange() == ItemEvent.SELECTED && 
                                e.getItem().equals("Con Empleo");
            LblSalario.setVisible(empleado);
            TxtSalario.setVisible(empleado);
        });

        // Espaciador
        gbc.gridy = 9; gbc.weighty = 1.0;
        panel.add(new JPanel(), gbc);

        return panel;
    }

    private JPanel crearPaso2() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 0: Titulo
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(new JLabel("Paso 2: Informacion de la Vivienda"), gbc);
        
        // Fila 1: Descripcion
        gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(new JLabel("Descripcion de la vivienda:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.5;
        TxtDescripcionVivienda = new JTextArea(5, 30);
        JScrollPane scrollDesc = new JScrollPane(TxtDescripcionVivienda);
        panel.add(scrollDesc, gbc);

        // Fila 2: Tipo Vivienda
        gbc.gridy = 2; gbc.gridx = 0; gbc.weightx = 0.0; gbc.weighty = 0.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel("La casa es:"), gbc);
        gbc.gridx = 1;
        CmbTipoVivienda = new JComboBox<>(new String[]{"Propia", "Renta", "Vive con familiar"});
        panel.add(CmbTipoVivienda, gbc);
        
        // Fila 3: Estado Vivienda
        gbc.gridy = 3; gbc.gridx = 0;
        panel.add(new JLabel("Estado de la vivienda:"), gbc);
        gbc.gridx = 1;
        TxtEstadoVivienda = new JTextField();
        panel.add(TxtEstadoVivienda, gbc);
        
        // Fila 4: Fotos
        gbc.gridy = 4; gbc.gridx = 0;
        panel.add(new JLabel("Fotos de la vivienda:"), gbc);
        gbc.gridx = 1;
        BtnSubirFotosVivienda = new JButton("Seleccionar archivos...");
        panel.add(BtnSubirFotosVivienda, gbc);

        // Espaciador
        gbc.gridy = 5; gbc.weighty = 1.0;
        panel.add(new JPanel(), gbc);
        
        return panel;
    }

    private JPanel crearPaso3() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 0: Titulo
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(new JLabel("Paso 3: Motivacion y Compromiso"), gbc);
        
        // Fila 1: Razones
        gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(new JLabel("Razones para adoptar:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        TxtRazones = new JTextField();
        panel.add(TxtRazones, gbc);
        
        // Fila 2: Antecedentes
        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(new JLabel("Antecedentes con mascotas:"), gbc);
        gbc.gridx = 1;
        TxtAntecedentes = new JTextField();
        panel.add(TxtAntecedentes, gbc);

        // Fila 3: Seguimiento
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        ChkSeguimiento = new JCheckBox("Estoy dispuesto a recibir seguimiento del refugio");
        ChkSeguimiento.setOpaque(false);
        panel.add(ChkSeguimiento, gbc);
        
        // Fila 4: Carta Compromiso
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 1;
        panel.add(new JLabel("Carta Compromiso:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0;
        TxtCartaCompromiso = new JTextArea(10, 30);
        JScrollPane scrollCarta = new JScrollPane(TxtCartaCompromiso);
        panel.add(scrollCarta, gbc);
        
        return panel;
    }
    
    private JPanel crearPaso4() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Paso 4: Resumen de la Solicitud"));
        panel.add(Box.createVerticalStrut(20));
        
        LblResumenMascota = new JLabel("Mascota: ");
        LblResumenMascota.setFont(new Font("Inter", Font.PLAIN, 16));
        panel.add(LblResumenMascota);
        
        LblResumenCita = new JLabel("Fecha de Cita: ");
        LblResumenCita.setFont(new Font("Inter", Font.PLAIN, 16));
        panel.add(LblResumenCita);
        
        LblResumenContacto = new JLabel("Contacto del Refugio: ");
        LblResumenContacto.setFont(new Font("Inter", Font.BOLD, 16));
        panel.add(LblResumenContacto);
        
        panel.add(Box.createVerticalGlue()); // Empuja todo hacia arriba
        
        return panel;
    }

    // --- Metodos de navegacion ---

    private void navegarSiguiente() {
        // 1. Validar y guardar datos del paso actual
        if (!validarYGuardarPasoActual()) {
            return; // No avanzar si la validacion falla
        }

        // 2. Avanzar al siguiente paso
        pasoActual++;
        
        if (pasoActual == 2) {
            cardLayout.show(PnlFormularios, PASO_2);
            BtnVolver.setVisible(true);
        } else if (pasoActual == 3) {
            cardLayout.show(PnlFormularios, PASO_3);
        } else if (pasoActual == 4) {
            // Cargar datos en el resumen
            cargarResumen();
            cardLayout.show(PnlFormularios, PASO_4);
            BtnSiguiente.setText("Finalizar Solicitud");
        } else if (pasoActual == 5) {
            // Enviar solicitud
            enviarSolicitud();
        } else if (pasoActual == 6) { 
            // Accion del boton "Volver al Inicio"
            onVolverInicio.run();
        }
    }

    private void navegarAnterior() {
        pasoActual--;

        if (pasoActual == 1) {
            cardLayout.show(PnlFormularios, PASO_1);
            BtnVolver.setVisible(false);
        } else if (pasoActual == 2) {
            cardLayout.show(PnlFormularios, PASO_2);
        } else if (pasoActual == 3) {
            cardLayout.show(PnlFormularios, PASO_3);
            BtnSiguiente.setText("Siguiente");
        }
        // No se puede ir "atras" desde el resumen (paso 4)
    }
    
    private boolean validarYGuardarPasoActual() {
        try {
            if (pasoActual == 1) {
                // Validaciones (simples)
                if (TxtNombre.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio.", "Error de Validacion", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                // Guardar
                // <--- INICIO MODIFICACION ---
                fechaCita = CalFechaCita.getCalendar().getTime(); // Se obtiene la fecha del JCalendar
                // <--- FIN MODIFICACION ---
                infoPersona.setNombre(TxtNombre.getText());
                infoPersona.setEdad((Integer) SpnEdad.getValue());
                infoPersona.setSexo((String) CmbSexo.getSelectedItem());
                infoPersona.setDireccion(TxtDireccion.getText());
                infoPersona.setEstatusEmpleo((String) CmbEstatusEmpleo.getSelectedItem());
                if (infoPersona.getEstatusEmpleo().equals("Con Empleo")) {
                    infoPersona.setSalarioMensual(Double.parseDouble(TxtSalario.getText()));
                } else {
                    infoPersona.setSalarioMensual(0);
                }
                // TODO: Guardar imagen ID
                
            } else if (pasoActual == 2) {
                // Guardar
                infoVivienda.setDescripcion(TxtDescripcionVivienda.getText());
                infoVivienda.setTipoVivienda((String) CmbTipoVivienda.getSelectedItem());
                infoVivienda.setEstadoVivienda(TxtEstadoVivienda.getText());
                // TODO: Guardar imagenes vivienda
                
            } else if (pasoActual == 3) {
                // Validacion carta compromiso
                if (TxtCartaCompromiso.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "La Carta Compromiso no puede estar vacia.", 
                        "Error de Validacion", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                // Guardar
                razones.setRazonesParaAdoptar(TxtRazones.getText());
                razones.setAntecedentesConMascotas(TxtAntecedentes.getText());
                razones.setDispuestoARecibirSeguimiento(ChkSeguimiento.isSelected());
                razones.setCartaCompromiso(TxtCartaCompromiso.getText());
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un numero valido para el salario.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void cargarResumen() {
        LblResumenMascota.setText("Mascota: " + mascotaSeleccionada.getNombre());
        LblResumenCita.setText("Fecha de Cita: " + fechaCita.toString());
        LblResumenContacto.setText("Contacto del Refugio: " + control.getNumeroContacto());
    }

    private void enviarSolicitud() {
        // Crear el DTO principal
        SolicitudAdopcion solicitud = new SolicitudAdopcion(
            infoPersona, infoVivienda, razones, fechaCita, 
            mascotaSeleccionada.getId(), mascotaSeleccionada.getNombre()
        );
        
        // Enviar al control
        boolean exito = control.procesarSolicitud(solicitud);
        
        if (exito) {
            JOptionPane.showMessageDialog(this, 
                "¡Solicitud enviada con exito! El refugio se pondra en contacto contigo.",
                "Solicitud Enviada", JOptionPane.INFORMATION_MESSAGE);
            
            // Cambiar botones
            BtnVolver.setVisible(false);
            BtnSiguiente.setText("Volver al Inicio");
            pasoActual = 6; // Estado final
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "No se pudo procesar la solicitud. Intente mas tarde.",
                "Error", JOptionPane.ERROR_MESSAGE);
            pasoActual = 4; // Regresar al resumen
        }
    }
}
