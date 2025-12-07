package EJ10;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArchivoJugadores archivo = new ArchivoJugadores();
        Scanner scanner = new Scanner(System.in);
        int opcion;
       
        System.out.println("📢 Sistema de Gestión de Jugadores Iniciado.");
        
        do {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║        MENÚ DE GESTIÓN DE JUGADORES    ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Agregar nuevo jugador               ║");
            System.out.println("║ 2. Mostrar todos los jugadores         ║"); 
            System.out.println("║ 3. Buscar jugador por nombre           ║"); 
            System.out.println("║ 4. Actualizar puntaje de jugador       ║");
            System.out.println("║ 5. Mostrar el jugador con más puntaje  ║");
            System.out.println("║ 0. Salir                               ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            } catch (java.util.InputMismatchException e) {
                System.out.println(" Opción inválida. Por favor, ingrese un número.");
                scanner.nextLine();
                opcion = -1; 
                continue;
            }
            
            switch (opcion) {
                case 1:
                    System.out.print("   Nombre del jugador: ");
                    String nombre = scanner.nextLine();
                    System.out.print("   Nivel: ");
                    int nivel = scanner.nextInt();
                    System.out.print("   Puntaje inicial: ");
                    int puntaje = scanner.nextInt();
                    archivo.agregarJugador(new Jugador(nombre, nivel, puntaje));
                    break;
                    
                case 2:
                    archivo.mostrarTodos();
                    break;
                    
                case 3:
                    System.out.print("   Nombre a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    archivo.buscarPorNombre(nombreBuscar);
                    break;
                    
                case 4:
                    System.out.print("   Nombre del jugador a actualizar: ");
                    String nombreActualizar = scanner.nextLine();
                    System.out.print("   Nuevo puntaje: ");
                    int nuevoPuntaje = scanner.nextInt();
                    archivo.actualizarPuntaje(nombreActualizar, nuevoPuntaje);
                    break;
                    
                case 5:
                    archivo.mostrarMejorJugador();
                    break;
                    
                case 0:
                    System.out.println("\n¡Gracias por usar el sistema!");
                    break;
                    
                default:
                    System.out.println("Opción no reconocida.");
            }
        } while (opcion != 0);
        
        scanner.close();
    }
}