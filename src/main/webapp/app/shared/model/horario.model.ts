import { ICita } from 'app/shared/model/cita.model';

export interface IHorario {
  id?: number;
  hora?: string;
  horas?: ICita[];
  franjaHorariaFranja?: string;
  franjaHorariaId?: number;
}

export class Horario implements IHorario {
  constructor(
    public id?: number,
    public hora?: string,
    public horas?: ICita[],
    public franjaHorariaFranja?: string,
    public franjaHorariaId?: number
  ) {}
}
