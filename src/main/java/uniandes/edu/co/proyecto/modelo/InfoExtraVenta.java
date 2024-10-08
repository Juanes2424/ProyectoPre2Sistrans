package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "InfoExtraVenta")
public class InfoExtraVenta {

    @EmbeddedId
    private InfoExtraVentaPK pk;

    private Integer cantidad;

    public InfoExtraVenta(Venta id_venta, Producto codigo_producto, Integer cantidad) {
        this.pk = new InfoExtraVentaPK(id_venta, codigo_producto);
        this.cantidad = cantidad;
    }

    InfoExtraVenta() {
        ;
    }

    public InfoExtraVentaPK getPk() {
        return pk;
    }

    public void setPk(InfoExtraVentaPK pk) {
        this.pk = pk;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
