package EJ3;

public class Main {
    public static void main(String[] args) {
    	
        ArchivoProducto gestor = new ArchivoProducto("productos.json");
        gestor.crearArchivo();        
        System.out.println("b) GUARDAR PRODUCTOS");
        
        gestor.guardarProducto(new Producto(101, "Monitor 27\"", 1500.00f));
        gestor.guardarProducto(new Producto(102, "Teclado Mecánico", 450.00f));
        gestor.guardarProducto(new Producto(103, "Mouse Gamer", 320.00f));
        gestor.guardarProducto(new Producto(104, "Laptop Pro", 8000.00f));
        gestor.guardarProducto(new Producto(105, "Webcam HD", 250.00f));
        gestor.guardarProducto(new Producto(106, "Monitor 32\"", 1500.00f));
        gestor.guardarProducto(new Producto(107, "Memoria RAM", 850.00f));
        
        System.out.println("\n--- Lista completa de productos ---");
        gestor.mostrarTodos();

        System.out.println("\n==================================================");
        System.out.println("c) BUSCAR PRODUCTOS");
        
        Producto pBuscado = gestor.buscaProducto(102);
        if (pBuscado != null) {
            System.out.println("Resultado: " + pBuscado.toString());
        } else {
            System.out.println("Resultado: Producto no encontrado.");
        }
        
        pBuscado = gestor.buscaProducto(999);
        if (pBuscado != null) {
            System.out.println("Resultado: " + pBuscado.toString());
        } else {
            System.out.println("Resultado: Producto no encontrado.");
        }
        
        System.out.println("\n==================================================");
        System.out.println("d) CALCULAR PROMEDIO DE PRECIOS");
        gestor.calcularPromedioPrecios();
        
        System.out.println("\n==================================================");
        System.out.println("e) MOSTRAR PRODUCTO MÁS CARO");
        gestor.mostrarMasCaro();
    }
}