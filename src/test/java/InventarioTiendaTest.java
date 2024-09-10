import static org.example.InventarioTienda.listarProductos;
import static org.junit.jupiter.api.Assertions.*;
import org.example.InventarioTienda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventarioTiendaTest {

    private InventarioTienda inventario;

    @BeforeEach
    void setUp() {
        inventario = new InventarioTienda();
        inventario.agregarProductos(1, "Laptop", 10);
        inventario.agregarProductos(2, "Smartphone", 20);
        inventario.agregarProductos(3, "Tablet", 15);
    }

    @Test
    void testAgregarProductosExistente() {
        inventario.agregarProductos(1, "Laptop", 5);
        int cantidadActual = inventario.consultarDisponibilidad(1);
        assertEquals(15, cantidadActual, "La cantidad debería ser 15 después de agregar.");
    }

    @Test
    void testAgregarNuevoProducto() {
        inventario.agregarProductos(4, "Teclado", 30);
        int cantidadActual = inventario.consultarDisponibilidad(4);
        assertEquals(30, cantidadActual, "La cantidad del nuevo producto debería ser 30.");
    }

    @Test
    void testRestarProductosExistente() {
        inventario.restarProductos(1, 5);
        int cantidadActual = inventario.consultarDisponibilidad(1);
        assertEquals(5, cantidadActual, "La cantidad debería ser 5 después de restar.");
    }

    @Test
    void testRestarProductoSinSuficienteStock() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventario.restarProductos(1, 15);
        });
        String mensajeEsperado = "Error: No hay suficiente cantidad para restar.";
        assertTrue(exception.getMessage().contains(mensajeEsperado), "Debería lanzar una excepción de cantidad insuficiente.");
    }

    @Test
    void testConsultarDisponibilidadProductoExistente() {
        int cantidad = inventario.consultarDisponibilidad(2);
        assertEquals(20, cantidad, "La cantidad disponible debería ser 20.");
    }

    @Test
    void testConsultarDisponibilidadProductoNoExistente() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventario.consultarDisponibilidad(5);
        });
        String mensajeEsperado = "Error: Producto no encontrado.";
        assertTrue(exception.getMessage().contains(mensajeEsperado), "Debería lanzar una excepción de producto no encontrado.");
    }

    @Test
    void testListarProductos() {
        String resultadoEsperado = "ID: 1 | Nombre: Laptop | Cantidad: 10\n" +
                "ID: 2 | Nombre: Smartphone | Cantidad: 20\n" +
                "ID: 3 | Nombre: Tablet | Cantidad: 15\n";
        assertEquals(resultadoEsperado, listarProductos(), "Debería listar todos los productos correctamente.");
    }
}
