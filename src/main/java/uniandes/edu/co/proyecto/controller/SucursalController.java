package uniandes.edu.co.proyecto.controller;

import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositorio.SucursalRepository;

import java.util.List;

import org.hibernate.mapping.Collection;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @PostMapping("/sucursal/new")
    public ResponseEntity<String> crearSucursal(@RequestBody Sucursal sucursal) {

        try {
            sucursalRepository.insertarSucursal(sucursal.getNombre(), sucursal.getTamano_metros(),
                    sucursal.getDireccion(), sucursal.getTelefono().toString(),
                    sucursal.getCodigo_ciudad().getCodigo());
            return new ResponseEntity<String>("Sucursal creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al crear sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sucursal/consulta/codigo/{codigo_barras}")
    public ResponseEntity<?> sucursalesConProductoCodigo(@PathVariable Integer codigo_barras) {
        try {
            return ResponseEntity.ok(sucursalRepository.darSucursalesConProducto(codigo_barras));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // @GetMapping("/sucursal/consulta/codigo/{codigo_barras}")
    // public ResponseEntity<?> sucursalesConProductoNombre(@PathVariable String
    // producto,
    // @PathVariable Integer codigo_barras) {

    // try {
    // return
    // ResponseEntity.ok(sucursalRepository.darSucursalesConProducto(producto,
    // codigo_barras));
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    // }
    // }
}