/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 *
 * @author mmax2
 */
public class BotonRedondeado extends JButton {

    private Color colorFondo;

    public BotonRedondeado(String texto, Color colorFondo) {
        super(texto);
        this.colorFondo = colorFondo;
        
        setForeground(Colores.TEXTO_BOTON_BLANCO);
        setFont(getFont().deriveFont(14f));
        
        // Configuracion para que el boton sea transparente y pintemos nosotros
        setContentAreaFilled(false); 
        setFocusPainted(false);      
        setBorderPainted(false);     
        
        // Tamano preferido
        setPreferredSize(new Dimension(120, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Pintar el fondo redondeado
        g2.setColor(colorFondo);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 25, 25)); // 25x25 es el arco

        // Pintar el texto encima
        super.paintComponent(g2);
        g2.dispose();
    }
}
