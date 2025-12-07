package EJ10;

class Jugador {
    private String nombre;
    private int nivel;
    private int puntaje;
    
    public Jugador(String nombre, int nivel, int puntaje) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntaje = puntaje;
    }
    
    public String getNombre() { return nombre; }
    public int getNivel() { return nivel; }
    public int getPuntaje() { return puntaje; }
    
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }
    
    public String toCSV() {
        return nombre + "," + nivel + "," + puntaje;
    }
    
    @Override
    public String toString() {
        return String.format("| %-15s | Nivel: %-3d | Puntaje: %-8d |", 
                             nombre, nivel, puntaje);
    }
}