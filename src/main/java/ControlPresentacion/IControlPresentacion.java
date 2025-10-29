/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlPresentacion;

import DTO.MascotaDTO;
import DTO.SolicitudAdopcion;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IControlPresentacion {
    List<MascotaDTO> getMascotasDisponibles();
    String getNumeroContacto();
    public boolean procesarSolicitud(SolicitudAdopcion solicitud);
}
