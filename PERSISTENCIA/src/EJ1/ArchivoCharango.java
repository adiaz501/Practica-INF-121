package EJ1;

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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.IOException;

public class ArchivoCharango {
    private String nombreArchivo;
    private Gson gson;

    public ArchivoCharango(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create(); 
    }

    public void guardarCharangos(List<Charango> charangos) {
        JsonArray jsonArray = new JsonArray();
        
        for (Charango c : charangos) {
            JsonObject jsonObject = new JsonObject();
            
            jsonObject.addProperty("material", c.getMaterial());
            jsonObject.addProperty("nroCuerdas", c.getNroCuerdas());
            
            JsonArray cuerdasArray = new JsonArray();
            for (boolean cuerda : c.getCuerdas()) {
                cuerdasArray.add(cuerda);
            }
            jsonObject.add("cuerdas", cuerdasArray);
            
            jsonArray.add(jsonObject);
        }
        
        String cadenaJson = gson.toJson(jsonArray);
        
        try (Writer writer = Files.newBufferedWriter(Paths.get(nombreArchivo))) {
            writer.write(cadenaJson);
            System.out.println(">>> ÉXITO: Charangos guardados en '" + nombreArchivo + "'.");
        } catch (IOException e) {
            System.err.println("!!! ERROR al escribir el archivo '" + nombreArchivo + "'.");
            e.printStackTrace();
        }
    }

    // Leer
    public List<Charango> leerCharangos() {
        List<Charango> charangos = new ArrayList<>();
        
        try (Reader reader = Files.newBufferedReader(Paths.get(nombreArchivo))) {
            
            JsonElement rootElement = JsonParser.parseReader(reader);
            
            if (rootElement.isJsonArray()) {
                JsonArray jsonArray = rootElement.getAsJsonArray();
                
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject()) {
                        JsonObject jsonObject = element.getAsJsonObject();
                        
                        String material = jsonObject.get("material").getAsString();
                        int nroCuerdas = jsonObject.get("nroCuerdas").getAsInt();
                        
                        JsonArray cuerdasJson = jsonObject.getAsJsonArray("cuerdas");
                        boolean[] cuerdas = new boolean[cuerdasJson.size()];
                        for (int i = 0; i < cuerdasJson.size(); i++) {
                            cuerdas[i] = cuerdasJson.get(i).getAsBoolean();
                        }
                        
                        charangos.add(new Charango(material, nroCuerdas, cuerdas));
                    }
                }
            }
            return charangos;
            
        } catch (IOException e) {
            System.err.println(" No se pudo leer '" + nombreArchivo + "'. lista vacía.");
            return new ArrayList<>();
        }
    }
    
    // b) Eliminar charangos con más de 6 cuerdas rotas
    public void eliminarCharangosCuerdasRotas() {
        List<Charango> charangos = leerCharangos();
        int antes = charangos.size();
        
        charangos.removeIf(c -> c.contarCuerdasRotas() > 6);
        
        if (charangos.size() < antes) {
            guardarCharangos(charangos);
            System.out.println("Charangos con mas de 6 cuerdas rotas eliminados y archivo actualizado.");
        } else {
            System.out.println("No se encontraron charangos para eliminar.");
        }
    }
    
    // c) Listar charangos de material X
    public void listarPorMaterial(String material) {
        List<Charango> charangos = leerCharangos();
        System.out.println("\nCharangos de material '" + material + "':");
        charangos.stream()
                .filter(c -> c.getMaterial().equalsIgnoreCase(material))
                .forEach(System.out::println);
    }
    
    // d) Buscar charangos con 10 cuerdas
    public void buscarCon10Cuerdas() {
        List<Charango> charangos = leerCharangos();
        System.out.println("\nCharangos con 10 cuerdas:");
        charangos.stream()
                .filter(c -> c.getNroCuerdas() == 10)
                .forEach(System.out::println);
    }
    
    // e) Ordenar por material alfabéticamente
    public void ordenarPorMaterial() {
        List<Charango> charangos = leerCharangos();
        charangos.sort(Comparator.comparing(Charango::getMaterial));
        guardarCharangos(charangos);
        System.out.println("Charangos ordenados por material y archivo actualizado.");
    }
}