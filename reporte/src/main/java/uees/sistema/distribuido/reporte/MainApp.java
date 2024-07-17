/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sistema.distribuido.reporte;

import java.util.Scanner;
import uees.sistema.distribuido.infra.RestApiClient;

/**
 *
 * @author ninoska
 */
public class MainApp {
    private static RestApiClient client = new RestApiClient();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Mostrar el menú
            System.out.println("Menú:");
            System.out.println("1. Obtener todos los formularios");
            System.out.println("2. Obtener un formulario por ID");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opción que desea escoger: ");

            // Leer la opción del usuario
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    obtenerTodosLosFormularios();
                    break;
                case 2:
                    System.out.print("Ingrese el ID del formulario: ");
                    String id = scanner.nextLine();
                    obtenerFormularioPorId(id);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

            System.out.println();
        }

        scanner.close();
    }
    
    private static void obtenerTodosLosFormularios() throws Exception {
        System.out.println("Obteniendo todos los formularios...");
        var forms=client.getForms();
        System.out.println("");
        System.out.println(forms.toString());
    }

    private static void obtenerFormularioPorId(String id) throws Exception {
        System.out.println("Obteniendo formulario con ID: " + id);
        var form=client.getFormById(id);
        System.out.println("");
        System.out.println(form.toString());
    }
}
