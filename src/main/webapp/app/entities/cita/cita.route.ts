import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICita, Cita } from 'app/shared/model/cita.model';
import { CitaService } from './cita.service';
import { CitaComponent } from './cita.component';
import { CitaDetailComponent } from './cita-detail.component';
import { CitaUpdateComponent } from './cita-update.component';

@Injectable({ providedIn: 'root' })
export class CitaResolve implements Resolve<ICita> {
  constructor(private service: CitaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICita> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cita: HttpResponse<Cita>) => {
          if (cita.body) {
            return of(cita.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Cita());
  }
}

export const citaRoute: Routes = [
  {
    path: '',
    component: CitaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [],
      defaultSort: 'id,asc',
      pageTitle: 'quileiaJavaWebApp.cita.home.title'
    }
  },
  {
    path: ':id/view',
    component: CitaDetailComponent,
    resolve: {
      cita: CitaResolve
    },
    data: {
      authorities: [],
      pageTitle: 'quileiaJavaWebApp.cita.home.title'
    }
  },
  {
    path: 'new',
    component: CitaUpdateComponent,
    resolve: {
      cita: CitaResolve
    },
    data: {
      authorities: [],
      pageTitle: 'quileiaJavaWebApp.cita.home.title'
    }
  },
  {
    path: ':id/edit',
    component: CitaUpdateComponent,
    resolve: {
      cita: CitaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'quileiaJavaWebApp.cita.home.title'
    }
  }
];
