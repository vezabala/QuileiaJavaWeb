import { IMedico } from 'app/shared/model/medico.model';
import { IPaciente } from 'app/shared/model/paciente.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

export interface ITipoDocumento {
  id?: number;
  iniciales?: string;
  nombreDocumento?: string;
  estadoTipoDocumento?: Estado;
  medicos?: IMedico[];
  pacientes?: IPaciente[];
}

export class TipoDocumento implements ITipoDocumento {
  constructor(
    public id?: number,
    public iniciales?: string,
    public nombreDocumento?: string,
    public estadoTipoDocumento?: Estado,
    public medicos?: IMedico[],
    public pacientes?: IPaciente[]
  ) {}
}
