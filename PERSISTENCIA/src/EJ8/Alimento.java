package EJ8;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Alimento {
    
    private String nombre;
    private Date fechaVencimiento; 
    private int cantidad;
    
    public Alimento(String nombre, Date fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
    }
    
    public String getNombre() { return nombre; }
    public Date getFechaVencimiento() { return fechaVencimiento; }
    public int getCantidad() { return cantidad; }
    public void setFechaVencimiento(Date fecha) { this.fechaVencimiento = fecha; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public boolean estaVencido() {
        return fechaVencimiento.before(new Date());
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Alimento{nombre='" + nombre + 
               "', fechaVencimiento=" + sdf.format(fechaVencimiento) + 
               ", cantidad=" + cantidad + "}";
    }
}