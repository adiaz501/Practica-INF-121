package EJ2;

public class Main {
    public static void main(String[] args) {
        
        ArchivoTrabajador archivo = new ArchivoTrabajador("trabajadores.json");
        
        System.out.println("\n=== 1. Crear Archivo ===");
        archivo.crearArchivo(); 
        
        System.out.println("\n=== 2. Guardar Trabajadores ===");
        
        Trabajador t1 = new Trabajador("Ana Lopez", 100, 2500.00);
        Trabajador t2 = new Trabajador("Pedro Salas", 200, 3200.00); 
        Trabajador t3 = new Trabajador("Carla Paz", 300, 2800.00);
        Trabajador t4 = new Trabajador("Luis Gomez", 400, 4000.00);
        
        archivo.guardarTrabajador(t1);
        archivo.guardarTrabajador(t2);
        archivo.guardarTrabajador(t3);
        archivo.guardarTrabajador(t4);
        
        System.out.println("\n=== Estado Inicial de los Trabajadores ===");
        archivo.mostrarTrabajadores();

        System.out.println("\n=== 3. Aumentar Salario ===");
        double aumento = 800.00;
        Trabajador tReferencia = new Trabajador(null, 200, 0); 
        archivo.aumentaSalario(aumento, tReferencia); 

        System.out.println("\n=== Estado después del aumento ===");
        archivo.mostrarTrabajadores();

        System.out.println("\n=== 4. Buscar Mayor Salario ===");
        archivo.buscarMayorSalario();

        System.out.println("\n=== 5. Ordenar por Salario ===");
        archivo.ordenarPorSalario(); 

        System.out.println("\n=== Estado después de ordenar ===");
        archivo.mostrarTrabajadores();
    }
}