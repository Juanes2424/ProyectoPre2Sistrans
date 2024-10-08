package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import oracle.sql.DATE;

@Entity
@Table(name = "RecepcionDeProducto")
public abstract class RecepcionDeProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private Date fecha_recepcion;

    @ManyToOne
    @JoinColumn(name = "id_orden_compra", referencedColumnName = "id")
    private OrdenDeCompra id_orden_compra;

    @ManyToOne
    @JoinColumn(name = "id_bodega", referencedColumnName = "id")
    private Bodega id_bodega;

    public RecepcionDeProducto(Date fecha_recepcion, OrdenDeCompra id_orden_compra, Bodega id_bodega) {
        this.fecha_recepcion = fecha_recepcion;
        this.id_orden_compra = id_orden_compra;
        this.id_bodega = id_bodega;
    }

    RecepcionDeProducto() {
        ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public OrdenDeCompra getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(OrdenDeCompra id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
    }

    public Bodega getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(Bodega id_bodega) {
        this.id_bodega = id_bodega;
    }
}
