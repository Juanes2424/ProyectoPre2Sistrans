package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.RecepcionDeProducto;

//import uniandes.edu.co.proyecto.dto.DocumentoIngresoDTO;

import java.util.List;

@Repository
public interface RecepcionDeProductoRepository extends JpaRepository<RecepcionDeProducto, String> {

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO RECEPCIONDEPRODUCTO (id, fecha_recepcion, id_orden_compra, id_bodega) " +
                        "VALUES (sq5.nextval, CURRENT_DATE, :orden, :bodega)", nativeQuery = true)
        void crearDocumento(@Param("orden") Integer orden, @Param("bodega") Integer bodega);

        @Modifying
        @Transactional
        @Query(value = "UPDATE InfoExtraBodega ib " +
                        "SET ib.costo_promedio = ( " +
                        "  (ib.total_existencias * ib.costo_promedio + " +
                        "   (SELECT ioe.cantidad * ioe.costo_unitario_compra " +
                        "    FROM InfoExtraOrden ioe " +
                        "    WHERE ioe.codigo_barras_producto = ib.codigo_producto " +
                        "    AND ioe.id_orden = :orden) " +
                        "  ) / " +
                        "  (ib.total_existencias + " +
                        "   (SELECT ioe.cantidad " +
                        "    FROM InfoExtraOrden ioe " +
                        "    WHERE ioe.codigo_barras_producto = ib.codigo_producto " +
                        "    AND ioe.id_orden = :orden) " +
                        "  ) " +
                        "), " +
                        "ib.total_existencias = ib.total_existencias + " +
                        "  (SELECT ioe.cantidad " +
                        "   FROM InfoExtraOrden ioe " +
                        "   WHERE ioe.codigo_barras_producto = ib.codigo_producto " +
                        "   AND ioe.id_orden = :orden) " +
                        "WHERE ib.id_bodega = :bodega " +
                        "AND EXISTS ( " +
                        "   SELECT 1 " +
                        "   FROM InfoExtraOrden ioe " +
                        "   WHERE ioe.codigo_barras_producto = ib.codigo_producto " +
                        "   AND ioe.id_orden = :orden" +
                        ")", nativeQuery = true)
        void actualizarAvgCant(@Param("orden") Integer orden, @Param("bodega") Integer bodega);

        @Modifying
        @Transactional
        @Query(value = "UPDATE OrdenDeCompra O SET o.estado = 'entregada' WHERE id = :orden ", nativeQuery = true)
        void cambiarAEntregada(@Param("orden") Integer orden);

        @Transactional(readOnly = true)
        @Query(value = "SELECT P.nombre AS proveedor, B.nombre AS nombre_bodega, S.nombre AS nombre_sucursal, " +
               "R.id AS id_Documento, R.fecha_recepcion AS fecha " +
               "FROM RecepcionDeProducto R " +
               "INNER JOIN Bodega B ON B.id = R.id_bodega " +
               "INNER JOIN Sucursal S ON S.id = B.sucursal " +
               "INNER JOIN OrdenDeCompra O ON O.id = R.id_orden_compra " +
               "INNER JOIN Proveedor P ON P.nit = O.proveedor " +
               "WHERE S.id = :idSucursal AND B.id = :idBodega AND R.fecha_recepcion >= CURRENT_DATE - 30", nativeQuery = true)
        List<Object[]> findDocumentosIngresoUltimos30Dias(
                @Param("idSucursal") Long idSucursal,
                @Param("idBodega") Long idBodega
        );

}