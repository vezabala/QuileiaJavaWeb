import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFranjaHoraria, FranjaHoraria } from 'app/shared/model/franja-horaria.model';
import { FranjaHorariaService } from './franja-horaria.service';
import { FranjaHorariaComponent } from './franja-horaria.component';
import { FranjaHorariaDetailComponent } from './franja-horaria-detail.component';
import { FranjaHorariaUpdateComponent } from './franja-horaria-update.component';

@Injectable({ providedIn: 'root' })
export class FranjaHorariaResolve implements Resolve<IFranjaHoraria> {
  constructor(private service: FranjaHorariaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFranjaHoraria> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((franjaHoraria: HttpResponse<FranjaHoraria>) => {
          if (franjaHoraria.body) {
            return of(franjaHoraria.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FranjaHoraria());
  }
}

export const franjaHorariaRoute: Routes = [
  {
    path: '',
    component: FranjaHorariaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'quileiaJavaWebApp.franjaHoraria.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FranjaHorariaDetailComponent,
    resolve: {
      franjaHoraria: FranjaHorariaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'quileiaJavaWebApp.franjaHoraria.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FranjaHorariaUpdateComponent,
    resolve: {
      franjaHoraria: FranjaHorariaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'quileiaJavaWebApp.franjaHoraria.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FranjaHorariaUpdateComponent,
    resolve: {
      franjaHoraria: FranjaHorariaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'quileiaJavaWebApp.franjaHoraria.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
