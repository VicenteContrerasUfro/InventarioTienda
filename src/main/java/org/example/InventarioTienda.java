package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventarioTienda{

    private static Object[][] productos = new Object[10][3];
    private static Scanner scanner = new Scanner(System.in);

    static {
        productos[0] = new Object[]{1, "Laptop", 10};
        productos[1] = new Object[]{2, "Smartphone", 20};
        productos[2] = new Object[]{3, "Tablet", 15};
    }

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú de Gestión de Inventario");
            System.out.println("1. Agregar Productos al Inventario");
            System.out.println("2. Restar Productos del Inventario");
            System.out.println("3. Consultar Disponibilidad de un Producto");
            System.out.println("4. Listar Todos los Productos");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        agregarProductos(1, "Laptop", 10);
                        break;
                    case 2:
                        restarProductos(1, 5);
                        break;
                    case 3:
                        consultarDisponibilidad(1);
                        break;
                    case 4:
                        listarProductos();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida. Por favor ingrese un número.");
                scanner.next();
            }
        }
    }

    public static void agregarProductos(int i, String laptop, int i1) {
        try {
            System.out.print("Ingrese el ID del producto: ");
            int idProducto = scanner.nextInt();

            int indice = buscarProducto(idProducto);
            if (indice != -1) {
                System.out.print("Ingrese la cantidad a agregar: ");
                int cantidad = scanner.nextInt();
                int cantidadActual = (int) productos[indice][2];
                productos[indice][2] = cantidadActual + cantidad;
                System.out.println("Cantidad actualizada exitosamente.");
            } else {
                System.out.print("Ingrese el nombre del producto: ");
                String nombreProducto = scanner.next();
                System.out.print("Ingrese la cantidad del producto: ");
                int cantidad = scanner.nextInt();

                int nuevoIndice = obtenerIndiceLibre();
                if (nuevoIndice != -1) {
                    productos[nuevoIndice] = new Object[]{idProducto, nombreProducto, cantidad};
                    System.out.println("Producto agregado exitosamente.");
                } else {
                    System.out.println("Error: No hay espacio en el inventario.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida.");
            scanner.next();
        }
    }

    public static void restarProductos(int i, int i1) {
        try {
            System.out.print("Ingrese el ID del producto: ");
            int idProducto = scanner.nextInt();
            System.out.print("Ingrese la cantidad a restar: ");
            int cantidad = scanner.nextInt();

            int indice = buscarProducto(idProducto);
            if (indice != -1) {
                int cantidadActual = (int) productos[indice][2];
                if (cantidadActual >= cantidad) {
                    productos[indice][2] = cantidadActual - cantidad;
                    System.out.println("Cantidad actualizada exitosamente.");
                } else {
                    System.out.println("Error: No hay suficiente cantidad para restar.");
                }
            } else {
                System.out.println("Error: Producto no encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida.");
            scanner.next();
        }
    }

    public static int consultarDisponibilidad(int i) {
        try {
            System.out.print("Ingrese el ID del producto: ");
            int idProducto = scanner.nextInt();

            int indice = buscarProducto(idProducto);
            if (indice != -1) {
                System.out.println("Producto: " + productos[indice][1] + " | Cantidad disponible: " + productos[indice][2]);
            } else {
                System.out.println("Error: Producto no encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida.");
            scanner.next();
        }
        return i;
    }

    public static short listarProductos() {
        System.out.println("Inventario de Productos:");
        for (Object[] producto : productos) {
            if (producto[0] != null) {
                System.out.println("ID: " + producto[0] + " | Nombre: " + producto[1] + " | Cantidad: " + producto[2]);
            }
        }
        return 0;
    }

    private static int buscarProducto(int idProducto) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] != null && (int) productos[i][0] == idProducto) {
                return i;
            }
        }
        return -1;
    }

    private static int obtenerIndiceLibre() {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] == null) {
                return i;
            }
        }
        return -1;
    }
}
