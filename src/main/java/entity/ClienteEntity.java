package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "cliente", schema = "dbo", catalog = "sysped")
@NamedQuery(name = "Cliente.byIdCliente", query = "SELECT c FROM  ClienteEntity c WHERE c.cedula = ?1")
public class ClienteEntity {

    @Id
    @Column(name = "cedula")
    private String cedula;
    @Basic
    @Column(name = "nombres")
    private String nombres;
    @Basic
    @Column(name = "apellidos")
    private String apellidos;
    @Basic
    @Column(name = "correoElectronico")
    private String correoElectronico;
    @Basic
    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "clienteByCliente")
    private Collection<FacturaEntity> facturasByCedula;
    @Basic
    @Column(name = "direccion")
    private String direccion;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteEntity that = (ClienteEntity) o;

        if (cedula != null ? !cedula.equals(that.cedula) : that.cedula != null) return false;
        if (nombres != null ? !nombres.equals(that.nombres) : that.nombres != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (correoElectronico != null ? !correoElectronico.equals(that.correoElectronico) : that.correoElectronico != null)
            return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cedula != null ? cedula.hashCode() : 0;
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (correoElectronico != null ? correoElectronico.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }

    public Collection<FacturaEntity> getFacturasByCedula() {
        return facturasByCedula;
    }

    public void setFacturasByCedula(Collection<FacturaEntity> facturasByCedula) {
        this.facturasByCedula = facturasByCedula;
    }

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "cedula='" + cedula + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefono='" + telefono + '\'' +
                ", facturasByCedula=" + facturasByCedula +
                '}';
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
