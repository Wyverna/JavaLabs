import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Javalab7Sem2SharedModule } from 'app/shared/shared.module';

import { AuditsComponent } from './audits.component';

import { auditsRoute } from './audits.route';

@NgModule({
  imports: [Javalab7Sem2SharedModule, RouterModule.forChild([auditsRoute])],
  declarations: [AuditsComponent]
})
export class AuditsModule {}
