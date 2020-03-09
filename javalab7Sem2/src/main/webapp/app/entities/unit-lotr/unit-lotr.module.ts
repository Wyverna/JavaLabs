import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Javalab7Sem2SharedModule } from 'app/shared/shared.module';
import { UnitLotrComponent } from './unit-lotr.component';
import { UnitLotrDetailComponent } from './unit-lotr-detail.component';
import { UnitLotrUpdateComponent } from './unit-lotr-update.component';
import { UnitLotrDeleteDialogComponent } from './unit-lotr-delete-dialog.component';
import { unitRoute } from './unit-lotr.route';

@NgModule({
  imports: [Javalab7Sem2SharedModule, RouterModule.forChild(unitRoute)],
  declarations: [UnitLotrComponent, UnitLotrDetailComponent, UnitLotrUpdateComponent, UnitLotrDeleteDialogComponent],
  entryComponents: [UnitLotrDeleteDialogComponent]
})
export class Javalab7Sem2UnitLotrModule {}
