package EJ5;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArchivoFarmacia archivo = new ArchivoFarmacia("farmacias.json"); 
       
        Farmacia f1 = new Farmacia("Farmacia Central", 1, "Av. 6 de Agosto");
        f1.agregarMedicamento(new Medicamento("Tapsin", 1001, "tos", 8.50));
        f1.agregarMedicamento(new Medicamento("Ibuprofeno", 1002, "dolor", 5.00));
        f1.agregarMedicamento(new Medicamento("Dextrometorfano", 1003, "tos", 12.00));
        f1.agregarMedicamento(new Medicamento("Aspirina", 1004, "dolor", 4.00));
        
        Farmacia f2 = new Farmacia("Farmacia Sucursal", 2, "Calle Comercio");
        f2.agregarMedicamento(new Medicamento("Tapsin", 2001, "tos", 8.50));
        f2.agregarMedicamento(new Medicamento("Paracetamol", 2002, "fiebre", 3.50));
        f2.agregarMedicamento(new Medicamento("Amoxicilina", 2003, "antibiotico", 20.00));
        
        Farmacia f3 = new Farmacia("Farmacia La Paz", 3, "Av. Buenos Aires");
        f3.agregarMedicamento(new Medicamento("Ambroxol", 3001, "tos", 10.00));
        f3.agregarMedicamento(new Medicamento("Amoxicilina", 3002, "antibiotico", 20.00));
        f3.agregarMedicamento(new Medicamento("Vit. C", 3003, "vitaminas", 7.00));
        
        ArrayList<Farmacia> farmacias = new ArrayList<>();
        farmacias.add(f1);
        farmacias.add(f2);
        farmacias.add(f3);
        
        archivo.escribirFarmacias(farmacias); 
        
        
        System.out.println("\n=== MEDICAMENTOS PARA TOS EN SUCURSAL 1 ===");
        archivo.mostrarMedicamentosTos(1);
        
        System.out.println("\n=== SUCURSALES CON TAPSIN ===");
        archivo.mostrarSucursalesConMedicamento("Tapsin");
        
        System.out.println("\n=== BUSCAR POR TIPO ===");
        archivo.buscarPorTipo("antibiotico");
        
        System.out.println("\n==================================================");
        System.out.println("d) ORDENAR POR DIRECCIÓN");
        archivo.ordenarPorDireccion();
        
        System.out.println("\n--- Farmacias después de ordenar ---");
        archivo.leerFarmacias().forEach(System.out::println); 
        
        System.out.println("\n==================================================");
        System.out.println("e) MOVER MEDICAMENTOS TOS DE SUC 1 A SUC 2");
        archivo.moverMedicamentos("tos", 1, 2);
        
        System.out.println("\n--- Contenido después de mover ---");
        archivo.leerFarmacias().forEach(f -> {
            System.out.println(f);
            f.getMedicamentos().forEach(m -> System.out.println("  " + m));
        });
    }
}