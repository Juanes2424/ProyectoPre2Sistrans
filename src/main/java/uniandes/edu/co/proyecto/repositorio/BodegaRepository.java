package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Bodega;

import java.util.List;

public interface BodegaRepository extends JpaRepository<Bodega, Integer> {

        public interface InventarioProducto {
                String getProducto();

                int getCantidad_Actual();

                int getCantidad_Mínima();

                double getCosto_Promedio();
        }

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM Bodega WHERE id =:id", nativeQuery = true)
        void borrarBodega(@Param("id") String id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Bodega (id, nombre, tamano_metros2, sucursal) " +
                        "VALUES (sq3.nextval, :nombre, :tamano_metros2, :sucursal)", nativeQuery = true)
        void insertarBodega(@Param("nombre") String nombre,
                        @Param("tamano_metros2") Integer tamano_metros2, @Param("sucursal") String sucursal);

        @Query(value = "SELECT PR.* " +
                        "FROM Producto PR " +
                        "INNER JOIN InfoExtraBodega I ON PR.codigo_barras = I.codigo_producto " +
                        "INNER JOIN Bodega B ON I.id_bodega = B.id " +
                        "INNER JOIN Sucursal S ON B.sucursal = S.id " +
                        "WHERE S.id = :id_sucursal " +
                        "AND B.id = :id_bodega", nativeQuery = true)
        List<?> darInventarioPorBodega(@Param("id_sucursal") Integer id_sucursal,
                        @Param("id_bodega") Integer id_bodega);

        // SELECT PR.*
        // FROM Producto PR
        // INNER JOIN InfoExtraBodega I ON PR.codigo_barras = I.codigo_producto
        // INNER JOIN Bodega B ON I.id_bodega = B.id
        // INNER JOIN Sucursal S ON B.sucursal = S.id
        // WHERE S.id = 4
        // AND B.id = 6
}