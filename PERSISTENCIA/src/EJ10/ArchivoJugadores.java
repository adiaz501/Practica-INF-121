package EJ10;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

class ArchivoJugadores {
    private String nombreArchivo = "jugadores.txt";
    
    public void guardarJugadores(ArrayList<Jugador> jugadores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Jugador j : jugadores) {
                writer.println(j.toCSV()); 
            }
        } catch (IOException e) {
            System.err.println("Error al guardar en " + nombreArchivo + ": " + e.getMessage());
        }
    }
    public ArrayList<Jugador> leerJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    try {
                        String nombre = datos[0];
                        int nivel = Integer.parseInt(datos[1]);
                        int puntaje = Integer.parseInt(datos[2]);
                        jugadores.add(new Jugador(nombre, nivel, puntaje));
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en línea: " + linea);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + nombreArchivo + " no encontrado. Se creará uno nuevo.");
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
        return jugadores;
    }
    
    public void agregarJugador(Jugador j) {
        ArrayList<Jugador> jugadores = leerJugadores();
        jugadores.add(j);
        guardarJugadores(jugadores);
        System.out.println("Jugador '" + j.getNombre() + "' agregado con éxito.");
    }
    
    public void mostrarTodos() {
        ArrayList<Jugador> jugadores = leerJugadores();
        System.out.println("LISTA DE JUGADORES (Total: " + jugadores.size() + ")");
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados.");
        } else {
            jugadores.forEach(System.out::println);
        }
    }
    
    public void buscarPorNombre(String nombre) {
        ArrayList<Jugador> jugadores = leerJugadores();
        
        System.out.println("\nBuscando jugador: " + nombre);
        
        Optional<Jugador> encontrado = jugadores.stream()
                .filter(j -> j.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
        
        if (encontrado.isPresent()) {
            System.out.println("Encontrado: " + encontrado.get());
        } else {
            System.out.println("Jugador '" + nombre + "' no encontrado.");
        }
    }
    
    public void mostrarMejorJugador() {
        ArrayList<Jugador> jugadores = leerJugadores();
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores para determinar el mejor.");
            return;
        }
        
        Optional<Jugador> mejor = jugadores.stream()
                .max(Comparator.comparingInt(Jugador::getPuntaje));
        
        mejor.ifPresent(j -> {
            System.out.println("\n MEJOR JUGADOR POR PUNTAJE:");
            System.out.println(j);
        });
    }

    public void actualizarPuntaje(String nombre, int nuevoPuntaje) {
        ArrayList<Jugador> jugadores = leerJugadores();
        boolean encontrado = false;
        
        for (Jugador j : jugadores) {
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                j.setPuntaje(nuevoPuntaje);
                encontrado = true;
                break;
            }
        }
        
        if (encontrado) {
            guardarJugadores(jugadores);
            System.out.println("Puntaje de '" + nombre + "' actualizado a " + nuevoPuntaje);
        } else {
            System.out.println("Jugador no encontrado para actualizar.");
        }
    }
}