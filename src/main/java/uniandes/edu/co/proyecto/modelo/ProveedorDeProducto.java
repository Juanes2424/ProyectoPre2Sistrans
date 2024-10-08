package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProveedorDeProducto")
public abstract class ProveedorDeProducto {

    @EmbeddedId
    private ProveedorDeProductoPK pk;

    public ProveedorDeProducto(Producto codigo_barras, Proveedor nit_proveedor) {
        this.pk = new ProveedorDeProductoPK(codigo_barras, nit_proveedor);
    }

    public ProveedorDeProductoPK getPk() {
        return pk;
    }

    public void setPk(ProveedorDeProductoPK pk) {
        this.pk = pk;
    }
}
