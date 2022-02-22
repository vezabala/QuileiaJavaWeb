import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'franja-horaria',
        loadChildren: () => import('./franja-horaria/franja-horaria.module').then(m => m.QuileiaJavaWebFranjaHorariaModule)
      },
      {
        path: 'horario',
        loadChildren: () => import('./horario/horario.module').then(m => m.QuileiaJavaWebHorarioModule)
      },
      {
        path: 'tipo-documento',
        loadChildren: () => import('./tipo-documento/tipo-documento.module').then(m => m.QuileiaJavaWebTipoDocumentoModule)
      },
      {
        path: 'especialidad',
        loadChildren: () => import('./especialidad/especialidad.module').then(m => m.QuileiaJavaWebEspecialidadModule)
      },
      {
        path: 'medico',
        loadChildren: () => import('./medico/medico.module').then(m => m.QuileiaJavaWebMedicoModule)
      },
      {
        path: 'paciente',
        loadChildren: () => import('./paciente/paciente.module').then(m => m.QuileiaJavaWebPacienteModule)
      },
      {
        path: 'cita',
        loadChildren: () => import('./cita/cita.module').then(m => m.QuileiaJavaWebCitaModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class QuileiaJavaWebEntityModule {}
