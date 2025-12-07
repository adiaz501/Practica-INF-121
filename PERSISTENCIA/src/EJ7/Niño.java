package EJ7;

public class Niño extends Persona {
   
    private int edad;
    private double peso;
    private double talla;
    
    public Niño(String nombre, String apellidoPaterno, String apellidoMaterno, int ci, 
                int edad, double peso, double talla) {
        super(nombre, apellidoPaterno, apellidoMaterno, ci);
        this.edad = edad;
        this.peso = peso;
        this.talla = talla;
    }
    
    public int getEdad() { return edad; }
    public double getPeso() { return peso; }
    public double getTalla() { return talla; }
    public String getCarnet() { return String.valueOf(super.getCi()); }
    
    public boolean tienePesoAdecuado() {
        double pesoEsperado = 10 + Math.max(0, edad - 1) * 3;
        return Math.abs(peso - pesoEsperado) <= 5;
    }
    
    public boolean tieneTallaAdecuada() {
        double tallaEsperada = 75 + (edad * 5);
        return Math.abs(talla - tallaEsperada) <= 10;
    }
    
    @Override
    public String toString() {
        return "Niño{" + super.toString() + 
               ", edad=" + edad + ", peso=" + String.format("%.1f", peso) + 
               "kg, talla=" + String.format("%.1f", talla) + "cm}";
    }
}