import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LanguageLotr } from 'app/shared/model/language-lotr.model';
import { LanguageLotrService } from './language-lotr.service';
import { LanguageLotrComponent } from './language-lotr.component';
import { LanguageLotrDetailComponent } from './language-lotr-detail.component';
import { LanguageLotrUpdateComponent } from './language-lotr-update.component';
import { ILanguageLotr } from 'app/shared/model/language-lotr.model';

@Injectable({ providedIn: 'root' })
export class LanguageLotrResolve implements Resolve<ILanguageLotr> {
  constructor(private service: LanguageLotrService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILanguageLotr> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((language: HttpResponse<LanguageLotr>) => language.body));
    }
    return of(new LanguageLotr());
  }
}

export const languageRoute: Routes = [
  {
    path: '',
    component: LanguageLotrComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.language.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LanguageLotrDetailComponent,
    resolve: {
      language: LanguageLotrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.language.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LanguageLotrUpdateComponent,
    resolve: {
      language: LanguageLotrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.language.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LanguageLotrUpdateComponent,
    resolve: {
      language: LanguageLotrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.language.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
