package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bodega;
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

        @Query(value = "SELECT Sucursal.* " +
                        "FROM Sucursal " +
                        "INNER JOIN Bodega B ON Sucursal.id = B.sucursal " +
                        "INNER JOIN InfoExtraBodega I ON B.id = I.id_bodega " +
                        "INNER JOIN Producto P ON I.codigo_producto = P.codigo_barras " +
                        "WHERE I.total_existencias > 0 AND P.codigo_barras =:codigo_barras ", nativeQuery = true)
        List<Sucursal> darSucursalesConProducto(@Param("codigo_barras") Integer codigo_barras);

        // SELECT Sucursal.*
        // FROM Sucursal
        // INNER JOIN Bodega B ON Sucursal.id = B.sucursal
        // INNER JOIN InfoExtraBodega I ON B.id = I.id_bodega
        // INNER JOIN Producto P ON I.codigo_producto = P.codigo_barras
        // WHERE I.total_existencias > 0 AND P.codigo_barras = 123
}