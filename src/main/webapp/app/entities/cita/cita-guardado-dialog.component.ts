import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ICita } from 'app/shared/model/cita.model';

@Component({
  templateUrl: './cita-guardado-dialog.component.html'
})
export class CitaGuardadoDialogComponent {
  cita?: ICita;

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
