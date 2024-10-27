package uniandes.edu.co.proyecto.controller;

import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositorio.BodegaRepository;
import uniandes.edu.co.proyecto.repositorio.BodegaRepository.InventarioProducto;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    @PostMapping("/bodega/new")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega) {

        try {
            bodegaRepository.insertarBodega(bodega.getNombre(), bodega.getTamano_metros2(),
                    bodega.getSucursal().getId().toString());
            return new ResponseEntity<String>("Bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al crear bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bodega/{id}/delete")
    public ResponseEntity<String> borrarSucursal(@PathVariable("id") String id) {
        try {
            bodegaRepository.borrarBodega(id);
            return new ResponseEntity<String>("Exito borrar bodega", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al borrar bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bodega/rfc3/sucursal/{sucursal}/bodega/{bodega}")
    public ResponseEntity<List<?>> darInventarioBodega(@PathVariable Integer sucursal,
            @PathVariable Integer bodega) {
        try {
            List<?> inventario = bodegaRepository.darInventarioPorBodega(sucursal, bodega);

            return ResponseEntity.ok(inventario);
        } catch (Exception e) {
            System.out.println("AHJAO");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
