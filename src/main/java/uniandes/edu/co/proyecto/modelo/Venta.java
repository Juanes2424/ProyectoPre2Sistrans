package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Venta")
public abstract class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date fecha_venta;

    @ManyToOne
    @JoinColumn(name = "cedula_cliente", referencedColumnName = "cedula")
    private Cliente cedula_cliente;

    @ManyToOne
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id")
    private Sucursal id_sucursal;

    public Venta(Date fecha_venta, Cliente cedula_cliente, Sucursal id_sucursal) {
        this.fecha_venta = fecha_venta;
        this.cedula_cliente = cedula_cliente;
        this.id_sucursal = id_sucursal;
    }

    Venta() {
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Cliente getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(Cliente cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public Sucursal getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Sucursal id_sucursal) {
        this.id_sucursal = id_sucursal;
    }
}
