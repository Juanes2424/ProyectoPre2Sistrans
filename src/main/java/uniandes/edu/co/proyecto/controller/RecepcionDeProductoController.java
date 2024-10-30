package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.service.RecepcionDeProductoService;
import java.util.*;

@RestController
@RequestMapping("/recepcionDeProducto")
public class RecepcionDeProductoController {

    @Autowired
    private RecepcionDeProductoService recepcionDeProductoService;

    @GetMapping("/documentos/rfc6/{idSucursal}/{idBodega}")
    public ResponseEntity<Map<String, Object>> obtenerDocumentosIngreso(
        @PathVariable Long idSucursal,
        @PathVariable Long idBodega) {
    try {
        List<Object[]> resultados = recepcionDeProductoService.obtenerDocumentosIngreso(idSucursal, idBodega);

        // Variables para almacenar los nombres de la bodega y sucursal
        String nombreBodega = null;
        String nombreSucursal = null;

        // Lista de documentos
        List<Map<String, Object>> documentos = new ArrayList<>();

        for (Object[] resultado : resultados) {
            // Extrae cada campo del resultado
            String proveedor = (String) resultado[0];
            nombreBodega = (String) resultado[1];
            nombreSucursal = (String) resultado[2];
            String idDocumento = ((String) resultado[3]).toString();
            String fechaRecepcion = resultado[4].toString();

            // Crea un mapa para el documento y agrégalo a la lista de documentos
            Map<String, Object> documento = new HashMap<>();
            documento.put("idDocumento", idDocumento);
            documento.put("fechaRecepcion", fechaRecepcion);
            documento.put("proveedor", proveedor);
            documentos.add(documento);
        }

        // Crear el mapa de respuesta principal
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("documentos", documentos);
        respuesta.put("nombreBodega", nombreBodega);
        respuesta.put("nombreSucursal", nombreSucursal);

        return ResponseEntity.ok(respuesta);

    } catch (RuntimeException e) {
       
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("El tiempo fue excedido", e.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    } catch (Exception e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("La consulta no puede ser realizada. Un rollback será ejecutado.", "Ocurrió un error inesperado. Inténtelo nuevamente.");
        return ResponseEntity.status(500).body(errorResponse);
    }
}

}
