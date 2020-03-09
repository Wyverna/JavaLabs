import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'unit-lotr',
        loadChildren: () => import('./unit-lotr/unit-lotr.module').then(m => m.Javalab7Sem2UnitLotrModule)
      },
      {
        path: 'image-lotr',
        loadChildren: () => import('./image-lotr/image-lotr.module').then(m => m.Javalab7Sem2ImageLotrModule)
      },
      {
        path: 'language-lotr',
        loadChildren: () => import('./language-lotr/language-lotr.module').then(m => m.Javalab7Sem2LanguageLotrModule)
      },
      {
        path: 'expedition-lotr',
        loadChildren: () => import('./expedition-lotr/expedition-lotr.module').then(m => m.Javalab7Sem2ExpeditionLotrModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class Javalab7Sem2EntityModule {}
