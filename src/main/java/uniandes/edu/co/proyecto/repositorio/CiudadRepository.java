package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Ciudad (nombre, codigo) VALUES(:nombre, superandes.nextval)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);

}