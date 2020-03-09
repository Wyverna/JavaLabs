import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Javalab7Sem2SharedModule } from 'app/shared/shared.module';
import { LanguageLotrComponent } from './language-lotr.component';
import { LanguageLotrDetailComponent } from './language-lotr-detail.component';
import { LanguageLotrUpdateComponent } from './language-lotr-update.component';
import { LanguageLotrDeleteDialogComponent } from './language-lotr-delete-dialog.component';
import { languageRoute } from './language-lotr.route';

@NgModule({
  imports: [Javalab7Sem2SharedModule, RouterModule.forChild(languageRoute)],
  declarations: [LanguageLotrComponent, LanguageLotrDetailComponent, LanguageLotrUpdateComponent, LanguageLotrDeleteDialogComponent],
  entryComponents: [LanguageLotrDeleteDialogComponent]
})
export class Javalab7Sem2LanguageLotrModule {}
