import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IMedico } from 'app/shared/model/medico.model';

@Component({
  templateUrl: './medico-guardado-dialog.component.html'
})
export class MedicoGuardadoDialogComponent {
  medico?: IMedico;

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
