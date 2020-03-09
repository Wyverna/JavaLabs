import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Javalab7Sem2SharedModule } from 'app/shared/shared.module';
import { ImageLotrComponent } from './image-lotr.component';
import { ImageLotrDetailComponent } from './image-lotr-detail.component';
import { ImageLotrUpdateComponent } from './image-lotr-update.component';
import { ImageLotrDeleteDialogComponent } from './image-lotr-delete-dialog.component';
import { imageRoute } from './image-lotr.route';

@NgModule({
  imports: [Javalab7Sem2SharedModule, RouterModule.forChild(imageRoute)],
  declarations: [ImageLotrComponent, ImageLotrDetailComponent, ImageLotrUpdateComponent, ImageLotrDeleteDialogComponent],
  entryComponents: [ImageLotrDeleteDialogComponent]
})
export class Javalab7Sem2ImageLotrModule {}
