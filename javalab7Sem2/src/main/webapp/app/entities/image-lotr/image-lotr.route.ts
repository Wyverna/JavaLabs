import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ImageLotr } from 'app/shared/model/image-lotr.model';
import { ImageLotrService } from './image-lotr.service';
import { ImageLotrComponent } from './image-lotr.component';
import { ImageLotrDetailComponent } from './image-lotr-detail.component';
import { ImageLotrUpdateComponent } from './image-lotr-update.component';
import { IImageLotr } from 'app/shared/model/image-lotr.model';

@Injectable({ providedIn: 'root' })
export class ImageLotrResolve implements Resolve<IImageLotr> {
  constructor(private service: ImageLotrService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IImageLotr> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((image: HttpResponse<ImageLotr>) => image.body));
    }
    return of(new ImageLotr());
  }
}

export const imageRoute: Routes = [
  {
    path: '',
    component: ImageLotrComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.image.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ImageLotrDetailComponent,
    resolve: {
      image: ImageLotrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.image.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ImageLotrUpdateComponent,
    resolve: {
      image: ImageLotrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.image.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ImageLotrUpdateComponent,
    resolve: {
      image: ImageLotrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'javalab7Sem2App.image.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
