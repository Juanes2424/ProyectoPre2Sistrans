package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ProveedorDeProductoPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "codigo_barras", referencedColumnName = "codigo_barras")
    private Producto codigo_barras;

    @ManyToOne
    @JoinColumn(name = "nit_proveedor", referencedColumnName = "nit")
    private Proveedor nit_proveedor;

    public ProveedorDeProductoPK(Producto codigo_barras, Proveedor nit_proveedor) {
        super();
        this.codigo_barras = codigo_barras;
        this.nit_proveedor = nit_proveedor;
    }

    public Producto getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(Producto codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public Proveedor getNit_proveedor() {
        return nit_proveedor;
    }

    public void setNit_proveedor(Proveedor nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }

}
