package entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "producto", schema = "sysped", catalog = "")
public class ProductoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idProducto")
    private String idProducto;
    @Basic
    @Column(name = "nombreProducto")
    private String nombreProducto;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @Basic
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic
    @Column(name = "imagen")
    private byte[] imagen;
    @OneToMany(mappedBy = "productoByProducto")
    private Collection<DetallePedidosEntity> detallePedidosByIdProducto;

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductoEntity that = (ProductoEntity) o;

        if (idProducto != null ? !idProducto.equals(that.idProducto) : that.idProducto != null) return false;
        if (nombreProducto != null ? !nombreProducto.equals(that.nombreProducto) : that.nombreProducto != null)
            return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;
        if (!Arrays.equals(imagen, that.imagen)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProducto != null ? idProducto.hashCode() : 0;
        result = 31 * result + (nombreProducto != null ? nombreProducto.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(imagen);
        return result;
    }

    public Collection<DetallePedidosEntity> getDetallePedidosByIdProducto() {
        return detallePedidosByIdProducto;
    }

    public void setDetallePedidosByIdProducto(Collection<DetallePedidosEntity> detallePedidosByIdProducto) {
        this.detallePedidosByIdProducto = detallePedidosByIdProducto;
    }

    @Override
    public String toString() {
        return "ProductoEntity{" +
                "idProducto='" + idProducto + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", imagen=" + Arrays.toString(imagen) +
                ", detallePedidosByIdProducto=" + detallePedidosByIdProducto +
                '}';
    }
}
