package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "factura", schema = "sysped", catalog = "")
public class FacturaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numFactura")
    private String numFactura;
    @Basic
    @Column(name = "cliente")
    private String cliente;
    @Basic
    @Column(name = "pedido")
    private String pedido;
    @Basic
    @Column(name = "metodoPago")
    private String metodoPago;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "cedula", nullable = false, insertable = false, updatable = false)
    private ClienteEntity clienteByCliente;
    @ManyToOne
    @JoinColumn(name = "pedido", referencedColumnName = "idPedido", nullable = false,insertable = false, updatable = false)
    private PedidoEntity pedidoByPedido;

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacturaEntity that = (FacturaEntity) o;

        if (numFactura != null ? !numFactura.equals(that.numFactura) : that.numFactura != null) return false;
        if (cliente != null ? !cliente.equals(that.cliente) : that.cliente != null) return false;
        if (pedido != null ? !pedido.equals(that.pedido) : that.pedido != null) return false;
        if (metodoPago != null ? !metodoPago.equals(that.metodoPago) : that.metodoPago != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numFactura != null ? numFactura.hashCode() : 0;
        result = 31 * result + (cliente != null ? cliente.hashCode() : 0);
        result = 31 * result + (pedido != null ? pedido.hashCode() : 0);
        result = 31 * result + (metodoPago != null ? metodoPago.hashCode() : 0);
        return result;
    }

    public ClienteEntity getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntity clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public PedidoEntity getPedidoByPedido() {
        return pedidoByPedido;
    }

    public void setPedidoByPedido(PedidoEntity pedidoByPedido) {
        this.pedidoByPedido = pedidoByPedido;
    }

    public String generarFactura() {
        String salida = "<div><label for = \"cedulaCliente\">Cedula: </label>"
                + "<input type=\"text\" name=\"cedulaCliente\" id=\"cedulaCliente\"> </div>"

                + "<div><label for = \"nombreCliente\">Nombre: </label>"
                + "<input type=\"text\" name=\"nombreCliente\" id=\"nombreCliente\"> </div>"

                + "<div><label for = \"apellidoCliente\">Apellido: </label>"
                + "<input type=\"text\" name=\"apellidoCliente\" id=\"apellidoCliente\"> </div>"

                + "<div><label for = \"emailCliente\">Correo Eléctronico: </label>"
                + "<input type=\"text\" name=\"emailCliente\" id=\"emailCliente> </div>"

                + "<div><label for = \"telefonoCliente\">Teléfono: </label>"
                + "<input type=\"text\" name=\"telefonoCliente\" id=\"telefonoCliente\"> </div>"

                + "<div><label for = \"codigoTicket\">Código del ticket del pedido: </label>"
                + "<input type=\"text\" name=\"codigoTicket\" id=\"codigoTicket\"> </div>"

                + "<div><label for = \"metodoPago\">Selecciona la forma de pago: </label>"
                + "<select name=\"metodoPago\" id=\"metodoPago\">"
                + "<option value=\"efectivo\">Efectivo</option>"
                + "<option value=\"tarjeta\">Tarjeta</option> </select> </div>";

        return salida;
    }

    @Override
    public String toString() {
        return "FacturaEntity{" +
                "numFactura='" + numFactura + '\'' +
                ", cliente='" + cliente + '\'' +
                ", pedido='" + pedido + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                ", clienteByCliente=" + clienteByCliente +
                ", pedidoByPedido=" + pedidoByPedido +
                '}';
    }
}
