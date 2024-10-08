package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {

        @Query(value = "SELECT * FROM Categorias", nativeQuery = true)
        Collection<Categoria> dCategoria();

        @Query(value = "SELECT * FROM Categorias WHERE id = :id", nativeQuery = true)
        Categoria obtenerCategoria(@Param("id") int id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO categoria (codigo, nombre, descripcion, caracteristicas_de_almacenamiento) VALUES(:codigo, :nombre, :descripcion, :caracteristicas_de_almacenamiento)", nativeQuery = true)
        void insertarCategoria(@Param("codigo") String codigo, @Param("nombre") String nombre,
                        @Param("descripcion") String descripcion,
                        @Param("caracteristicas_de_almacenamiento") String caracteristicasDeAlmacenamiento);

        @Modifying
        @Transactional
        @Query(value = "UPDATE Categorias (codigo, nombre, descripcion, caracteristicas_de_almacenamiento) VALUES(:codigo, :nombre, :descripcion, :caracteristicas_de_almacenamiento) WHERE id=:id", nativeQuery = true)
        void actualizaRCategoria(@Param("id") int id, @Param("codigo") String codigo, @Param("nombre") String nombre,
                        @Param("descripcion") String descripcion,
                        @Param("caracteristicas_de_almacenamiento") String caracteristicasDeAlmacenamiento);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM Categorias WHERE id = :id", nativeQuery = true)
        void eliminarCategoria(@Param("id") int id);

}
