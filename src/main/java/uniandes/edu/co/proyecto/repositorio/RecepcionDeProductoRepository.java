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

        @Transactional(readOnly = true)
        @Query(value = "SELECT  O.proveedor AS proveedor,  B.nombre AS nombre_bodega, S.nombre AS nombre_sucursal,"+
            "R.id AS id_Documento, R.fecha_recepcion AS fecha" +
            "FROM RecepcionDeProducto R " +
            "INNER JOIN Bodega B ON B.id = R.id_bodega " +
            "INNER JOIN Sucursal S ON S.id = B.sucursal " +
            "INNER JOIN OrdenDeCompra O ON O.id = R.id_orden_compra " +
            "WHERE S.id = :idSucursal AND b.id = :idBodega AND R.fecha_recepcion >= CURRENT_DATE - 30", nativeQuery = true)
        List<Object[]> findDocumentosIngresoUltimos30Dias(
                @Param("idSucursal") Long idSucursal,
                @Param("idBodega") Long idBodega
        );
}
