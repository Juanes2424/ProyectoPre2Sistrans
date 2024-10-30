package uniandes.edu.co.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.RecepcionDeProducto;
import uniandes.edu.co.proyecto.repositorio.RecepcionDeProductoRepository;

import java.util.List;

@Service
public class RecepcionDeProductoService {

    @Autowired
    private RecepcionDeProductoRepository recepcionDeProductoRepository;

    public RecepcionDeProductoService(RecepcionDeProductoRepository recepcionDeProductoRepository) {
        this.recepcionDeProductoRepository = recepcionDeProductoRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void rf10(int orden, int bodega) {
        try {
            recepcionDeProductoRepository.crearDocumento(orden, bodega);
            recepcionDeProductoRepository.actualizarAvgCant(orden, bodega);
            recepcionDeProductoRepository.cambiarAEntregada(orden);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<Object[]> obtenerDocumentosIngreso(Long idSucursal, Long idBodega) throws InterruptedException {
        try {
            // Temporizador de 30 segundos
            Thread.sleep(30000);
            // Obtiene la lista de documentos
            return recepcionDeProductoRepository.findDocumentosIngresoUltimos30Dias(idSucursal, idBodega);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los documentos de ingreso. Inténtelo nuevamente más tarde.");
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public List<Object[]> obtenerDocumentosIngreso2(Long idSucursal, Long idBodega) throws InterruptedException {
        try {
            // Temporizador de 30 segundos
            Thread.sleep(30000);
            // Obtiene la lista de documentos
            return recepcionDeProductoRepository.findDocumentosIngresoUltimos30Dias(idSucursal, idBodega);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los documentos de ingreso. Inténtelo nuevamente más tarde.");
        }
    }
    
    
     
}
