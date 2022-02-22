import { IHorario } from 'app/shared/model/horario.model';
import { IMedico } from 'app/shared/model/medico.model';
import { ICita } from 'app/shared/model/cita.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

export interface IFranjaHoraria {
  id?: number;
  franja?: string;
  estadoFranjaHoraria?: Estado;
  horarios?: IHorario[];
  medicos?: IMedico[];
  citas?: ICita[];
}

export class FranjaHoraria implements IFranjaHoraria {
  constructor(
    public id?: number,
    public franja?: string,
    public estadoFranjaHoraria?: Estado,
    public horarios?: IHorario[],
    public medicos?: IMedico[],
    public citas?: ICita[]
  ) {}
}
