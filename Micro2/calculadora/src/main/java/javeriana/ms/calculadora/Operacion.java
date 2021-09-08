package javeriana.ms.calculadora;


public class Operacion {

    private String nombre;
    private String fecha;
    private String user;

    public Operacion(){

    }
    public String getNombre() {
        return nombre;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
