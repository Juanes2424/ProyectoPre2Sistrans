package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class InfoExtraBodegaPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_bodega", referencedColumnName = "id")
    private Bodega id_bodega;

    @ManyToOne
    @JoinColumn(name = "codigo_producto", referencedColumnName = "codigo_barras")
    private Producto codigo_producto;

    public InfoExtraBodegaPK(Bodega id_bodega, Producto codigo_producto) {
        super();
        this.id_bodega = id_bodega;
        this.codigo_producto = codigo_producto;
    }

    public Bodega getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(Bodega id_bodega) {
        this.id_bodega = id_bodega;
    }

    public Producto getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(Producto codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

}
