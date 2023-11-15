package entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class DetallePedidosEntityPK implements Serializable {
    @Column(name = "numDetalle")
    @Id
    private int numDetalle;
    @Column(name = "pedido")
    @Id
    private String pedido;
    @Column(name = "producto")
    @Id
    private String producto;

    public int getNumDetalle() {
        return numDetalle;
    }

    public void setNumDetalle(int numDetalle) {
        this.numDetalle = numDetalle;
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

        DetallePedidosEntityPK that = (DetallePedidosEntityPK) o;

        if (numDetalle != that.numDetalle) return false;
        if (pedido != null ? !pedido.equals(that.pedido) : that.pedido != null) return false;
        if (producto != null ? !producto.equals(that.producto) : that.producto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numDetalle;
        result = 31 * result + (pedido != null ? pedido.hashCode() : 0);
        result = 31 * result + (producto != null ? producto.hashCode() : 0);
        return result;
    }
}
