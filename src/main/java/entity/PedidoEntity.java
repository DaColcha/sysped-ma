package entity;

import com.example.syspedv1.DBConnection;
import com.example.syspedv1.HistorialPedidos;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "pedido", schema = "sysped", catalog = "")
public class PedidoEntity {
    @Id
    @Column(name = "idPedido")
    private String idPedido;
    @Basic
    @Column(name = "estado")
    private String estado;
    @Basic
    @Column(name = "fechaPedido")
    private Date fechaPedido;
    @Basic
    @Column(name = "horaPedido")
    private Time horaPedido;
    @OneToMany(mappedBy = "pedidoByPedido")
    private Collection<DetallePedidosEntity> detallePedidosByIdPedido;
    @OneToMany(mappedBy = "pedidoByPedido")
    private Collection<FacturaEntity> facturasByIdPedido;

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Time getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Time horaPedido) {
        this.horaPedido = horaPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedidoEntity that = (PedidoEntity) o;

        if (idPedido != null ? !idPedido.equals(that.idPedido) : that.idPedido != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;
        if (fechaPedido != null ? !fechaPedido.equals(that.fechaPedido) : that.fechaPedido != null) return false;
        if (horaPedido != null ? !horaPedido.equals(that.horaPedido) : that.horaPedido != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPedido != null ? idPedido.hashCode() : 0;
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (fechaPedido != null ? fechaPedido.hashCode() : 0);
        result = 31 * result + (horaPedido != null ? horaPedido.hashCode() : 0);
        return result;
    }

    public Collection<DetallePedidosEntity> getDetallePedidosByIdPedido() {
        return detallePedidosByIdPedido;
    }

    public void setDetallePedidosByIdPedido(Collection<DetallePedidosEntity> detallePedidosByIdPedido) {
        this.detallePedidosByIdPedido = detallePedidosByIdPedido;
    }

    public Collection<FacturaEntity> getFacturasByIdPedido() {
        return facturasByIdPedido;
    }

    public void setFacturasByIdPedido(Collection<FacturaEntity> facturasByIdPedido) {
        this.facturasByIdPedido = facturasByIdPedido;
    }

    public java.util.Date obtenerFecha() {
        return new java.util.Date();
    }

    // Método para obtener la hora hasta minutos
    public Time obtenerHoraActual() {
        java.util.Date utilDate = new java.util.Date();
        return new Time(utilDate.getTime());
    }

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "idPedido='" + idPedido + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaPedido=" + fechaPedido +
                ", horaPedido=" + horaPedido +
                ", detallePedidosByIdPedido=" + detallePedidosByIdPedido +
                ", facturasByIdPedido=" + facturasByIdPedido +
                '}';
    }

    private String formarItems(ProductoEntity p, int cantidad){
        return "<tr>"
                + "<td>" + cantidad + "</td>"
                + "<td>" + p.getNombreProducto() + "</td>"
                + "<td>" + p.getPrecio() + "</td>"
                + "<td>" + p.getPrecio().multiply(BigDecimal.valueOf(cantidad)) + "</td>"
                + "</tr>";
    }
    public String generarCodigoTicket(){
        HistorialPedidos h = new HistorialPedidos();

        // Formatear el ultimo número como un código con ceros a la izquierda
        return String.format("%05d", Integer.parseInt(h.ultimoCodigo()) + 1);
    }

    public  String generarTicket(HttpServletRequest request){

        String cadena =  "<div class = \"ticket\"> "+
                "<div class=\"ticket-header\">Ticket de Compra</div>"+
                "<div >codigo ticket: "+generarCodigoTicket()+"</div>"+"<table>"
                + "<tr>"
                + "<th>Cantidad</th>"
                + "<th>Nombre</th>"
                + "<th>Precio Unitario</th>"
                + "<th>Total</th>"
                + "</tr>";

        TypedQuery<ProductoEntity> productos = DBConnection.entityManager.createNamedQuery("Productos.allResults", ProductoEntity.class);
        List<ProductoEntity> listaProductosSeleccionados = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();

        int i = 0;
        double total = 0;

        for(ProductoEntity p : productos.getResultList()){
            String aux = request.getParameter("item"+i);
            if(aux != null){
                int cantidad = 1;
                if (!request.getParameter("cantidad"+i).isBlank()) {
                    cantidad = Integer.parseInt(request.getParameter("cantidad" + i));
                }

                listaProductosSeleccionados.add(p);
                cantidades.add(cantidad);
                cadena += formarItems(p, cantidad);
                total += p.getPrecio().doubleValue() * cantidad;
            }
            i++;
        }
        cadena += "<tr>"
                + "<td colspan=\"3\"><strong>Total:</strong></td>"
                + "<td>$ " + BigDecimal.valueOf(total) + "</td>"
                + "</tr>";
        cadena += "</table> <div/>";

        HistorialPedidos h = new HistorialPedidos();
        h.agregarPedido(generarCodigoTicket(),cantidades, listaProductosSeleccionados);
        return cadena;
    }

}
