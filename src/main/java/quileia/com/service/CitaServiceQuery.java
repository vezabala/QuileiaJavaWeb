package quileia.com.service;

import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quileia.com.Criteria.CitaCriteria;
import quileia.com.domain.Cita;
import quileia.com.domain.Cita_;
import quileia.com.domain.Medico;
import quileia.com.domain.Medico_;
import quileia.com.repository.CitaRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CitaServiceQuery extends QueryService<Cita> {
    @Autowired
    CitaRepository citaRepository;

    public List<Cita> findByCriterialCita (CitaCriteria citaCriteria){
        final Specification<Cita> specification = createSpecificationCita(citaCriteria);
        List<Cita> citas = citaRepository.findAll(specification);
        return citas;
    }
    private Specification<Cita> createSpecificationCita (CitaCriteria citaCriteria){
        Specification<Cita> specification = Specification.where(null);
        if (citaCriteria != null ){
            if(citaCriteria.getMedico() != null){
                specification =
                    specification.and(buildReferringEntitySpecification(citaCriteria.getMedico(), Cita_.medicos , Medico_.nombreCompleto));
            }
        }
        return specification;
    }
}
