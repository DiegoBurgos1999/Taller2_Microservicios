package co.edu.javeriana.ws.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Paseo {
    
    private Long identificador;
    private String nombre;
    private String lugar_salida;
    private String lugar_llegada;
    private String fecha;

    public Paseo(){

    }

    public Long getIdentificador() {
        return identificador;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getLugar_llegada() {
        return lugar_llegada;
    }
    public void setLugar_llegada(String lugar_llegada) {
        this.lugar_llegada = lugar_llegada;
    }
    public String getLugar_salida() {
        return lugar_salida;
    }
    public void setLugar_salida(String lugar_salida) {
        this.lugar_salida = lugar_salida;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

}
