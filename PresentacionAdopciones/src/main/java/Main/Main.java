/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Control.ControlPresentacion;
import Interfaz.IControlPresentacion;
import Presentacion.FrmPrincipal;
import javax.swing.SwingUtilities;

/**
 *
 * @author mmax2
 */
public class Main {
    public static void main(String[] args) {
        // Es buena practica iniciar las UIs de Swing en el
        // Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            
            // 1. Crear el control
            // (La presentacion depende del control)
            IControlPresentacion control = new ControlPresentacion();
            
            // 2. Crear la vista principal
            // (La vista necesita el control para operar)
            FrmPrincipal frm = new FrmPrincipal(control);
            
            // 3. Mostrar la vista
            frm.setVisible(true);
        });
    }
}
