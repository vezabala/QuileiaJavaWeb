export interface ICita {
  id?: number;
  especialidadNombreEspecialidad?: string;
  especialidadId?: number;
  franjaHorariaFranja?: string;
  franjaHorariaId?: number;
  horarioHora?: string;
  horarioId?: number;
  medicosNombreCompleto?: string;
  medicosId?: number;
  pacientesNombreCompleto?: string;
  pacientesId?: number;
}

export class Cita implements ICita {
  constructor(
    public id?: number,
    public especialidadNombreEspecialidad?: string,
    public especialidadId?: number,
    public franjaHorariaFranja?: string,
    public franjaHorariaId?: number,
    public horarioHora?: string,
    public horarioId?: number,
    public medicosNombreCompleto?: string,
    public medicosId?: number,
    public pacientesNombreCompleto?: string,
    public pacientesId?: number
  ) {}
}
