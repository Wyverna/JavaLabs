import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Javalab7Sem2SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [Javalab7Sem2SharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class Javalab7Sem2HomeModule {}
