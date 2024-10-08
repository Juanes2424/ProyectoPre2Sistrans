package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.InfoExtraOrden;
import uniandes.edu.co.proyecto.modelo.InfoExtraOrdenPK;

public interface InfoExtraOrdenRepository extends JpaRepository<InfoExtraOrden, InfoExtraOrdenPK> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO InfoExtraOrden (codigo_barras_producto, id_orden, cantidad, costo_unitario_compra) " +
                   "VALUES (:codigoBarras, :idOrden, :cantidad, :costoUnitarioCompra)", nativeQuery = true)
    void insertarInfoExtraOrden(@Param("codigoBarras") String codigoBarras,
                                 @Param("idOrden") Integer idOrden,
                                 @Param("cantidad") Integer cantidad,
                                 @Param("costoUnitarioCompra") Integer costoUnitarioCompra);
}
