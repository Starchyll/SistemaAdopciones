/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.awt.Image;

/**
 *
 * @author mmax2
 */
public class InfoPersonalDTO {
    private String nombre;
    private int edad;
    private String direccion;
    private Image imagenIdentificacion;
    private String sexo; // "Hombre" o "Mujer"
    private String estatusEmpleo; // "Con Empleo" o "Sin Empleo"
    private double salarioMensual; // 0.0 si no tiene empleo

    
    
    public InfoPersonalDTO() {
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Image getImagenIdentificacion() {
        return imagenIdentificacion;
    }

    public void setImagenIdentificacion(Image imagenIdentificacion) {
        this.imagenIdentificacion = imagenIdentificacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstatusEmpleo() {
        return estatusEmpleo;
    }

    public void setEstatusEmpleo(String estatusEmpleo) {
        this.estatusEmpleo = estatusEmpleo;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }
}
