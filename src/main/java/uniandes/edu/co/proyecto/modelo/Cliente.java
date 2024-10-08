package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cliente")
public abstract class Cliente {

    @Id
    private Integer cedula;
    private String nombre;

    public Cliente(Integer cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public Cliente() {
        ;
    }

    public Integer getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
