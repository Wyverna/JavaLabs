import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UnitLotr } from 'app/shared/model/unit-lotr.model';
import { UnitLotrService } from './unit-lotr.service';
import { UnitLotrComponent } from './unit-lotr.component';
import { UnitLotrDetailComponent } from './unit-lotr-detail.component';
import { UnitLotrUpdateComponent } from './unit-lotr-update.component';
import { IUnitLotr } from 'app/shared/model/unit-lotr.model';

@Injectable({ providedIn: 'root' })
export class UnitLotrResolve implements Resolve<IUnitLotr> {
  constructor(private service: UnitLotrService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUnitLotr> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((unit: HttpResponse<UnitLotr>) => unit.body));
    }
    return of(new UnitLotr());
  }
}

export const unitRoute: Routes = [
  {
    path: '',
    component: UnitLotrComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.unit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UnitLotrDetailComponent,
    resolve: {
      unit: UnitLotrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.unit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UnitLotrUpdateComponent,
    resolve: {
      unit: UnitLotrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.unit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UnitLotrUpdateComponent,
    resolve: {
      unit: UnitLotrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.unit.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
