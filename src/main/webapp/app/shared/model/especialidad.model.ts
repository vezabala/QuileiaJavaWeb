import { IMedico } from 'app/shared/model/medico.model';
import { ICita } from 'app/shared/model/cita.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

export interface IEspecialidad {
  id?: number;
  nombreEspecialidad?: string;
  estadoTipoDocumento?: Estado;
  medicos?: IMedico[];
  citas?: ICita[];
}

export class Especialidad implements IEspecialidad {
  constructor(
    public id?: number,
    public nombreEspecialidad?: string,
    public estadoTipoDocumento?: Estado,
    public medicos?: IMedico[],
    public citas?: ICita[]
  ) {}
}
