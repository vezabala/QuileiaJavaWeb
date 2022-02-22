import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEspecialidad, Especialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from './especialidad.service';
import { EspecialidadComponent } from './especialidad.component';
import { EspecialidadDetailComponent } from './especialidad-detail.component';
import { EspecialidadUpdateComponent } from './especialidad-update.component';

@Injectable({ providedIn: 'root' })
export class EspecialidadResolve implements Resolve<IEspecialidad> {
  constructor(private service: EspecialidadService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEspecialidad> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((especialidad: HttpResponse<Especialidad>) => {
          if (especialidad.body) {
            return of(especialidad.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Especialidad());
  }
}

export const especialidadRoute: Routes = [
  {
    path: '',
    component: EspecialidadComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'quileiaJavaWebApp.especialidad.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EspecialidadDetailComponent,
    resolve: {
      especialidad: EspecialidadResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'quileiaJavaWebApp.especialidad.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EspecialidadUpdateComponent,
    resolve: {
      especialidad: EspecialidadResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'quileiaJavaWebApp.especialidad.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EspecialidadUpdateComponent,
    resolve: {
      especialidad: EspecialidadResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'quileiaJavaWebApp.especialidad.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
