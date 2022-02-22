import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFranjaHoraria, FranjaHoraria } from 'app/shared/model/franja-horaria.model';
import { FranjaHorariaService } from './franja-horaria.service';

@Component({
  selector: 'jhi-franja-horaria-update',
  templateUrl: './franja-horaria-update.component.html'
})
export class FranjaHorariaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    franja: [null, [Validators.required, Validators.maxLength(30)]],
    estadoFranjaHoraria: [null, [Validators.required]]
  });

  constructor(protected franjaHorariaService: FranjaHorariaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ franjaHoraria }) => {
      this.updateForm(franjaHoraria);
    });
  }

  updateForm(franjaHoraria: IFranjaHoraria): void {
    this.editForm.patchValue({
      id: franjaHoraria.id,
      franja: franjaHoraria.franja,
      estadoFranjaHoraria: franjaHoraria.estadoFranjaHoraria
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const franjaHoraria = this.createFromForm();
    if (franjaHoraria.id !== undefined) {
      this.subscribeToSaveResponse(this.franjaHorariaService.update(franjaHoraria));
    } else {
      this.subscribeToSaveResponse(this.franjaHorariaService.create(franjaHoraria));
    }
  }

  private createFromForm(): IFranjaHoraria {
    return {
      ...new FranjaHoraria(),
      id: this.editForm.get(['id'])!.value,
      franja: this.editForm.get(['franja'])!.value,
      estadoFranjaHoraria: this.editForm.get(['estadoFranjaHoraria'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFranjaHoraria>>): void {
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
