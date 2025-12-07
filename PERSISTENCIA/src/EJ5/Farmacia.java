package EJ5; 

import java.util.ArrayList;

public class Farmacia {
    private String nombreFarmacia; 
    private int sucursal;       
    private String direccion;
    private int nroMedicamentos; 
    
    private ArrayList<Medicamento> medicamentos; 
    
    public Farmacia(String nombreFarmacia, int sucursal, String direccion) {
        this.nombreFarmacia = nombreFarmacia;
        this.sucursal = sucursal;
        this.direccion = direccion;
        this.medicamentos = new ArrayList<>();
        this.nroMedicamentos = 0; 
    }
    
    public String getDireccion() { return direccion; }
    public int getSucursal() { return sucursal; }
    public String getNombreFarmacia() { return nombreFarmacia; }
    public int getNroMedicamentos() { return nroMedicamentos; }
    public ArrayList<Medicamento> getMedicamentos() { return medicamentos; }
    
    public void agregarMedicamento(Medicamento m) {
        medicamentos.add(m);
        this.nroMedicamentos = medicamentos.size(); 
    }
    
    public void actualizarNroMedicamentos() {
        this.nroMedicamentos = medicamentos.size();
    }
    
    @Override
    public String toString() {
        return "Farmacia{nombre='" + nombreFarmacia + "', sucursal=" + sucursal + 
               ", direccion='" + direccion + "', nroMedicamentos=" + nroMedicamentos + "}";
    }
}