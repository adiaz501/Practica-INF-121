package EJ1;

public class Charango {
    private String material;
    private int nroCuerdas;
    private boolean[] cuerdas;
    
    public Charango(String material, int nroCuerdas, boolean[] cuerdas) {
        this.material = material;
        this.nroCuerdas = nroCuerdas;
        this.cuerdas = cuerdas;
    }
    
    public String getMaterial() { return material; }
    public int getNroCuerdas() { return nroCuerdas; }
    public boolean[] getCuerdas() { return cuerdas; }
    
    public int contarCuerdasRotas() {
        int c = 0;
        for (boolean cuerda : cuerdas) {
            if (!cuerda) c++;
        }
        return c;
    }
    
    @Override
    public String toString() {
        return "Charango{material='" + material + "', nroCuerdas=" + nroCuerdas + 
               ", cuerdas rotas=" + contarCuerdasRotas() + "}";
    }
}