/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author mmax2
 */
public class Razones_AntecedentesDTO {
    private String razonesParaAdoptar;
    private String antecedentesConMascotas;
    private boolean dispuestoARecibirSeguimiento;
    private String cartaCompromiso;
    
    public Razones_AntecedentesDTO() {
    }

    public String getRazonesParaAdoptar() {
        return razonesParaAdoptar;
    }

    public void setRazonesParaAdoptar(String razonesParaAdoptar) {
        this.razonesParaAdoptar = razonesParaAdoptar;
    }

    public String getAntecedentesConMascotas() {
        return antecedentesConMascotas;
    }

    public void setAntecedentesConMascotas(String antecedentesConMascotas) {
        this.antecedentesConMascotas = antecedentesConMascotas;
    }

    public boolean isDispuestoARecibirSeguimiento() {
        return dispuestoARecibirSeguimiento;
    }

    public void setDispuestoARecibirSeguimiento(boolean dispuestoARecibirSeguimiento) {
        this.dispuestoARecibirSeguimiento = dispuestoARecibirSeguimiento;
    }

    public String getCartaCompromiso() {
        return cartaCompromiso;
    }

    public void setCartaCompromiso(String cartaCompromiso) {
        this.cartaCompromiso = cartaCompromiso;
    }
}
