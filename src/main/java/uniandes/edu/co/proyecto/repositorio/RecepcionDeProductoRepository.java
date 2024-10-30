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

        /*
         * @Transactional(readOnly = true)
         * 
         * @Query("SELECT new uniandes.edu.co.proyecto.dto.DocumentoIngresoDTO(s.nombre, b.nombre, r.id, r.fecha_recepcion, p.nombre) "
         * +
         * "FROM RecepcionDeProducto r " +
         * "JOIN r.id_bodega b " +
         * "JOIN b.sucursal s " +
         * "JOIN r.id_orden_compra o " +
         * "JOIN o.proveedor p " +
         * "WHERE s.id = :idSucursal AND b.id = :idBodega AND r.fecha_recepcion >= CURRENT_DATE - 30"
         * )
         * List<DocumentoIngresoDTO> findDocumentosIngresoUltimos30Dias(
         * 
         * @Param("idSucursal") Long idSucursal,
         * 
         * @Param("idBodega") Long idBodega
         * );
         */
}
