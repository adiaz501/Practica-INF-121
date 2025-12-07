package EJ2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class ArchivoTrabajador {
    
    private String nombreArch; 
    private final Gson gson;
    
    public ArchivoTrabajador(String nombreArchivo) {
        this.nombreArch = nombreArchivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    private List<Trabajador> leerArchivo() {
        List<Trabajador> trabajadores = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(nombreArch))) {
            
            JsonElement rootElement = JsonParser.parseReader(reader);
            
            if (rootElement.isJsonArray()) {
                JsonArray jsonArray = rootElement.getAsJsonArray();
                
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject()) {
                        JsonObject jsonObject = element.getAsJsonObject();
                        
                        String nombre = jsonObject.get("nombre").getAsString();
                        int carnet = jsonObject.get("carnet").getAsInt();
                        double salario = jsonObject.get("salario").getAsDouble();
                        
                        trabajadores.add(new Trabajador(nombre, carnet, salario));
                    }
                }
            }
        } catch (IOException e) {
        } catch (Exception e) {
            System.err.println("Error al parsear el contenido JSON: " + e.getMessage());
        }
        return trabajadores;
    }
    
    private void escribirArchivo(List<Trabajador> trabajadores) {
        JsonArray jsonArray = new JsonArray();
        
        for (Trabajador t : trabajadores) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("nombre", t.getNombre());
            jsonObject.addProperty("carnet", t.getCarnet());
            jsonObject.addProperty("salario", t.getSalario());
            jsonArray.add(jsonObject);
        }
        
        String cadenaJson = gson.toJson(jsonArray);
        
        try (Writer writer = Files.newBufferedWriter(Paths.get(nombreArch))) {
            writer.write(cadenaJson);
        } catch (IOException e) {
            System.err.println("ERROR al escribir el archivo '" + nombreArch + "'.");
            e.printStackTrace();
        }
    }
    

    // a) Implementa un método para crear y guardar el archivo.
    public void crearArchivo() {
        if (Files.exists(Paths.get(nombreArch))) {
            System.out.println("Archivo '" + nombreArch + "' ya existe.");
            return;
        }
        escribirArchivo(new ArrayList<>());
        System.out.println("Archivo '" + nombreArch + "' creado y guardado exitosamente.");
    }
    
    // b) Implementa un método para guardar
    public void guardarTrabajador(Trabajador t) {
        List<Trabajador> trabajadores = leerArchivo();
        int carnetNuevo = t.getCarnet();
        boolean encontrado = false;

        for (int i = 0; i < trabajadores.size(); i++) {
            Trabajador trabajadorExistente = trabajadores.get(i);
            
            if (trabajadorExistente.getCarnet() == carnetNuevo) {
                trabajadores.set(i, t); 
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            trabajadores.add(t);
            System.out.println("Trabajador con Carnet " + t.getCarnet() + " agregado.");
        } else {
            System.out.println("Trabajador con Carnet " + t.getCarnet() + " actualizado.");
        }

        escribirArchivo(trabajadores);
    }
    
    // c) Implementa un método para aumentar el salario de un trabajador.
    public void aumentaSalario(double aumento, Trabajador t) {
        List<Trabajador> trabajadores = leerArchivo();
        int carnetBuscado = t.getCarnet();
        boolean encontrado = false;
        
        for (Trabajador trabajadorPersistente : trabajadores) {
            if (trabajadorPersistente.getCarnet() == carnetBuscado) {
                trabajadorPersistente.setSalario(trabajadorPersistente.getSalario() + aumento);
                encontrado = true;
                break;
            }
        }
        
        if (encontrado) {
            escribirArchivo(trabajadores); 
            System.out.println("Salario aumentado en " + String.format("%.2f", aumento) + " para el Carnet: " + carnetBuscado);
        } else {
            System.out.println("Error: Trabajador con Carnet " + carnetBuscado + " no encontrado.");
        }
    }
    
 // d) Buscar todos los trabajadores con el mayor salario
    public void buscarMayorSalario() {
        List<Trabajador> trabajadores = leerArchivo();
        
        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores para buscar.");
            return;
        }

        OptionalDouble maxSalario = trabajadores.stream()
                .mapToDouble(Trabajador::getSalario)
                .max();

        if (maxSalario.isPresent()) {
            double salarioMasAlto = maxSalario.getAsDouble(); 

            List<Trabajador> trabajadoresConSalarioMaximo = trabajadores.stream()
                    .filter(t -> t.getSalario() == salarioMasAlto)
                    .collect(Collectors.toList());

            System.out.println("\nTrabajador(es) con mayor salario (" + String.format("%.2f", salarioMasAlto) + "):");
            
            trabajadoresConSalarioMaximo.forEach(System.out::println);
            
        } else {
            System.out.println("Error al calcular el salario máximo.");
        }
    }
    
    // e) Ordenar a los trabajadores por su salario.
    public void ordenarPorSalario() {
        List<Trabajador> trabajadores = leerArchivo();
        trabajadores.sort(Comparator.comparingDouble(Trabajador::getSalario)); 
        escribirArchivo(trabajadores);
        System.out.println("Trabajadores ordenados por salario y archivo actualizado.");
    }
    
    public void mostrarTrabajadores() {
        List<Trabajador> trabajadores = leerArchivo();
        if (trabajadores.isEmpty()) {
            System.out.println("La lista de trabajadores está vacía.");
        } else {
            trabajadores.forEach(System.out::println);
        }
    }
}