import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFranjaHoraria } from 'app/shared/model/franja-horaria.model';
import { FranjaHorariaService } from './franja-horaria.service';

@Component({
  templateUrl: './franja-horaria-delete-dialog.component.html'
})
export class FranjaHorariaDeleteDialogComponent {
  franjaHoraria?: IFranjaHoraria;

  constructor(
    protected franjaHorariaService: FranjaHorariaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.franjaHorariaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('franjaHorariaListModification');
      this.activeModal.close();
    });
  }
}
