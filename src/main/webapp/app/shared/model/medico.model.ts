import { ICita } from 'app/shared/model/cita.model';

export interface IMedico {
  id?: number;
  nombreCompleto?: string;
  identificacion?: string;
  tarjetaProfesional?: string;
  anosExperiencia?: boolean;
  citas?: ICita[];
  tipoDocumentoNombreDocumento?: string;
  tipoDocumentoId?: number;
  especialidadNombreEspecialidad?: string;
  especialidadId?: number;
  franjaHorariaFranja?: string;
  franjaHorariaId?: number;
}

export class Medico implements IMedico {
  constructor(
    public id?: number,
    public nombreCompleto?: string,
    public identificacion?: string,
    public tarjetaProfesional?: string,
    public anosExperiencia?: boolean,
    public citas?: ICita[],
    public tipoDocumentoNombreDocumento?: string,
    public tipoDocumentoId?: number,
    public especialidadNombreEspecialidad?: string,
    public especialidadId?: number,
    public franjaHorariaFranja?: string,
    public franjaHorariaId?: number
  ) {
    this.anosExperiencia = this.anosExperiencia || false;
  }
}
