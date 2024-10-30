package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.InfoExtraBodega;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;

public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Long> {

        @Query(value = "SELECT * FROM ORDENDECOMPRA", nativeQuery = true)
        List<OrdenDeCompra> findAllOrdenes();

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO OrdenDeCompra (id, estado, fecha_creacion, fecha_entrega, sucursal, proveedor) " +
                        "VALUES(sq5.nextval, :estado, :fecha_creacion, :fecha_entrega, :sucursal_id, :proveedor_id)", nativeQuery = true)
        void insertarOrdenDeCompra(@Param("estado") String estado, @Param("fecha_creacion") Date fecha_creacion,
                        @Param("fecha_entrega") Date fecha_entrega, @Param("sucursal_id") Long sucursalId,
                        @Param("proveedor_id") Long proveedorId);

        @Modifying
        @Transactional
        @Query(value = "UPDATE OrdenDeCompra O SET o.estado = 'anulada' WHERE id = :id AND o.estado != 'entregada'", nativeQuery = true)
        void cambiarAnulado(@Param("id") Integer id);

        // @Modifying
        // @Transactional
        // @Query(value = "INSERT INTO RECEPCIONDEPRODUCTO (id, fecha_recepcion,
        // id_orden_compra, id_bodega) " +
        // "VALUES (sq5.nextval, CURRENT_DATE, :orden, :bodega);" +
        // " " +
        // "UPDATE InfoExtraBodega ib " +
        // "SET " +
        // "costo_promedio = ( " +
        // "(ib.total_existencias * ib.costo_promedio + ioe.cantidad *
        // ioe.costo_unitario_compra) / " +
        // "(ib.total_existencias + ioe.cantidad) " +
        // "), " +
        // "total_existencias = ib.total_existencias + ioe.cantidad " +
        // "FROM InfoExtraBodega " +
        // "ib JOIN " +
        // "InfoExtraOrden ioe " +
        // "ON ib.codigo_producto = ioe.codigo_barras_producto " +
        // "WHERE ioe.id_orden= :orden AND ib.id_bodega= :bodega; " +
        // " " +
        // "UPDATE OrdenDeCompra O SET o.estado = 'entregada' WHERE id = :orden " + "IF
        // @@ERROR <> 0 "
        // + "BEGIN " + "ROLLBACK; " + "PRINT 'Transaction rolled back due to an
        // error.'; " + "END "
        // + "ELSE " + "BEGIN " + "COMMIT; " + "PRINT 'Transaction committed
        // successfully.'; "
        // + "END ", nativeQuery = true)

        // void rf10(@Param("orden") Integer orden, @Param("bodega") Integer bodega);

}