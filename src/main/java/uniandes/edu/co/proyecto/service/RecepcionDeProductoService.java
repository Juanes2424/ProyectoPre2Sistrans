package uniandes.edu.co.proyecto.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.repositorio.RecepcionDeProductoRepository;


import java.lang.Thread;
import java.util.List;

@Service
public class RecepcionDeProductoService {

    @Autowired
    private RecepcionDeProductoRepository recepcionDeProductoRepository;

    public RecepcionDeProductoService(RecepcionDeProductoRepository recepcionDeProductoRepository) {
        this.recepcionDeProductoRepository = recepcionDeProductoRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void rf10Serializable(int orden, int bodega) {
        try {
            Thread.sleep(31000);
            recepcionDeProductoRepository.crearDocumento(orden, bodega);
            recepcionDeProductoRepository.actualizarAvgCant(orden, bodega);
            recepcionDeProductoRepository.cambiarAEntregada(orden);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional()
    public void rf10ReadCommited(int orden, int bodega) {
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
           
            Thread.sleep(30000);
            List<Object[]> documentos = recepcionDeProductoRepository.findDocumentosIngresoUltimos30Dias(idSucursal, idBodega);
            return documentos ;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los documentos de ingreso. Inténtelo nuevamente más tarde.");
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public List<Object[]> obtenerDocumentosIngreso2(Long idSucursal, Long idBodega) throws InterruptedException {
        try {
  
            Thread.sleep(30000);
            
            return recepcionDeProductoRepository.findDocumentosIngresoUltimos30Dias(idSucursal, idBodega);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los documentos de ingreso. Inténtelo nuevamente más tarde.");
        }
    }
    
}
