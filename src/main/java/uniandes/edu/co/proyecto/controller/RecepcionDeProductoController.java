package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.service.RecepcionDeProductoService;

import java.util.List;

@RestController
@RequestMapping("recepcionDeProducto")
public class RecepcionDeProductoController {

    @Autowired
    private RecepcionDeProductoService recepcionDeProductoService;

    @GetMapping("/documentos/rfc6")
    public ResponseEntity<List<Object[]>> obtenerDocumentosIngreso(
            @RequestParam Long idSucursal,
            @RequestParam Long idBodega) {
        try {
            List<Object[]> documentos = recepcionDeProductoService.obtenerDocumentosIngreso(idSucursal, idBodega);
            return ResponseEntity.ok(documentos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
