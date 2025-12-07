package EJ5; 

import com.google.gson.*;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class ArchivoFarmacia {
    
    private String nombreArchivo; 
    private final Gson gson;
    
    public ArchivoFarmacia(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
 
    public void escribirFarmacias(ArrayList<Farmacia> farmacias) {
        JsonArray jsonArrayFarmacias = new JsonArray();
        
        for (Farmacia f : farmacias) {
            JsonObject jsonFarmacia = new JsonObject();
            
            jsonFarmacia.addProperty("nombreFarmacia", f.getNombreFarmacia());
            jsonFarmacia.addProperty("sucursal", f.getSucursal()); 
            jsonFarmacia.addProperty("direccion", f.getDireccion());
            jsonFarmacia.addProperty("nroMedicamentos", f.getNroMedicamentos()); 
            
            JsonArray jsonArrayMedicamentos = new JsonArray();
            for (Medicamento m : f.getMedicamentos()) {
                JsonObject jsonMedicamento = new JsonObject();
                jsonMedicamento.addProperty("nombre", m.getNombre());
                jsonMedicamento.addProperty("codMedicamento", m.getCodMedicamento());
                jsonMedicamento.addProperty("tipo", m.getTipo());
                jsonMedicamento.addProperty("precio", m.getPrecio());
                jsonArrayMedicamentos.add(jsonMedicamento);
            }
            
            jsonFarmacia.add("medicamentos", jsonArrayMedicamentos);
            jsonArrayFarmacias.add(jsonFarmacia);
        }
        
        String cadenaJson = gson.toJson(jsonArrayFarmacias);
        
        try (Writer writer = Files.newBufferedWriter(Paths.get(nombreArchivo))) {
            writer.write(cadenaJson);
            System.out.println("Farmacias guardadas.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
    
    public ArrayList<Farmacia> leerFarmacias() {
        ArrayList<Farmacia> farmacias = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(nombreArchivo))) {
            
            JsonElement rootElement = JsonParser.parseReader(reader);
            
            if (rootElement.isJsonArray()) {
                JsonArray jsonArrayFarmacias = rootElement.getAsJsonArray();
                
                for (JsonElement elementFarmacia : jsonArrayFarmacias) {
                    if (elementFarmacia.isJsonObject()) {
                        JsonObject jsonFarmacia = elementFarmacia.getAsJsonObject();
                        
                        String nombreFarmacia = jsonFarmacia.get("nombreFarmacia").getAsString();
                        int sucursal = jsonFarmacia.get("sucursal").getAsInt(); 
                        String direccion = jsonFarmacia.get("direccion").getAsString();
                        
                        Farmacia farmacia = new Farmacia(nombreFarmacia, sucursal, direccion);
                        
                        JsonArray jsonArrayMedicamentos = jsonFarmacia.getAsJsonArray("medicamentos");
                        
                        if (jsonArrayMedicamentos != null) {
                            for (JsonElement elementMedicamento : jsonArrayMedicamentos) {
                                JsonObject jsonMedicamento = elementMedicamento.getAsJsonObject();
                                String nombre = jsonMedicamento.get("nombre").getAsString();
                                int codMedicamento = jsonMedicamento.get("codMedicamento").getAsInt(); 
                                String tipo = jsonMedicamento.get("tipo").getAsString();
                                double precio = jsonMedicamento.get("precio").getAsDouble();
                                
                                farmacia.agregarMedicamento(new Medicamento(nombre, codMedicamento, tipo, precio)); 
                            }
                        }
                        farmacias.add(farmacia);
                        farmacia.actualizarNroMedicamentos(); 
                    }
                }
            }
        } catch (IOException e) {
        } catch (Exception e) {
            System.err.println("Error al parsear el contenido JSON: " + e.getMessage());
        }
        return farmacias;
    }
   
    // a) Mostrar los medicamentos para la tos, de la Sucursal número X
    public void mostrarMedicamentosTos(int sucursal) {
        ArrayList<Farmacia> farmacias = leerFarmacias();
        System.out.println("\nMedicamentos para la tos en Sucursal " + sucursal + ":");
        
        farmacias.stream()
                .filter(f -> f.getSucursal() == sucursal)
                .flatMap(f -> f.getMedicamentos().stream())
                .filter(m -> m.getTipo().equalsIgnoreCase("tos"))
                .forEach(System.out::println);
    }
    
    // b) Mostrar el número de sucursal y su dirección que tienen el medicamento
    public void mostrarSucursalesConMedicamento(String nombreMed) {
        ArrayList<Farmacia> farmacias = leerFarmacias();
        System.out.println("\nSucursales con medicamento '" + nombreMed + "':");
        
        farmacias.stream()
                .filter(f -> f.getMedicamentos().stream()
                        .anyMatch(m -> m.getNombre().equalsIgnoreCase(nombreMed)))
                .forEach(f -> System.out.println("Sucursal " + f.getSucursal() + 
                        " - " + f.getDireccion()));
    }
    
    // c) Buscar medicamentos por tipo
    public void buscarPorTipo(String tipo) {
        ArrayList<Farmacia> farmacias = leerFarmacias();
        System.out.println("\nMedicamentos de tipo '" + tipo + "':");
        
        farmacias.forEach(f -> {
            System.out.println("\nSucursal " + f.getSucursal() + " (" + f.getNombreFarmacia() + "):");
            f.getMedicamentos().stream()
                    .filter(m -> m.getTipo().equalsIgnoreCase(tipo))
                    .forEach(System.out::println);
        });
    }
    
    // d) Ordenar las farmacias según su dirección en orden alfabético.
    public void ordenarPorDireccion() {
        ArrayList<Farmacia> farmacias = leerFarmacias();
        farmacias.sort(Comparator.comparing(Farmacia::getDireccion));
        escribirFarmacias(farmacias); 
        System.out.println("\nFarmacias ordenadas por dirección y archivo actualizado.");
    }
    
    // e) Mover los medicamentos de tipo X de la farmacia Y a la farmacia Z
    public void moverMedicamentos(String tipo, int sucursalOrigen, int sucursalDestino) {
        ArrayList<Farmacia> farmacias = leerFarmacias();
        Farmacia origen = farmacias.stream()
                .filter(f -> f.getSucursal() == sucursalOrigen)
                .findFirst().orElse(null);
        Farmacia destino = farmacias.stream()
                .filter(f -> f.getSucursal() == sucursalDestino)
                .findFirst().orElse(null);
        
        if (origen == null || destino == null) {
            System.out.println("Error: Sucursal de origen o destino no encontrada.");
            return;
        }
        
        ArrayList<Medicamento> aMover = new ArrayList<>();
        origen.getMedicamentos().removeIf(m -> {
            if (m.getTipo().equalsIgnoreCase(tipo)) {
                aMover.add(m);
                return true; 
            }
            return false;
        });
        
        destino.getMedicamentos().addAll(aMover);
        
        origen.actualizarNroMedicamentos();
        destino.actualizarNroMedicamentos();
        
        escribirFarmacias(farmacias); 
        System.out.println("\n" + aMover.size() + " medicamentos de tipo '" + tipo + "' movidos de Sucursal " + sucursalOrigen + " a Sucursal " + sucursalDestino + ".");
    }
}