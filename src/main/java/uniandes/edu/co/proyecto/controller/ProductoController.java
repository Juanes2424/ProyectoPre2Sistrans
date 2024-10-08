package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositorio.CategoriaRepository;
import uniandes.edu.co.proyecto.repositorio.ProductoRepository;

@RestController
@RequestMapping("")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/producto/new/save")
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto) {
        try {

            categoriaRepository.findById(producto.getCategoria().getCodigo())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada"));

            productoRepository.insertarProducto(
                    producto.getCodigo_barras().toString(),
                    producto.getNombre(),
                    producto.getPrecio_unitario(),
                    producto.getPresentacion(),
                    producto.getCantidad_presentacion(),
                    producto.getUnidad_medida_presentacion(),
                    producto.getCantidad_empaque(),
                    producto.getUnidad_empaque(),
                    producto.getFecha_expiracion(),
                    String.valueOf(producto.getCategoria().getCodigo()));

            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/productos/{codigo_barras}/edit/save")
    public ResponseEntity<String> actualizarProducto(@PathVariable Integer codigo_barras,
            @RequestBody Producto producto) {
        try {
            productoRepository.actualizarProducto(
                    codigo_barras,
                    producto.getNombre(),
                    producto.getPrecio_unitario(),
                    producto.getPresentacion(),
                    producto.getCantidad_presentacion(),
                    producto.getUnidad_medida_presentacion(),
                    producto.getCantidad_empaque(),
                    producto.getUnidad_empaque(),
                    producto.getFecha_expiracion(),
                    producto.getCategoria().getCodigo());
            return new ResponseEntity<>("Producto actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productos/{codigo_barras}/delete")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer codigo_barras,
            @RequestBody Producto producto) {
        try {
            productoRepository.eliminarProducto(codigo_barras);
            return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
