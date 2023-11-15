package entity;

import com.example.syspedv1.DBConnection;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

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
    private String ultimoCodigo(){
        try  {
            TypedQuery<PedidoEntity> pedidoMax = DBConnection.entityManager.createNamedQuery("Pedido.ultimo", PedidoEntity.class);
            for(PedidoEntity p : pedidoMax.getResultList()){
                return p.getIdPedido();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "00000";
    }
    public String SiguienteTicket(){
        int ultimoNumero = Integer.parseInt(ultimoCodigo());

        // Incrementar el número
        int siguienteNumero = ultimoNumero + 1;

        // Formatear el siguiente número como un código con ceros a la izquierda
        return String.format("%05d", siguienteNumero);
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
}
