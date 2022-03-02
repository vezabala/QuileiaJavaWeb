package quileia.com.service;

import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quileia.com.Criteria.HorarioCriteria;
import quileia.com.domain.FranjaHoraria_;
import quileia.com.domain.Horario;
import quileia.com.domain.Horario_;
import quileia.com.repository.HorarioRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HorarioServiceQuery extends QueryService<Horario> {
    @Autowired
    HorarioRepository horarioRepository;

    public List<Horario> findByCriterial (HorarioCriteria horarioCriteria){
        final Specification<Horario> specification = createSpecification(horarioCriteria);
        List<Horario> medicos = horarioRepository.findAll(specification);
        return medicos;
    }
    private Specification<Horario> createSpecification (HorarioCriteria criteria){
        Specification<Horario> specification = Specification.where(null);
        if (criteria != null ){

            if(criteria.getFranjaHoraria() != null){
                specification =
                    specification.and(buildReferringEntitySpecification(criteria.getFranjaHoraria(), Horario_.franjaHoraria , FranjaHoraria_.franja));
            }
        }
        return specification;
    }
}
