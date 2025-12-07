package EJ8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArchRefri archivo = new ArchRefri("alimentos.json");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            ArrayList<Alimento> alimentos = new ArrayList<>();
            alimentos.add(new Alimento("Leche", sdf.parse("01/12/2025"), 2)); 
            alimentos.add(new Alimento("Yogurt", sdf.parse("20/01/2026"), 5));
            alimentos.add(new Alimento("Queso", sdf.parse("10/12/2025"), 0)); 
            alimentos.add(new Alimento("Jamón", sdf.parse("25/12/2025"), 3)); 
            alimentos.add(new Alimento("Mantequilla", sdf.parse("30/01/2026"), 4));
            alimentos.add(new Alimento("Huevos", sdf.parse("18/12/2025"), 12)); 
            
            archivo.crear(alimentos);
            
            System.out.println("\n--- LISTA INICIAL ---");
            archivo.leer().forEach(System.out::println);
            
            System.out.println("\n=== a) MODIFICAR ===");
            archivo.modificarPorNombre("Yogurt", sdf.parse("25/01/2026"), 8);
            
            System.out.println("\n=== a) ELIMINAR ===");
            archivo.eliminarPorNombre("Jamón");
            
            System.out.println("\n--- LISTA DESPUÉS DE MODIFICACIONES---");
            archivo.leer().forEach(System.out::println);
            
            archivo.mostrarCaducadosAntesDe(sdf.parse("20/12/2025")); 
            
            archivo.eliminarConCantidadCero();
            System.out.println("\n--- LISTA DESPUÉS DE ELIMINAR CANTIDAD 0 ---");
            archivo.leer().forEach(System.out::println);

            archivo.buscarVencidos();
            
            archivo.mostrarConMasCantidad();
            
        } catch (ParseException e) {
            System.err.println("Error en formato de fecha: " + e.getMessage());
        }
    }
}