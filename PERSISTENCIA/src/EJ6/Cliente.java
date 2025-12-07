package EJ6;

public class Cliente {
    
    private int codCliente;
    private String ci;  
    private String nombre;
    private String apellido;
    
    public Cliente(int codCliente, String ci, String nombre, String apellido) {
        this.setCodCliente(codCliente);
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public String getCi() { return ci; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    
   
	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	} 
	
	@Override
    public String toString() {
        return "Cliente{CI='" + ci + "', nombre='" + nombre + " " + apellido + "'}";
    }

}