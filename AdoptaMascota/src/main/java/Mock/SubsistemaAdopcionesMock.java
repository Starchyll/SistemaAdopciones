/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mock;

import DTO.MascotaDTO;
import DTO.SolicitudAdopcion;
import Interface.ISubsistemaAdopciones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mmax2
 */
public class SubsistemaAdopcionesMock implements ISubsistemaAdopciones{
     @Override
    public List<MascotaDTO> obtenerMascotasDisponibles() {
        List<MascotaDTO> mascotas = new ArrayList<>();
        
        // Usamos las imagenes que subiste, asumiendo que estan en la ruta de recursos
        mascotas.add(new MascotaDTO(
                "Salchicho", "Perro", "Dachshund", 3, "Ninguna", 
                "/IMG/imgi_1_macho-letra-N.jpg"));
        
        mascotas.add(new MascotaDTO(
                 "Pelusa", "Perro", "Yorkshire", 2, "Ninguna", 
                "/IMG/imgi_1_61OrHjHFlXL.jpg"));
        
        mascotas.add(new MascotaDTO(
                 "Max", "Perro", "Golden Retriever", 5, "Cadera sensible", 
                "/IMG/imgi_1_71vwkcrD5mL.jpg"));

        mascotas.add(new MascotaDTO(
                 "Oreo", "Perro", "Border Collie", 1, "Mucha energia", 
                "/IMG/imgi_1_91m34y-EkTL.jpg"));
        
        return mascotas;
    }

    @Override
    public boolean registrarSolicitud(SolicitudAdopcion solicitud) {
        System.out.println("----- Solicitud Recibida por el Subsistema -----");
        System.out.println("Mascota: " + solicitud.getMascotaNombre() + " (ID: " + solicitud.getMascotaId() + ")");
        System.out.println("Fecha Cita: " + solicitud.getFechaCita().toString());
        System.out.println("Adoptante: " + solicitud.getInfoPersona().getNombre());
        System.out.println("Carta Compromiso: " + solicitud.getRazones().getCartaCompromiso().substring(0, 20) + "...");
        System.out.println("-------------------------------------------------");
        
        // Simulamos que siempre se registra con exito
        return true; 
    }
    
    @Override
    public String obtenerNumeroContactoRefugio() {
        return "+52 (644) 123-4567";
    }
}
