package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "OrdenDeCompra")
public class OrdenDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String estado;

    @Column(name = "fecha_creacion", updatable = false) 
    private Date fecha_creacion;

    @Column(name = "fecha_entrega")
    private Date fecha_entrega;

    @ManyToOne
    @JoinColumn(name = "sucursal", referencedColumnName = "id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "nit")
    private Proveedor proveedor;

    @OneToMany(mappedBy = "pk.id_orden", cascade = CascadeType.ALL)
    private Set<InfoExtraOrden> detalles;

    public OrdenDeCompra() {
    }

    public OrdenDeCompra(String estado, Date fecha_entrega, Sucursal sucursal, Proveedor proveedor) {
        this.estado = estado;
        this.fecha_entrega = fecha_entrega;
        this.sucursal = sucursal;
        this.proveedor = proveedor;
    }

    @PrePersist
    protected void onCreate() {
        this.fecha_creacion = new Date(System.currentTimeMillis()); 
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Set<InfoExtraOrden> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<InfoExtraOrden> detalles) {
        this.detalles = detalles;
    }
}
