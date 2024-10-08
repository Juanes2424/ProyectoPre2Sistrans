package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, String> {

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Proveedor (nit,nombre,direccion, nombre_contacto, telefono_contacto ) VALUES (:nit, :nombre, :direccion, :nombre_contacto, :telefono_contacto)", nativeQuery = true)
        void insertarProveedor(@Param("nit") String nit, @Param("nombre") String nombre,
                        @Param("direccion") String direccion, @Param("nombre_contacto") String nombre_contacto,
                        @Param("telefono_contacto") String telefono_contacto);

        @Modifying
        @Transactional
        @Query(value = "UPDATE Proveedor SET nit=:nit, nombre=:nombre, direccion=:direccion, nombre_contacto=:nombre_contacto, telefono_contacto=:telefono_contacto WHERE nit=:nit", nativeQuery = true)
        void actualizarProveedor(@Param("nit") String nit, @Param("nombre") String nombre,
                        @Param("direccion") String direccion, @Param("nombre_contacto") String nombre_contacto,
                        @Param("telefono_contacto") String telefono_contacto);

}