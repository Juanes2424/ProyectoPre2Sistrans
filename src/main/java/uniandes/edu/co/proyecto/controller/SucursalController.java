package uniandes.edu.co.proyecto.controller;

import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositorio.SucursalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    // @GetMapping("/sucursal/consulta")
    // public ResponseEntity<?> sucursalesConProducto(@RequestParam String producto,
    // @RequestParam Integer codigo_barras) {

    // try {
    // Collection<Sucursal> sucursales =
    // sucursalRepository.darSucursalesConProducto(producto, codigo_barras);
    // return ResponseEntity.ok(sucursales);
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    // }
}
