import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { Javalab7Sem2SharedModule } from 'app/shared/shared.module';
import { Javalab7Sem2CoreModule } from 'app/core/core.module';
import { Javalab7Sem2AppRoutingModule } from './app-routing.module';
import { Javalab7Sem2HomeModule } from './home/home.module';
import { Javalab7Sem2EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    Javalab7Sem2SharedModule,
    Javalab7Sem2CoreModule,
    Javalab7Sem2HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    Javalab7Sem2EntityModule,
    Javalab7Sem2AppRoutingModule
  ],
  declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [JhiMainComponent]
})
export class Javalab7Sem2AppModule {}
