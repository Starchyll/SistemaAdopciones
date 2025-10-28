/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.MascotaDTO;
import DTO.SolicitudAdopcion;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface ISubsistemaAdopciones {
    /**
     * Obtiene la lista de mascotas disponibles para adopcion.
     * @return Lista de DTOs de mascotas.
     */
    List<MascotaDTO> obtenerMascotasDisponibles();
    
    /**
     * Registra una nueva solicitud de adopcion en el sistema.
     * @param solicitud El DTO con toda la informacion.
     * @return true si el registro fue exitoso, false en caso contrario.
     */
    boolean registrarSolicitud(SolicitudAdopcion solicitud);

    /**
     * Obtiene el numero de contacto del refugio.
     * @return Un String con el numero de telefono.
     */
    String obtenerNumeroContactoRefugio();
}
