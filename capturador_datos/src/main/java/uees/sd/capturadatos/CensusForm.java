/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.capturadatos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author ninoska
 */
public class CensusForm {
    @JsonProperty("id")
    public String id;

    @JsonProperty("nombre")
    public String nombre;

    @JsonProperty("apellido")
    public String apellido;

    @JsonProperty("edad")
    public int edad;

    @JsonProperty("genero")
    public String genero;

    @JsonProperty("direccion")
    public String direccion;

    @JsonProperty("ciudad")
    public String ciudad;

    @JsonProperty("estado")
    public String estado;

    @JsonProperty("codigoPostal")
    public String codigoPostal;

    @JsonProperty("pais")
    public String pais;

    @JsonProperty("telefono")
    public String telefono;

    @JsonProperty("email")
    public String email;

    @JsonProperty("ocupacion")
    public String ocupacion;

    @JsonProperty("estadoCivil")
    public String estadoCivil;

    @JsonProperty("numeroDeHijos")
    public int numeroDeHijos;

    @JsonProperty("nivelEducativo")
    public String nivelEducativo;

    @JsonProperty("ingresoAnual")
    public double ingresoAnual;

    @JsonProperty("tieneMascotas")
    public boolean tieneMascotas;

    @JsonProperty("numeroDeMascotas")
    public int numeroDeMascotas;

    @JsonProperty("tamanoHogar")
    public int tamanoHogar;
}
