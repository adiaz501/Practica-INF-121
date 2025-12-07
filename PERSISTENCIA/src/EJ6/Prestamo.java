package EJ6;

public class Prestamo {
    
    private String codCliente;
    private int codLibro;
    private String fechaPrestamo;
    private int cantidad;
    
    public Prestamo(String codCliente, int codLibro, String fechaPrestamo, int cantidad) {
        this.codCliente = codCliente;
        this.codLibro = codLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.cantidad = cantidad;
    }
    
    public String getCodCliente() { return codCliente; }
    public int getCodLibro() { return codLibro; }
    public String getFechaPrestamo() { return fechaPrestamo; }
    public int getCantidad() { return cantidad; }
    
    @Override
    public String toString() {
        return "Prestamo{codLibro=" + codLibro + ", codCliente=" + codCliente + 
               ", fecha=" + fechaPrestamo + ", cantidad=" + cantidad + "}";
    }
}