/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mmax2
 */
public class MascotaDTO {
    
    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String enfermedadActual;
    private String rutaImagen; // Ruta relativa a la imagen

    public MascotaDTO( String nombre, String especie, String raza, int edad, String enfermedadActual, String rutaImagen) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.enfermedadActual = enfermedadActual;
        this.rutaImagen = rutaImagen;
    }
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public String getEnfermedadActual() {
        return enfermedadActual;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }
}
