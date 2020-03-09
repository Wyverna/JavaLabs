import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Javalab7Sem2SharedModule } from 'app/shared/shared.module';

import { JhiDocsComponent } from './docs.component';

import { docsRoute } from './docs.route';

@NgModule({
  imports: [Javalab7Sem2SharedModule, RouterModule.forChild([docsRoute])],
  declarations: [JhiDocsComponent]
})
export class DocsModule {}
