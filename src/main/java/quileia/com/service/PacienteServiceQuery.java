package quileia.com.service;

import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quileia.com.Criteria.PacienteCriteria;
import quileia.com.domain.Paciente;
import quileia.com.domain.Paciente_;
import quileia.com.repository.PacienteRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PacienteServiceQuery extends QueryService<Paciente> {
    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> findByCriterialPaciente (PacienteCriteria pacienteCriteria){
        final Specification<Paciente> specification = createSpecification(pacienteCriteria);
        List<Paciente> pacientes = pacienteRepository.findAll(specification);
        return pacientes;
    }
    private Specification<Paciente> createSpecification (PacienteCriteria pacienteCriteria){
        Specification<Paciente> specification = Specification.where(null);
        if (pacienteCriteria != null ){
            if(pacienteCriteria.getIdentificacion() != null){
                specification =
                    specification.and(buildStringSpecification(pacienteCriteria.getIdentificacion() , Paciente_.identificacion));
            }
            if(pacienteCriteria.getNombreCompleto() != null){
                specification =
                    specification.and(buildStringSpecification(pacienteCriteria.getNombreCompleto() , Paciente_.nombreCompleto));
            }
        }
        return specification;
    }
    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }
}
