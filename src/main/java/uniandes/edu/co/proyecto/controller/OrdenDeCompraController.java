package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import uniandes.edu.co.proyecto.modelo.InfoExtraOrden;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.repositorio.InfoExtraOrdenRepository;
import uniandes.edu.co.proyecto.repositorio.OrdenDeCompraRepository;
import uniandes.edu.co.proyecto.repositorio.ProveedorRepository;
import uniandes.edu.co.proyecto.repositorio.SucursalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private InfoExtraOrdenRepository infoExtraOrdenRepository;

    @PostMapping("ordendecompra/new/save")
    public ResponseEntity<String> crearOrdenDeCompra(@RequestBody OrdenDeCompra ordenDeCompra) {
        try {

            sucursalRepository.findById(ordenDeCompra.getSucursal().getId())

                    .orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada"));
            proveedorRepository.findById(ordenDeCompra.getProveedor().getNit())
                    .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

            ordenDeCompraRepository.insertarOrdenDeCompra("vigente",
                    ordenDeCompra.getFecha_creacion(), ordenDeCompra.getFecha_entrega(),
                    Long.valueOf(ordenDeCompra.getSucursal().getId()),
                    Long.valueOf(ordenDeCompra.getProveedor().getNit()));

            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("ordendecompra/details/save")
    public ResponseEntity<String> detailsOrdenDeCompra(@RequestBody List<InfoExtraOrden> infoExtra) {
        try {

            for (InfoExtraOrden item : infoExtra) {
                infoExtraOrdenRepository.insertarInfoExtraOrden(item.getPk().getCodigo_barras_producto().toString(),
                        Integer.parseInt(item.getPk().getId_orden().toString()), item.getCantidad(),
                        item.getCosto_unitario_compra());
            }

            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("ordendecompra/{id}/anulada")
    public ResponseEntity<String> anularOrdenDeCompra(@PathVariable Integer id) {
        try {
            ordenDeCompraRepository.cambiarAnulado(id);
            return new ResponseEntity<>("Orden de compra anulada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al anular la orden de compra: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ordendecompra/obtener")
    public ResponseEntity<Collection<OrdenDeCompra>> obtenerOrdenes() {
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>ALOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            Collection<OrdenDeCompra> ordenes = ordenDeCompraRepository.findAllOrdenes();
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
