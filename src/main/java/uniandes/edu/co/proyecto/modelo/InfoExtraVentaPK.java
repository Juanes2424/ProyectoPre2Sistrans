package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class InfoExtraVentaPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_venta", referencedColumnName = "id")
    private Venta id_venta;

    @ManyToOne
    @JoinColumn(name = "codigo_producto", referencedColumnName = "codigo_barras")
    private Producto codigo_producto;

    public InfoExtraVentaPK(Venta id_venta, Producto codigo_producto) {
        super();
        this.id_venta = id_venta;
        this.codigo_producto = codigo_producto;
    }

    public Venta getId_venta() {
        return id_venta;
    }

    public void setId_venta(Venta id_venta) {
        this.id_venta = id_venta;
    }

    public Producto getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(Producto codigo_producto) {
        this.codigo_producto = codigo_producto;
    }
}
