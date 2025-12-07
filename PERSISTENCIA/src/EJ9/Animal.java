package EJ9;

import java.io.Serializable;

class Animal implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    private String nombre;
    private String especie;
    private int cantidad; 
    
    public Animal(String nombre, String especie, int cantidad) {
        this.nombre = nombre;
        this.especie = especie;
        this.cantidad = cantidad;
    }
    
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public int getCantidad() { return cantidad; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecie(String especie) { this.especie = especie; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "Animal{nombre='" + nombre + "', especie='" + especie + 
               "', cantidad=" + cantidad + "}";
    }
}