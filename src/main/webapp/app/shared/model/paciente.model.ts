import { Moment } from 'moment';
import { ICita } from 'app/shared/model/cita.model';

export interface IPaciente {
  id?: number;
  nombreCompleto?: string;
  fechaNacimiento?: Moment;
  identificacion?: string;
  eps?: string;
  historiaClinica?: any;
  citas?: ICita[];
  tipoDocumentoNombreDocumento?: string;
  tipoDocumentoId?: number;
}

export class Paciente implements IPaciente {
  constructor(
    public id?: number,
    public nombreCompleto?: string,
    public fechaNacimiento?: Moment,
    public identificacion?: string,
    public eps?: string,
    public historiaClinica?: any,
    public citas?: ICita[],
    public tipoDocumentoNombreDocumento?: string,
    public tipoDocumentoId?: number
  ) {}
}
