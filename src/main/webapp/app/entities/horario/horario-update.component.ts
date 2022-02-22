import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IHorario, Horario } from 'app/shared/model/horario.model';
import { HorarioService } from './horario.service';
import { IFranjaHoraria } from 'app/shared/model/franja-horaria.model';
import { FranjaHorariaService } from 'app/entities/franja-horaria/franja-horaria.service';

@Component({
  selector: 'jhi-horario-update',
  templateUrl: './horario-update.component.html'
})
export class HorarioUpdateComponent implements OnInit {
  isSaving = false;
  franjahorarias: IFranjaHoraria[] = [];

  editForm = this.fb.group({
    id: [],
    hora: [null, [Validators.required, Validators.maxLength(20)]],
    franjaHorariaId: [null, Validators.required]
  });

  constructor(
    protected horarioService: HorarioService,
    protected franjaHorariaService: FranjaHorariaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ horario }) => {
      this.updateForm(horario);

      this.franjaHorariaService.query().subscribe((res: HttpResponse<IFranjaHoraria[]>) => (this.franjahorarias = res.body || []));
    });
  }

  updateForm(horario: IHorario): void {
    this.editForm.patchValue({
      id: horario.id,
      hora: horario.hora,
      franjaHorariaId: horario.franjaHorariaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const horario = this.createFromForm();
    if (horario.id !== undefined) {
      this.subscribeToSaveResponse(this.horarioService.update(horario));
    } else {
      this.subscribeToSaveResponse(this.horarioService.create(horario));
    }
  }

  private createFromForm(): IHorario {
    return {
      ...new Horario(),
      id: this.editForm.get(['id'])!.value,
      hora: this.editForm.get(['hora'])!.value,
      franjaHorariaId: this.editForm.get(['franjaHorariaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHorario>>): void {
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

  trackById(index: number, item: IFranjaHoraria): any {
    return item.id;
  }
}
