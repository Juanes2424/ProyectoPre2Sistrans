package uniandes.edu.co.proyecto.controller;

import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositorio.SucursalRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/sucursal/consulta/codigo/{codigo_barras}")
    public ResponseEntity<List<Sucursal>> sucursalesConProductoCodigo(@PathVariable Integer codigo_barras) {
        try {
            List<Sucursal> res = sucursalRepository.darSucursalesConProducto(codigo_barras);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/sucursal/consulta/nombre/{nombre}")
    public ResponseEntity<List<Sucursal>> sucursalesConProductoNombre(@PathVariable String nombre) {
        try {
            List<Sucursal> res = sucursalRepository.darSucursalesConProductoNombre(nombre);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/sucursal/rfc1/indiceOcupacion/sucursal/{sucursalId}")
    public ResponseEntity<List<Map<String, Object>>> obtenerIndiceOcupacionPorBodega(
            @PathVariable Long sucursalId,
            @RequestBody Map<String, Object> request) {
        try {
            @SuppressWarnings("unchecked")
            List<String> productos = (List<String>) request.get("productos");
            List<Object[]> resultados = sucursalRepository.obtenerIndiceOcupacionPorBodega(sucursalId, productos);
            List<Map<String, Object>> respuesta = new ArrayList<>();

            for (Object[] fila : resultados) {
                Map<String, Object> item = new HashMap<>();
                item.put("bodegaId", fila[0]);
                item.put("indiceOcupacion", fila[1]);
                respuesta.add(item);
            }

            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
