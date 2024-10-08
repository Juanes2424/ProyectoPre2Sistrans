package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class InfoExtraOrdenPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "codigo_barras_producto", referencedColumnName = "codigo_barras")
    private Producto codigo_barras_producto;

    @ManyToOne
    @JoinColumn(name = "id_orden", referencedColumnName = "id")
    private OrdenDeCompra id_orden;

    public InfoExtraOrdenPK(Producto codigo_barras_producto, OrdenDeCompra id_orden) {
        this.codigo_barras_producto = codigo_barras_producto;
        this.id_orden = id_orden;
    }

    InfoExtraOrdenPK() {
        ;
    }

    // Getters y setters
    public Producto getCodigo_barras_producto() {
        return codigo_barras_producto;
    }

    public void setCodigo_barras_producto(Producto codigo_barras_producto) {
        this.codigo_barras_producto = codigo_barras_producto;
    }

    public OrdenDeCompra getId_orden() {
        return id_orden;
    }

    public void setId_orden(OrdenDeCompra id_orden) {
        this.id_orden = id_orden;
    }
}
