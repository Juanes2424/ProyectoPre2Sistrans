package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "InfoExtraOrden")
public class InfoExtraOrden {

    @EmbeddedId
    private InfoExtraOrdenPK pk;

    private Integer cantidad;
    private Integer costo_unitario_compra;

    public InfoExtraOrden(Producto codigo_barras_producto, OrdenDeCompra id_orden, Integer cantidad,
            Integer costo_unitario_compra) {
        this.pk = new InfoExtraOrdenPK(codigo_barras_producto, id_orden);
        this.cantidad = cantidad;
        this.costo_unitario_compra = costo_unitario_compra;
    }

    InfoExtraOrden() {
        ;
    }

    public InfoExtraOrdenPK getPk() {
        return pk;
    }

    public void setPk(InfoExtraOrdenPK pk) {
        this.pk = pk;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCosto_unitario_compra() {
        return costo_unitario_compra;
    }

    public void setCosto_unitario_compra(Integer costo_unitario_compra) {
        this.costo_unitario_compra = costo_unitario_compra;
    }

    public void setOrdenDeCompra(OrdenDeCompra orden) {
        this.pk.setId_orden(orden);
    }
}
