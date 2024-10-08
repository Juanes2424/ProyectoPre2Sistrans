package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private Integer tamano_metros;
    private String direccion;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "codigo_ciudad", referencedColumnName = "codigo")
    private Ciudad codigo_ciudad;

    public Sucursal(String nombre, Integer tamano_metros, String direccion, String telefono) {
        this.nombre = nombre;
        this.tamano_metros = tamano_metros;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    Sucursal() {
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTamano_metros() {
        return tamano_metros;
    }

    public void setTamano_metros(Integer tamano_metros) {
        this.tamano_metros = tamano_metros;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Ciudad getCodigo_ciudad() {
        return codigo_ciudad;
    }

    public void setCodigo_ciudad(Ciudad codigo_ciudad) {
        this.codigo_ciudad = codigo_ciudad;
    }

}