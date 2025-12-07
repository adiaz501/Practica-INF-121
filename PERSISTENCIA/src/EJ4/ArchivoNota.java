package EJ4;

import com.google.gson.*;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchivoNota {
    
    private String nombreArchivo;
    private final Gson gson;
    
    public ArchivoNota(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public void escribirNotas(List<Nota> notas) {
        JsonArray jsonArray = new JsonArray();
        
        for (Nota n : notas) {
            JsonObject jsonNota = new JsonObject();
            jsonNota.addProperty("materia", n.getMateria());
            jsonNota.addProperty("notaFinal", n.getNotaFinal());
            
            Estudiante e = n.getEstudiante();
            JsonObject jsonEstudiante = new JsonObject();
            jsonEstudiante.addProperty("ru", e.getRu());
            jsonEstudiante.addProperty("nombre", e.getNombre());
            jsonEstudiante.addProperty("paterno", e.getPaterno());
            jsonEstudiante.addProperty("materno", e.getMaterno());
            jsonEstudiante.addProperty("edad", e.getEdad());
            
            jsonNota.add("estudiante", jsonEstudiante);
            jsonArray.add(jsonNota);
        }
        
        String cadenaJson = gson.toJson(jsonArray);
        
        try (Writer writer = Files.newBufferedWriter(Paths.get(nombreArchivo))) {
            writer.write(cadenaJson);
            System.out.println("Notas guardadas.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
    
    public ArrayList<Nota> leerNotas() {
        ArrayList<Nota> notas = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(nombreArchivo))) {
            
            JsonElement rootElement = JsonParser.parseReader(reader);
            
            if (rootElement.isJsonArray()) {
                JsonArray jsonArray = rootElement.getAsJsonArray();
                
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject()) {
                        JsonObject jsonNota = element.getAsJsonObject();
                        
                        String materia = jsonNota.get("materia").getAsString();
                        double notaFinal = jsonNota.get("notaFinal").getAsDouble();
                        
                        JsonObject jsonEstudiante = jsonNota.get("estudiante").getAsJsonObject();
                        
                        String ru = jsonEstudiante.get("ru").getAsString();
                        String nombre = jsonEstudiante.get("nombre").getAsString();
                        String paterno = jsonEstudiante.get("paterno").getAsString();
                        String materno = jsonEstudiante.get("materno").getAsString();
                        int edad = jsonEstudiante.get("edad").getAsInt();
                        
                        Estudiante estudiante = new Estudiante(ru, nombre, paterno, materno, edad);
                        
                        notas.add(new Nota(materia, notaFinal, estudiante));
                    }
                }
            }
        } catch (IOException e) {
        } catch (Exception e) {
            System.err.println("Error al parsear el contenido JSON: " + e.getMessage());
        }
        return notas;
    }
    
    // b) Implementar un método para agregar a varios estudiantes.
    public void agregarEstudiantes(ArrayList<Nota> nuevasNotas) {
        ArrayList<Nota> notas = leerNotas();
        notas.addAll(nuevasNotas);
        escribirNotas(notas); 
        System.out.println(nuevasNotas.size() + " notas/estudiantes agregados.");
    }
    
    // c) Obtener el promedio de notas de todos los estudiantes.
    public void calcularPromedioNotas() {
        ArrayList<Nota> notas = leerNotas();
        if (notas.isEmpty()) {
            System.out.println("No hay notas registradas.");
            return;
        }
        
        double promedio = notas.stream()
                .mapToDouble(Nota::getNotaFinal)
                .average()
                .orElse(0.0);
        
        System.out.println("\nPromedio general de notas: " + 
                           String.format("%.2f", promedio));
    }
    
    // d) Buscar al o los estudiantes con la mejor nota.
    public void buscarMejorNota() {
        ArrayList<Nota> notas = leerNotas();
        if (notas.isEmpty()) {
            System.out.println("No hay notas.");
            return;
        }
        
        double maxNota = notas.stream()
                .mapToDouble(Nota::getNotaFinal)
                .max()
                .orElse(0.0);
        
        System.out.println("\nEstudiante(s) con mejor nota (" + maxNota + "):");
        notas.stream()
                .filter(n -> n.getNotaFinal() == maxNota)
                .forEach(n -> System.out.println(n.getEstudiante()));
    }
    
    // e) Eliminar a todos los estudiantes de una determinada materia.
    public void eliminarPorMateria(String materia) {
        ArrayList<Nota> notas = leerNotas();
        int tamInicial = notas.size();
        notas.removeIf(n -> n.getMateria().equalsIgnoreCase(materia));
        escribirNotas(notas);
        
        int eliminados = tamInicial - notas.size();
        System.out.println("\nEliminados " + eliminados + 
                           " registros de la materia: " + materia);
    }
    
    public void mostrarTodas() {
        leerNotas().forEach(System.out::println);
    }
}