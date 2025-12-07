package EJ6;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    
    private static final ArchLibro archLibro = new ArchLibro("libros.json");
    private static final ArchCliente archCliente = new ArchCliente("clientes.json");
    private static final ArchPrestamo archPrestamo = new ArchPrestamo("prestamos.json");

    // a) Listar libros cuyo precio estén entre 2 valores (x e y).
    public static void listarLibrosPorPrecio(double minPrecio, double maxPrecio) {
        ArrayList<Libro> libros = archLibro.leer();
        System.out.println("\n=== a) Libros con precio entre " + minPrecio + " y " + maxPrecio + " ===");
        libros.stream()
                .filter(l -> l.getPrecio() >= minPrecio && l.getPrecio() <= maxPrecio)
                .forEach(System.out::println);
    }

    // b) Calcular el ingreso total generado por un libro especifico
    public static void calcularIngresoLibro(int codigoLibro) {
        ArrayList<Libro> libros = archLibro.leer();
        ArrayList<Prestamo> transacciones = archPrestamo.leer(); 
        
        Libro libro = libros.stream()
                .filter(l -> l.getCodLibro() == codigoLibro) 
                .findFirst().orElse(null);
        
        if (libro == null) {
            System.out.println("\nLibro no encontrado.");
            return;
        }
        
        double ingresoTotal = transacciones.stream()
                .filter(t -> t.getCodLibro() == codigoLibro)
                .mapToDouble(t -> t.getCantidad() * libro.getPrecio())
                .sum();
        
        System.out.println("\n=== b) Ingreso total por '" + libro.getTitulo() + "' (Cod: " + codigoLibro + ") ===");
        System.out.println("Ingreso total: Bs. " + String.format("%.2f", ingresoTotal));
    }

    // c) Mostrar la lista de libros que nunca fueron vendidos.
    public static void mostrarLibrosNoVendidos() {
        ArrayList<Libro> libros = archLibro.leer();
        ArrayList<Prestamo> transacciones = archPrestamo.leer();
        
        Set<Integer> librosTransaccionados = transacciones.stream()
                .map(Prestamo::getCodLibro)
                .collect(Collectors.toSet());
        
        System.out.println("\n=== c) Libros nunca vendidos/prestados ===");
        libros.stream()
                .filter(l -> !librosTransaccionados.contains(l.getCodLibro())) 
                .forEach(System.out::println);
    }

    // d) Mostrar a todos los clientes que compraron un libro especifico.
    public static void mostrarClientesPorLibro(int codigoLibro) {
        ArrayList<Cliente> clientes = archCliente.leer();
        ArrayList<Prestamo> transacciones = archPrestamo.leer();
        
        Set<String> cisClientes = transacciones.stream()
                .filter(t -> t.getCodLibro() == codigoLibro)
                .map(Prestamo::getCodCliente) 
                .collect(Collectors.toSet());
        
        System.out.println("\n=== d) Clientes que compraron/prestaron el libro (Cod: " + codigoLibro + ") ===");
        clientes.stream()
                .filter(c -> cisClientes.contains(c.getCi()))
                .forEach(System.out::println);
    }

    // e) Definir el libro más prestado.
    public static void libroMasPrestado() {
        ArrayList<Libro> libros = archLibro.leer();
        ArrayList<Prestamo> prestamos = archPrestamo.leer();
        
        Map<Integer, Long> conteo = prestamos.stream()
            .collect(Collectors.groupingBy(Prestamo::getCodLibro, Collectors.summingLong(Prestamo::getCantidad)));

        if (conteo.isEmpty()) {
            System.out.println("\n=== e) Libro más prestado ===");
            System.out.println("No hay préstamos registrados.");
            return;
        }
        
        Optional<Map.Entry<Integer, Long>> masPrestadoEntry = conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        
        if (masPrestadoEntry.isPresent()) {
            int codigoMasPrestado = masPrestadoEntry.get().getKey();
            Long cantidad = masPrestadoEntry.get().getValue();
            
            Libro libro = libros.stream()
                    .filter(l -> l.getCodLibro() == codigoMasPrestado)
                    .findFirst().orElse(null);
            
            System.out.println("\n=== e) Libro más prestado ===");
            if (libro != null) {
                System.out.println(libro.getTitulo() + " (Cod: " + codigoMasPrestado + ") con " + cantidad + " unidades prestadas.");
            }
        }
    }

    // f) Mostrar el cliente que tuvo más préstamos.
    public static void clienteConMasPrestamos() {
        ArrayList<Cliente> clientes = archCliente.leer();
        ArrayList<Prestamo> prestamos = archPrestamo.leer();
        
        Map<String, Long> conteo = prestamos.stream()
            .collect(Collectors.groupingBy(Prestamo::getCodCliente, Collectors.summingLong(Prestamo::getCantidad)));

        if (conteo.isEmpty()) {
            System.out.println("\n=== f) Cliente con más préstamos ===");
            System.out.println("No hay préstamos registrados.");
            return;
        }
        
        Optional<Map.Entry<String, Long>> masPrestamosEntry = conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        
        if (masPrestamosEntry.isPresent()) {
            String ciMasPrestamos = masPrestamosEntry.get().getKey();
            Long cantidad = masPrestamosEntry.get().getValue();
            
            Cliente cliente = clientes.stream()
                    .filter(c -> c.getCi().equals(ciMasPrestamos))
                    .findFirst().orElse(null);
            
            System.out.println("\n=== f) Cliente con más préstamos ===");
            if (cliente != null) {
                System.out.println(cliente.getNombre() + " " + cliente.getApellido() + " (CI: " + ciMasPrestamos + ") con " + cantidad + " unidades prestadas.");
            }
        }
    }

    public static void main(String[] args) {
        
        ArrayList<Libro> libros = new ArrayList<>();
        libros.add(new Libro(1, "Cien años de soledad", 85.50));
        libros.add(new Libro(2, "Don Quijote", 120.00));
        libros.add(new Libro(3, "El principito", 45.00));
        libros.add(new Libro(4, "1984", 95.00)); 
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(10, "12345678", "Juan", "Pérez"));
        clientes.add(new Cliente(20, "87654321", "María", "López"));
        clientes.add(new Cliente(30, "11223344", "Carlos", "García"));
        
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        prestamos.add(new Prestamo("12345678", 1, "2025-02-01", 1)); 
        prestamos.add(new Prestamo("87654321", 1, "2025-02-05", 2)); 
        prestamos.add(new Prestamo("12345678", 2, "2025-02-10", 1));
        prestamos.add(new Prestamo("12345678", 3, "2025-02-15", 3)); 
        prestamos.add(new Prestamo("11223344", 2, "2025-02-20", 1));
        
        archLibro.guardar(libros);
        archCliente.guardar(clientes);
        archPrestamo.guardar(prestamos);
        
        listarLibrosPorPrecio(50.0, 100.0);
        calcularIngresoLibro(1); 
        mostrarLibrosNoVendidos(); 
        mostrarClientesPorLibro(1); 
        libroMasPrestado(); 
        clienteConMasPrestamos();
    }
}