import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { QuileiaJavaWebSharedModule } from 'app/shared/shared.module';
import { CitaComponent } from './cita.component';
import { CitaDetailComponent } from './cita-detail.component';
import { CitaUpdateComponent } from './cita-update.component';
import { CitaDeleteDialogComponent } from './cita-delete-dialog.component';
import { citaRoute } from './cita.route';
import { CitaGuardadoDialogComponent } from 'app/entities/cita/cita-guardado-dialog.component';

@NgModule({
  imports: [QuileiaJavaWebSharedModule, RouterModule.forChild(citaRoute)],
  declarations: [CitaComponent, CitaDetailComponent, CitaUpdateComponent, CitaDeleteDialogComponent, CitaGuardadoDialogComponent],
  entryComponents: [CitaDeleteDialogComponent, CitaGuardadoDialogComponent]
})
export class QuileiaJavaWebCitaModule {}
