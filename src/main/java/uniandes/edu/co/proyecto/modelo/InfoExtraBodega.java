package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "InfoExtraBodega")
public abstract class InfoExtraBodega {

    @EmbeddedId
    private InfoExtraBodegaPK pk;

    private Integer nivel_minimo_reorden;
    private Integer capacidad_almacenamiento;
    private Integer costo_bodega;
    private Integer total_existencias;
    private Integer costo_promedio;

    public InfoExtraBodega(Bodega id_bodega, Producto codigo_producto, Integer nivel_minimo_reorden,
            Integer capacidad_almacenamiento,
            Integer costo_bodega, Integer total_existencias, Integer costo_promedio) {
        this.pk = new InfoExtraBodegaPK(id_bodega, codigo_producto);
        this.nivel_minimo_reorden = nivel_minimo_reorden;
        this.capacidad_almacenamiento = capacidad_almacenamiento;
        this.costo_bodega = costo_bodega;
        this.total_existencias = total_existencias;
        this.costo_promedio = costo_promedio;
    }

    InfoExtraBodega() {
        ;
    }

    public InfoExtraBodegaPK getPk() {
        return pk;
    }

    public void setPk(InfoExtraBodegaPK pk) {
        this.pk = pk;
    }

    public Integer getNivel_minimo_reorden() {
        return nivel_minimo_reorden;
    }

    public void setNivel_minimo_reorden(Integer nivel_minimo_reorden) {
        this.nivel_minimo_reorden = nivel_minimo_reorden;
    }

    public Integer getCapacidad_almacenamiento() {
        return capacidad_almacenamiento;
    }

    public void setCapacidad_almacenamiento(Integer capacidad_almacenamiento) {
        this.capacidad_almacenamiento = capacidad_almacenamiento;
    }

    public Integer getCosto_bodega() {
        return costo_bodega;
    }

    public void setCosto_bodega(Integer costo_bodega) {
        this.costo_bodega = costo_bodega;
    }

    public Integer getTotal_existencias() {
        return total_existencias;
    }

    public void setTotal_existencias(Integer total_existencias) {
        this.total_existencias = total_existencias;
    }

    public Integer getCosto_promedio() {
        return costo_promedio;
    }

    public void setCosto_promedio(Integer costo_promedio) {
        this.costo_promedio = costo_promedio;
    }

}
