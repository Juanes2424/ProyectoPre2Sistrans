package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.RecepcionDeProducto;

//import uniandes.edu.co.proyecto.dto.DocumentoIngresoDTO;

import java.util.List;

@Repository
public interface RecepcionDeProductoRepository extends JpaRepository<RecepcionDeProducto, String> {

       /* @Transactional(readOnly = true)
        @Query("SELECT new uniandes.edu.co.proyecto.dto.DocumentoIngresoDTO(s.nombre, b.nombre, r.id, r.fecha_recepcion, p.nombre) " +
            "FROM RecepcionDeProducto r " +
            "JOIN r.id_bodega b " +
            "JOIN b.sucursal s " +
            "JOIN r.id_orden_compra o " +
            "JOIN o.proveedor p " +
            "WHERE s.id = :idSucursal AND b.id = :idBodega AND r.fecha_recepcion >= CURRENT_DATE - 30")
        List<DocumentoIngresoDTO> findDocumentosIngresoUltimos30Dias(
                @Param("idSucursal") Long idSucursal,
                @Param("idBodega") Long idBodega
        );*/ 
}
