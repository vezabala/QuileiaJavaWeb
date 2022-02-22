import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICita, Cita } from 'app/shared/model/cita.model';
import { CitaService } from './cita.service';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from 'app/entities/especialidad/especialidad.service';
import { IFranjaHoraria } from 'app/shared/model/franja-horaria.model';
import { FranjaHorariaService } from 'app/entities/franja-horaria/franja-horaria.service';
import { IHorario } from 'app/shared/model/horario.model';
import { HorarioService } from 'app/entities/horario/horario.service';
import { IMedico } from 'app/shared/model/medico.model';
import { MedicoService } from 'app/entities/medico/medico.service';
import { IPaciente } from 'app/shared/model/paciente.model';
import { PacienteService } from 'app/entities/paciente/paciente.service';

type SelectableEntity = IEspecialidad | IFranjaHoraria | IHorario | IMedico | IPaciente;

@Component({
  selector: 'jhi-cita-update',
  templateUrl: './cita-update.component.html'
})
export class CitaUpdateComponent implements OnInit {
  isSaving = false;
  especialidads: IEspecialidad[] = [];
  franjahorarias: IFranjaHoraria[] = [];
  horarios: IHorario[] = [];
  medicos: IMedico[] = [];
  pacientes: IPaciente[] = [];
  fechaDp: any;

  editForm = this.fb.group({
    id: [],
    fecha: [null, [Validators.required]],
    especialidadId: [null, Validators.required],
    franjaHorariaId: [null, Validators.required],
    horarioId: [null, Validators.required],
    medicosId: [null, Validators.required],
    pacientesId: [null, Validators.required]
  });

  constructor(
    protected citaService: CitaService,
    protected especialidadService: EspecialidadService,
    protected franjaHorariaService: FranjaHorariaService,
    protected horarioService: HorarioService,
    protected medicoService: MedicoService,
    protected pacienteService: PacienteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cita }) => {
      this.updateForm(cita);

      this.especialidadService.query().subscribe((res: HttpResponse<IEspecialidad[]>) => (this.especialidads = res.body || []));

      this.franjaHorariaService.query().subscribe((res: HttpResponse<IFranjaHoraria[]>) => (this.franjahorarias = res.body || []));

      this.horarioService.query().subscribe((res: HttpResponse<IHorario[]>) => (this.horarios = res.body || []));

      this.medicoService.query().subscribe((res: HttpResponse<IMedico[]>) => (this.medicos = res.body || []));

      this.pacienteService.query().subscribe((res: HttpResponse<IPaciente[]>) => (this.pacientes = res.body || []));
    });
  }

  updateForm(cita: ICita): void {
    this.editForm.patchValue({
      id: cita.id,
      fecha: cita.fecha,
      especialidadId: cita.especialidadId,
      franjaHorariaId: cita.franjaHorariaId,
      horarioId: cita.horarioId,
      medicosId: cita.medicosId,
      pacientesId: cita.pacientesId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cita = this.createFromForm();
    if (cita.id !== undefined) {
      this.subscribeToSaveResponse(this.citaService.update(cita));
    } else {
      this.subscribeToSaveResponse(this.citaService.create(cita));
    }
  }

  private createFromForm(): ICita {
    return {
      ...new Cita(),
      id: this.editForm.get(['id'])!.value,
      fecha: this.editForm.get(['fecha'])!.value,
      especialidadId: this.editForm.get(['especialidadId'])!.value,
      franjaHorariaId: this.editForm.get(['franjaHorariaId'])!.value,
      horarioId: this.editForm.get(['horarioId'])!.value,
      medicosId: this.editForm.get(['medicosId'])!.value,
      pacientesId: this.editForm.get(['pacientesId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICita>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
