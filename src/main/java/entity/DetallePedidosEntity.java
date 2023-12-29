package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedidos", schema = "dbo", catalog = "sysped")
@NamedQuery(name = "Pedido.ultimo", query = "SELECT p FROM  PedidoEntity p ORDER BY idPedido DESC LIMIT 1")
@NamedQuery(name = "DetallePedido.byIdDetalle", query = "SELECT dp FROM  DetallePedidosEntity dp WHERE dp.pedido = ?1")
@IdClass(DetallePedidosEntityPK.class)
public class DetallePedidosEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numDetalle")
    private int numDetalle;
    @Basic
    @Column(name = "especificaciones")
    private String especificaciones;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pedido")
    private String pedido;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "producto")
    private String producto;
    @Basic
    @Column(name = "cantidad")
    private int cantidad;
    @ManyToOne
    @JoinColumns(@JoinColumn(name = "pedido", referencedColumnName = "idPedido", nullable = false, insertable = false, updatable = false))
    private PedidoEntity pedidoByPedido;
    @ManyToOne
    @JoinColumns(@JoinColumn(name = "producto", referencedColumnName = "idProducto", nullable = false,insertable = false, updatable = false))
    private ProductoEntity productoByProducto;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNumDetalle() {
        return numDetalle;
    }

    public void setNumDetalle(int numDetalle) {
        this.numDetalle = numDetalle;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetallePedidosEntity that = (DetallePedidosEntity) o;

        if (numDetalle != that.numDetalle) return false;
        if (especificaciones != null ? !especificaciones.equals(that.especificaciones) : that.especificaciones != null)
            return false;
        if (pedido != null ? !pedido.equals(that.pedido) : that.pedido != null) return false;
        if (producto != null ? !producto.equals(that.producto) : that.producto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numDetalle;
        result = 31 * result + (especificaciones != null ? especificaciones.hashCode() : 0);
        result = 31 * result + (pedido != null ? pedido.hashCode() : 0);
        result = 31 * result + (producto != null ? producto.hashCode() : 0);
        return result;
    }

    public PedidoEntity getPedidoByPedido() {
        return pedidoByPedido;
    }

    public void setPedidoByPedido(PedidoEntity pedidoByPedido) {
        this.pedidoByPedido = pedidoByPedido;
    }

    public ProductoEntity getProductoByProducto() {
        return productoByProducto;
    }

    public void setProductoByProducto(ProductoEntity productoByProducto) {
        this.productoByProducto = productoByProducto;
    }

    @Override
    public String toString() {
        return "DetallePedidosEntity{" +
                "numDetalle=" + numDetalle +
                ", especificaciones='" + especificaciones + '\'' +
                ", pedido='" + pedido + '\'' +
                ", producto='" + producto + '\'' +
                ", pedidoByPedido=" + pedidoByPedido +
                ", productoByProducto=" + productoByProducto +
                '}';
    }

}
