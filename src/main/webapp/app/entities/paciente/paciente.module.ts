import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { QuileiaJavaWebSharedModule } from 'app/shared/shared.module';
import { PacienteComponent } from './paciente.component';
import { PacienteDetailComponent } from './paciente-detail.component';
import { PacienteUpdateComponent } from './paciente-update.component';
import { PacienteDeleteDialogComponent } from './paciente-delete-dialog.component';
import { pacienteRoute } from './paciente.route';

@NgModule({
  imports: [QuileiaJavaWebSharedModule, RouterModule.forChild(pacienteRoute)],
  declarations: [PacienteComponent, PacienteDetailComponent, PacienteUpdateComponent, PacienteDeleteDialogComponent],
  entryComponents: [PacienteDeleteDialogComponent]
})
export class QuileiaJavaWebPacienteModule {}
