/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.awt.Image;
import java.util.List;

/**
 *
 * @author mmax2
 */
public class InfoViviendaDTO {
    private String descripcion;
    private String tipoVivienda; // "Propia", "Renta", "Vive con familiar"
    private String estadoVivienda;
    private List<Image> imagenesVivienda;

    // Constructor, Getters y Setters
    
    public InfoViviendaDTO() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getEstadoVivienda() {
        return estadoVivienda;
    }

    public void setEstadoVivienda(String estadoVivienda) {
        this.estadoVivienda = estadoVivienda;
    }

    public List<Image> getImagenesVivienda() {
        return imagenesVivienda;
    }

    public void setImagenesVivienda(List<Image> imagenesVivienda) {
        this.imagenesVivienda = imagenesVivienda;
    }
}
