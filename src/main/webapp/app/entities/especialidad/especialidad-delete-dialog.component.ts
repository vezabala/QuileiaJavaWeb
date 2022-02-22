import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from './especialidad.service';

@Component({
  templateUrl: './especialidad-delete-dialog.component.html'
})
export class EspecialidadDeleteDialogComponent {
  especialidad?: IEspecialidad;

  constructor(
    protected especialidadService: EspecialidadService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.especialidadService.delete(id).subscribe(() => {
      this.eventManager.broadcast('especialidadListModification');
      this.activeModal.close();
    });
  }
}
