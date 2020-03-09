import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Javalab7Sem2SharedModule } from 'app/shared/shared.module';

import { LogsComponent } from './logs.component';

import { logsRoute } from './logs.route';

@NgModule({
  imports: [Javalab7Sem2SharedModule, RouterModule.forChild([logsRoute])],
  declarations: [LogsComponent]
})
export class LogsModule {}
