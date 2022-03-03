import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { QuileiaJavaWebSharedModule } from 'app/shared/shared.module';
import { EspecialidadComponent } from './especialidad.component';
import { EspecialidadDetailComponent } from './especialidad-detail.component';
import { EspecialidadUpdateComponent } from './especialidad-update.component';
import { EspecialidadDeleteDialogComponent } from './especialidad-delete-dialog.component';
import { especialidadRoute } from './especialidad.route';

@NgModule({
  imports: [QuileiaJavaWebSharedModule, RouterModule.forChild(especialidadRoute)],
  declarations: [EspecialidadComponent, EspecialidadDetailComponent, EspecialidadUpdateComponent, EspecialidadDeleteDialogComponent],
  entryComponents: [EspecialidadDeleteDialogComponent]
})
export class QuileiaJavaWebEspecialidadModule {}
