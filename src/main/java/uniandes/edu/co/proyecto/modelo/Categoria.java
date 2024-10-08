package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categoria")
public class Categoria {

    private String nombre;
    @Id
    private String codigo;
    private String descripcion;
    private String caracteristicas_de_almacenamiento;

    public Categoria(String nombre, String codigo, String descripcion, String caracteristicas_de_almacenamiento) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.caracteristicas_de_almacenamiento = caracteristicas_de_almacenamiento;
    }

    Categoria() {
        ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas_de_almacenamiento() {
        return caracteristicas_de_almacenamiento;
    }

    public void setCaracteristicas_de_almacenamiento(String caracteristicas_de_almacenamiento) {
        this.caracteristicas_de_almacenamiento = caracteristicas_de_almacenamiento;
    }
}
