import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IPaciente } from 'app/shared/model/paciente.model';

@Component({
  templateUrl: './paciente-guardado-dialog.component.html'
})
export class PacienteGuardadoDialogComponent {
  paciente?: IPaciente;

  constructor(public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    window.history.back();
    this.activeModal.dismiss();
  }
  previousState(): void {
    window.history.back();
    this.activeModal.dismiss();
  }
}
