import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { QuileiaJavaWebSharedModule } from 'app/shared/shared.module';
import { FranjaHorariaComponent } from './franja-horaria.component';
import { FranjaHorariaDetailComponent } from './franja-horaria-detail.component';
import { FranjaHorariaUpdateComponent } from './franja-horaria-update.component';
import { FranjaHorariaDeleteDialogComponent } from './franja-horaria-delete-dialog.component';
import { franjaHorariaRoute } from './franja-horaria.route';

@NgModule({
  imports: [QuileiaJavaWebSharedModule, RouterModule.forChild(franjaHorariaRoute)],
  declarations: [FranjaHorariaComponent, FranjaHorariaDetailComponent, FranjaHorariaUpdateComponent, FranjaHorariaDeleteDialogComponent],
  entryComponents: [FranjaHorariaDeleteDialogComponent]
})
export class QuileiaJavaWebFranjaHorariaModule {}
