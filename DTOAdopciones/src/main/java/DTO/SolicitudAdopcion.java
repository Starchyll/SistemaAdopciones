/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author mmax2
 */
public class SolicitudAdopcion {
    private InfoPersonalDTO infoPersona;
    private InfoViviendaDTO infoVivienda;
    private Razones_AntecedentesDTO razones;
    private Date fechaCita;
    private Long mascotaId;
    private String mascotaNombre; 

    public SolicitudAdopcion(InfoPersonalDTO infoPersona, InfoViviendaDTO infoVivienda, Razones_AntecedentesDTO razones, Date fechaCita, Long mascotaId, String mascotaNombre) {
        this.infoPersona = infoPersona;
        this.infoVivienda = infoVivienda;
        this.razones = razones;
        this.fechaCita = fechaCita;
        this.mascotaId = mascotaId;
        this.mascotaNombre = mascotaNombre;
    }
    
    public InfoPersonalDTO getInfoPersona() {
        return infoPersona;
    }

    public InfoViviendaDTO getInfoVivienda() {
        return infoVivienda;
    }

    public Razones_AntecedentesDTO getRazones() {
        return razones;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public Long getMascotaId() {
        return mascotaId;
    }
    
    public String getMascotaNombre() {
        return mascotaNombre;
    }
}
