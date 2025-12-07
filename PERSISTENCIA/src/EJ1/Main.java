package EJ1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArchivoCharango archivo = new ArchivoCharango("charangos.json");
        
        List<Charango> charangos = new ArrayList<>();
        
        boolean[] cuerdas1 = {true, true, false, false, true, true, false, false, true, true};
        charangos.add(new Charango("Madera", 10, cuerdas1));
        
        boolean[] cuerdas2 = {true, true, true, true, true, true, true, false, false, false};
        charangos.add(new Charango("Caparazón", 10, cuerdas2));
        
        boolean[] cuerdas3 = {true, true, true, true, true, true, true, true, true, true};
        charangos.add(new Charango("Madera", 10, cuerdas3));
        
        boolean[] cuerdas4 = {false, false, false, false, false, false, false, true, true, true};
        charangos.add(new Charango("Caparazón", 10, cuerdas4));
        
        archivo.guardarCharangos(charangos);
        
        System.out.println("\n=== PRUEBA INICIAL (Desde JSON) ===");
        archivo.leerCharangos().forEach(System.out::println);
        
        System.out.println("\n=== ELIMINAR CON MAS DE 6 CUERDAS ROTAS ===");
        archivo.eliminarCharangosCuerdasRotas();
        System.out.println("\nLista después de eliminar:");
        archivo.leerCharangos().forEach(System.out::println);
        
        System.out.println("\n=== LISTAR POR MATERIAL ===");
        archivo.listarPorMaterial("Madera");
        
        System.out.println("\n=== BUSCAR CON 10 CUERDAS ===");
        archivo.buscarCon10Cuerdas();
        
        System.out.println("\n=== ORDENAR POR MATERIAL EN ORDEN ALFABÉTICO ===");
        archivo.ordenarPorMaterial();
        System.out.println("\nLista después de ordenar:");
        archivo.leerCharangos().forEach(System.out::println);
    }
}