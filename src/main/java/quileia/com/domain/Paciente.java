package quileia.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Paciente.
 */
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorPaciente")
    @SequenceGenerator(name = "sequenceGeneratorPaciente")
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "nombre_completo", length = 250, nullable = false)
    private String nombreCompleto;

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotNull
    @Size(max = 100)
    @Column(name = "identificacion", length = 100, nullable = false)
    private String identificacion;

    @NotNull
    @Size(max = 250)
    @Column(name = "eps", length = 250, nullable = false)
    private String eps;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "historia_clinica")
    private String historiaClinica;

    @OneToMany(mappedBy = "pacientes")
    @JsonIgnore
    private Set<Cita> citas = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("pacientes")
    private TipoDocumento tipoDocumento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Paciente nombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        return this;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Paciente fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public Paciente identificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEps() {
        return eps;
    }

    public Paciente eps(String eps) {
        this.eps = eps;
        return this;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getHistoriaClinica() {
        return historiaClinica;
    }

    public Paciente historiaClinica(String historiaClinica) {
        this.historiaClinica = historiaClinica;
        return this;
    }

    public void setHistoriaClinica(String historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public Paciente citas(Set<Cita> citas) {
        this.citas = citas;
        return this;
    }

    public Paciente addCitas(Cita cita) {
        this.citas.add(cita);
        cita.setPacientes(this);
        return this;
    }

    public Paciente removeCitas(Cita cita) {
        this.citas.remove(cita);
        cita.setPacientes(null);
        return this;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public Paciente tipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paciente)) {
            return false;
        }
        return id != null && id.equals(((Paciente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Paciente{" +
            "id=" + getId() +
            ", nombreCompleto='" + getNombreCompleto() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", identificacion='" + getIdentificacion() + "'" +
            ", eps='" + getEps() + "'" +
            ", historiaClinica='" + getHistoriaClinica() + "'" +
            "}";
    }
}
