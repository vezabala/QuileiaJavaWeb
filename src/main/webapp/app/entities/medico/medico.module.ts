import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { QuileiaJavaWebSharedModule } from 'app/shared/shared.module';
import { MedicoComponent } from './medico.component';
import { MedicoDetailComponent } from './medico-detail.component';
import { MedicoUpdateComponent } from './medico-update.component';
import { MedicoDeleteDialogComponent } from './medico-delete-dialog.component';
import { medicoRoute } from './medico.route';
import { MedicoGuardadoDialogComponent } from 'app/entities/medico/medico-guardado-dialog.component';

@NgModule({
  imports: [QuileiaJavaWebSharedModule, RouterModule.forChild(medicoRoute)],
  declarations: [MedicoComponent, MedicoDetailComponent, MedicoUpdateComponent, MedicoDeleteDialogComponent, MedicoGuardadoDialogComponent],
  entryComponents: [MedicoDeleteDialogComponent, MedicoGuardadoDialogComponent]
})
export class QuileiaJavaWebMedicoModule {}
