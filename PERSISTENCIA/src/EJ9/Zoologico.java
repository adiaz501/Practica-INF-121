package EJ9;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Zoologico implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id; 
    private String nombre;
    private String ubicacion; 
    private ArrayList<Animal> animales;
    
    public Zoologico(int id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.animales = new ArrayList<>();
    }
    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public ArrayList<Animal> getAnimales() { return animales; }
    
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    
    public void agregarAnimal(Animal a) {
        animales.add(a);
    }
    
    public int cantidadEspecies() {
        Set<String> especies = new HashSet<>();
        animales.forEach(a -> especies.add(a.getEspecie()));
        return especies.size();
    }
    
    public boolean estaVacio() {
        return animales.isEmpty();
    }
    
    @Override
    public String toString() {
        return "Zoologico{id=" + id + ", nombre='" + nombre + "', ubicacion='" + ubicacion + 
               "', animales_total=" + animales.size() + ", especies_distintas=" + cantidadEspecies() + "}";
    }
}