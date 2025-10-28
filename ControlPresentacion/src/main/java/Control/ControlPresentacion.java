/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DTO.MascotaDTO;
import DTO.SolicitudAdopcion;
import Interface.ISubsistemaAdopciones;
import Interfaz.IControlPresentacion;
import Mock.SubsistemaAdopcionesMock;
import java.util.List;

/**
 *
 * @author mmax2
 */
public class ControlPresentacion implements IControlPresentacion {
    
    private ISubsistemaAdopciones subsistema;

    public ControlPresentacion() {
        
        this.subsistema = new SubsistemaAdopcionesMock();
    }

    @Override
    public List<MascotaDTO> getMascotasDisponibles() {
        return subsistema.obtenerMascotasDisponibles();
    }

    @Override
    public String getNumeroContacto() {
        
        return subsistema.obtenerNumeroContactoRefugio();
    }
    
    @Override
    public boolean procesarSolicitud(SolicitudAdopcion solicitud) {
        
        return subsistema.registrarSolicitud(solicitud);
    }
}
