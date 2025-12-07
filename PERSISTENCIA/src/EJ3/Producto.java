package EJ3;

public class Producto {
    private int codigo;
    private String nombre;
    private float precio;
    
    public Producto(int codigo, String nombre, float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public float getPrecio() { return precio; }

    @Override
    public String toString() {
        return "Producto{codigo=" + codigo + ", nombre='" + nombre + 
               "', precio=" + String.format("%.2f", precio) + "}";
    }
}