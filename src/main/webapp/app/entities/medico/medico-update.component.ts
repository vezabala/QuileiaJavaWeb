import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedico, Medico } from 'app/shared/model/medico.model';
import { MedicoService } from './medico.service';
import { ITipoDocumento } from 'app/shared/model/tipo-documento.model';
import { TipoDocumentoService } from 'app/entities/tipo-documento/tipo-documento.service';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from 'app/entities/especialidad/especialidad.service';
import { IFranjaHoraria } from 'app/shared/model/franja-horaria.model';
import { FranjaHorariaService } from 'app/entities/franja-horaria/franja-horaria.service';

type SelectableEntity = ITipoDocumento | IEspecialidad | IFranjaHoraria;

@Component({
  selector: 'jhi-medico-update',
  templateUrl: './medico-update.component.html'
})
export class MedicoUpdateComponent implements OnInit {
  isSaving = false;
  tipodocumentos: ITipoDocumento[] = [];
  especialidads: IEspecialidad[] = [];
  franjahorarias: IFranjaHoraria[] = [];

  editForm = this.fb.group({
    id: [],
    nombreCompleto: [null, [Validators.required, Validators.maxLength(255)]],
    identificacion: [null, [Validators.required, Validators.maxLength(100)]],
    tarjetaProfesional: [null, [Validators.required, Validators.maxLength(100)]],
    anosExperiencia: [null, [Validators.required]],
    tipoDocumentoId: [null, Validators.required],
    especialidadId: [null, Validators.required],
    franjaHorariaId: [null, Validators.required]
  });

  constructor(
    protected medicoService: MedicoService,
    protected tipoDocumentoService: TipoDocumentoService,
    protected especialidadService: EspecialidadService,
    protected franjaHorariaService: FranjaHorariaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medico }) => {
      this.updateForm(medico);

      this.tipoDocumentoService.query().subscribe((res: HttpResponse<ITipoDocumento[]>) => (this.tipodocumentos = res.body || []));

      this.especialidadService.query().subscribe((res: HttpResponse<IEspecialidad[]>) => (this.especialidads = res.body || []));

      this.franjaHorariaService.query().subscribe((res: HttpResponse<IFranjaHoraria[]>) => (this.franjahorarias = res.body || []));
    });
  }

  updateForm(medico: IMedico): void {
    this.editForm.patchValue({
      id: medico.id,
      nombreCompleto: medico.nombreCompleto,
      identificacion: medico.identificacion,
      tarjetaProfesional: medico.tarjetaProfesional,
      anosExperiencia: medico.anosExperiencia,
      tipoDocumentoId: medico.tipoDocumentoId,
      especialidadId: medico.especialidadId,
      franjaHorariaId: medico.franjaHorariaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medico = this.createFromForm();
    if (medico.id !== undefined) {
      this.subscribeToSaveResponse(this.medicoService.update(medico));
    } else {
      this.subscribeToSaveResponse(this.medicoService.create(medico));
    }
  }

  private createFromForm(): IMedico {
    return {
      ...new Medico(),
      id: this.editForm.get(['id'])!.value,
      nombreCompleto: this.editForm.get(['nombreCompleto'])!.value,
      identificacion: this.editForm.get(['identificacion'])!.value,
      tarjetaProfesional: this.editForm.get(['tarjetaProfesional'])!.value,
      anosExperiencia: this.editForm.get(['anosExperiencia'])!.value,
      tipoDocumentoId: this.editForm.get(['tipoDocumentoId'])!.value,
      especialidadId: this.editForm.get(['especialidadId'])!.value,
      franjaHorariaId: this.editForm.get(['franjaHorariaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedico>>): void {
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
