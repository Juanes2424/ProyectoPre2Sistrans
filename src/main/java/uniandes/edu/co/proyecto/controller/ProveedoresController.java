package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.repositorio.ProveedorRepository;

@RestController
public class ProveedoresController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @PostMapping("/proveedor/new/save")
    public ResponseEntity<String> proveedorGuardar(@RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.insertarProveedor(proveedor.getNit(), proveedor.getNombre(), proveedor.getDireccion(),
                    proveedor.getNombre_contacto(), proveedor.getTelefono_contacto().toString());
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/proveedor/{nit}/edit/save")
    public ResponseEntity<String> proveedorEditarGuardar(@PathVariable("nit") String nit,
            @RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.actualizarProveedor(nit, proveedor.getNombre(), proveedor.getDireccion(),
                    proveedor.getNombre_contacto(), proveedor.getTelefono_contacto().toString());
            return new ResponseEntity<>("Proveedor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
