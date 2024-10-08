package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Sucursal (id, nombre, tamano_metros, direccion, telefono, codigo_ciudad) " +
                        "VALUES (sq2.nextval, :nombre, :tamano_metros, :direccion, :telefono, :codigo_ciudad)", nativeQuery = true)
        void insertarSucursal(@Param("nombre") String nombre,
                        @Param("tamano_metros") int tamano_metros, @Param("direccion") String direccion,
                        @Param("telefono") String telefono,
                        @Param("codigo_ciudad") int codigo_ciudad);

        /*
         * @Query(value = "SELECT DISTINCT S.* " +
         * "FROM Sucursal S " +
         * "INNER JOIN Bodega B ON S.id = B.sucursal " +
         * "INNER JOIN InfoExtraBodega I ON B.id = I.id_bodega " +
         * "INNER JOIN Producto P ON I.codigo_producto = P.codigo_barras " +
         * "WHERE (P.nombre = :producto OR P.codigo_barras = :codigo_barras) AND I.total_existencias > 0"
         * , nativeQuery = true)
         * Collection<Sucursal> darSucursalesConProducto(@Param("producto") String
         * producto, @Param("productoId") Integer codigo_barras);
         */
}