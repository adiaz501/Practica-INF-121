package EJ9;

import java.io.*;
import java.util.ArrayList;

public class ArchivoZoo {
    private String nombreArchivo;
    
    public ArchivoZoo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    public void guardarZoologicos(ArrayList<Zoologico> zoologicos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(nombreArchivo))) {
            oos.writeObject(zoologicos);
            System.out.println("Zoológicos guardados en archivo binario.");
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<Zoologico> leerZoologicos() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(nombreArchivo))) {
            return (ArrayList<Zoologico>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>(); 
        }
    }
    
    // a) Crear
    public void crear(ArrayList<Zoologico> zoologicos) {
        guardarZoologicos(zoologicos);
        System.out.println("Archivo creado con " + zoologicos.size() + " zoológicos.");
    }
    
    // a) Modificar (por ID)
    public void modificar(int idZoo, String nuevoNombre, String nuevaUbicacion) {
        ArrayList<Zoologico> zoologicos = leerZoologicos();
        boolean encontrado = false;
        
        for (Zoologico z : zoologicos) {
            if (z.getId() == idZoo) { 
                z.setNombre(nuevoNombre); 
                z.setUbicacion(nuevaUbicacion); 
                encontrado = true;
                break;
            }
        }
        
        if (encontrado) {
            guardarZoologicos(zoologicos);
            System.out.println("Zoológico con ID " + idZoo + " modificado.");
        } else {
            System.out.println("Zoológico no encontrado para modificar.");
        }
    }
    
    // a) Eliminar (por ID)
    public void eliminar(int idZoo) {
        ArrayList<Zoologico> zoologicos = leerZoologicos();
        int tamInicial = zoologicos.size();
        
        zoologicos.removeIf(z -> z.getId() == idZoo);
        
        if (zoologicos.size() < tamInicial) {
            guardarZoologicos(zoologicos);
            System.out.println("Zoológico con ID " + idZoo + " eliminado.");
        } else {
            System.out.println("Zoológico no encontrado para eliminar.");
        }
    }
    
    // b) Listar zoológicos con mayor variedad de especies
    public void listarConMayorVariedad() {
        ArrayList<Zoologico> zoologicos = leerZoologicos();
        if (zoologicos.isEmpty()) {
            System.out.println("No hay zoológicos.");
            return;
        }
        
        int maxEspecies = zoologicos.stream()
                .mapToInt(Zoologico::cantidadEspecies)
                .max()
                .orElse(0);
        
        System.out.println("\n=== b) ZOOLÓGICOS CON MAYOR VARIEDAD (" + maxEspecies + " especies) ===");
        zoologicos.stream()
                .filter(z -> z.cantidadEspecies() == maxEspecies)
                .forEach(System.out::println);
    }
    
    // c) Listar y eliminar zoológicos vacíos
    public void listarYEliminarVacios() {
        ArrayList<Zoologico> zoologicos = leerZoologicos();
        
        System.out.println("\n=== c) ZOOLÓGICOS VACÍOS ENCONTRADOS ===");
        zoologicos.stream()
                .filter(Zoologico::estaVacio)
                .forEach(z -> System.out.println("Vacío: " + z.getNombre() + " (ID: " + z.getId() + ")"));
        
        int tamInicial = zoologicos.size();
        zoologicos.removeIf(Zoologico::estaVacio);
        
        int eliminados = tamInicial - zoologicos.size();
        guardarZoologicos(zoologicos);
        System.out.println("Eliminados " + eliminados + " zoológicos vacíos.");
    }
    
    // d) Mostrar animales de especie X
    public void mostrarAnimalesPorEspecie(String especie) {
        ArrayList<Zoologico> zoologicos = leerZoologicos();
        System.out.println("\n=== d) ANIMALES DE ESPECIE '" + especie.toUpperCase() + "' ===");
        
        zoologicos.forEach(z -> {
            z.getAnimales().stream()
                    .filter(a -> a.getEspecie().equalsIgnoreCase(especie))
                    .forEach(a -> System.out.println("  Zoo: " + z.getNombre() + " -> " + a));
        });
    }
    
    // e) Mover los animales de un zoológico x a un zoológico y (por ID).
    public void moverAnimales(int idOrigen, int idDestino) {
        ArrayList<Zoologico> zoologicos = leerZoologicos();
        
        Zoologico origen = zoologicos.stream()
                .filter(z -> z.getId() == idOrigen)
                .findFirst().orElse(null);
        
        Zoologico destino = zoologicos.stream()
                .filter(z -> z.getId() == idDestino)
                .findFirst().orElse(null);
        
        if (origen == null || destino == null) {
            System.out.println(" Error: Uno de los zoológicos no existe.");
            return;
        }
        
        int cantidad = origen.getAnimales().size();
        destino.getAnimales().addAll(origen.getAnimales());
        origen.getAnimales().clear();
        
        guardarZoologicos(zoologicos);
        System.out.println("\n" + cantidad + " animales movidos de '" + 
                           origen.getNombre() + "' (ID: " + idOrigen + ") a '" + 
                           destino.getNombre() + "' (ID: " + idDestino + ").");
    }
}