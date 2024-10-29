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

    /* 
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<RecepcionDeProducto> obtenerDocumentosIngreso(Long idSucursal, Long idBodega) throws InterruptedException {
        // Espera de 30 segundos
        Thread.sleep(30000);
        // Obtiene la lista de documentos
        return recepcionDeProductoRepository.findDocumentosIngresoUltimos30Dias(idSucursal, idBodega);
    }*/
}
