package EJ7;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArchNiño archivo = new ArchNiño("niños.json");
        
        ArrayList<Niño> niños = new ArrayList<>();
        
        niños.add(new Niño("Juan", "Pérez", "López", 1001, 5, 18.0, 105.0));       
        niños.add(new Niño("María", "López", "Gómez", 1002, 6, 22.0, 115.0)); 
        niños.add(new Niño("Carlos", "García", "Ramos", 1003, 4, 14.0, 95.0));
        niños.add(new Niño("Ana", "Martínez", "Díaz", 1004, 7, 28.0, 125.0));
        niños.add(new Niño("Luis", "Rodríguez", "Soto", 1005, 5, 25.0, 125.0));
        niños.add(new Niño("Sofia", "Torres", "Vega", 1006, 6, 20.0, 112.0));
        
        archivo.crear(niños);
        
        archivo.listar();
        
        archivo.mostrar(1003);
        
        archivo.contarPesoTallaAdecuados();
        
        archivo.mostrarSinPesoTallaAdecuada();
        
        archivo.calcularPromedioEdad();
        
        System.out.println("\n=== e) BUSCAR POR CARNET (CI) 1004 ===");
        archivo.buscarPorCarnet(1004);
        
        archivo.mostrarMayorTalla();
    }
}