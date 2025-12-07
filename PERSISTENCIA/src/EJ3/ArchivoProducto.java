package EJ3;

import com.google.gson.*;
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

public class ArchivoProducto {
    
    private String nombreArch; 
    private final Gson gson;
    
    public ArchivoProducto(String nombreArchivo) {
        this.nombreArch = nombreArchivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    private List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(nombreArch))) {
            
            JsonElement rootElement = JsonParser.parseReader(reader);
            
            if (rootElement.isJsonArray()) {
                JsonArray jsonArray = rootElement.getAsJsonArray();
                
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject()) {
                        JsonObject jsonObject = element.getAsJsonObject();
                        
                        int codigo = jsonObject.get("codigo").getAsInt();
                        String nombre = jsonObject.get("nombre").getAsString();
                        float precio = jsonObject.get("precio").getAsFloat(); 
                        
                        productos.add(new Producto(codigo, nombre, precio));
                    }
                }
            }
        } catch (IOException e) {
        } catch (Exception e) {
            System.err.println("Error al parsear el contenido JSON: " + e.getMessage());
        }
        return productos;
    }
    
    private void escribirProductos(List<Producto> productos) {
        JsonArray jsonArray = new JsonArray();
        
        for (Producto p : productos) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("codigo", p.getCodigo());
            jsonObject.addProperty("nombre", p.getNombre());
            jsonObject.addProperty("precio", p.getPrecio());
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
    
    public void crearArchivo() {
        if (Files.exists(Paths.get(nombreArch))) {
            System.out.println("Archivo '" + nombreArch + "' ya existe.");
            return;
        }
        
        escribirProductos(new ArrayList<>());
        System.out.println("Archivo '" + nombreArch + "' creado y guardado exitosamente.");
    }
    
    public void guardarProducto(Producto p) {
        List<Producto> productos = leerProductos();
        
        boolean existe = productos.stream()
            .anyMatch(prod -> prod.getCodigo() == p.getCodigo());
        
        if (existe) {
            List<Producto> actualizados = productos.stream()
                .map(prod -> prod.getCodigo() == p.getCodigo() ? p : prod)
                .collect(Collectors.toList());
            escribirProductos(actualizados);
            System.out.println("Producto con código " + p.getCodigo() + " actualizado.");
        } else {
            productos.add(p);
            escribirProductos(productos);
            System.out.println("Producto agregado: " + p.getNombre());
        }
    }
    
    public Producto buscaProducto(int codigo) {
        List<Producto> productos = leerProductos();
        
        System.out.println("\nBuscando producto con código: " + codigo);
        
        return productos.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }
    
    public void calcularPromedioPrecios() {
        List<Producto> productos = leerProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos para calcular el promedio.");
            return;
        }
        
        OptionalDouble promedio = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average();
                
        System.out.println("\nPromedio de precios: Bs. " + 
                           String.format("%.2f", promedio.orElse(0.0)));
    }
    
    public void mostrarMasCaro() {
        List<Producto> productos = leerProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos para mostrar el más caro.");
            return;
        }
        
        Producto masCaro = productos.stream()
                .max(Comparator.comparingDouble(Producto::getPrecio))
                .orElse(null);
        
        System.out.println("\nProducto más caro:");
        System.out.println(masCaro);
    }
    
    public void mostrarTodos() {
        List<Producto> productos = leerProductos();
        if (productos.isEmpty()) {
            System.out.println("La lista de productos está vacía.");
        } else {
            productos.forEach(System.out::println);
        }
    }
}