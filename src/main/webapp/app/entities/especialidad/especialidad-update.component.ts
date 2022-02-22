import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEspecialidad, Especialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from './especialidad.service';

@Component({
  selector: 'jhi-especialidad-update',
  templateUrl: './especialidad-update.component.html'
})
export class EspecialidadUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombreEspecialidad: [null, [Validators.required, Validators.maxLength(255)]],
    estadoTipoDocumento: [null, [Validators.required]]
  });

  constructor(protected especialidadService: EspecialidadService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ especialidad }) => {
      this.updateForm(especialidad);
    });
  }

  updateForm(especialidad: IEspecialidad): void {
    this.editForm.patchValue({
      id: especialidad.id,
      nombreEspecialidad: especialidad.nombreEspecialidad,
      estadoTipoDocumento: especialidad.estadoTipoDocumento
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const especialidad = this.createFromForm();
    if (especialidad.id !== undefined) {
      this.subscribeToSaveResponse(this.especialidadService.update(especialidad));
    } else {
      this.subscribeToSaveResponse(this.especialidadService.create(especialidad));
    }
  }

  private createFromForm(): IEspecialidad {
    return {
      ...new Especialidad(),
      id: this.editForm.get(['id'])!.value,
      nombreEspecialidad: this.editForm.get(['nombreEspecialidad'])!.value,
      estadoTipoDocumento: this.editForm.get(['estadoTipoDocumento'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEspecialidad>>): void {
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
}
