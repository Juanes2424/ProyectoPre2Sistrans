package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.List;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;

public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Integer> {

        @Query(value = "SELECT * FROM OrdenDeCompra", nativeQuery = true)
        Collection<OrdenDeCompra> darOrdenes();

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

        List<OrdenDeCompra> findAll();
}