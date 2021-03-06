import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { QuileiaJavaWebSharedModule } from 'app/shared/shared.module';
import { QuileiaJavaWebCoreModule } from 'app/core/core.module';
import { QuileiaJavaWebAppRoutingModule } from './app-routing.module';
import { QuileiaJavaWebHomeModule } from './home/home.module';
import { QuileiaJavaWebEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    BrowserModule,
    QuileiaJavaWebSharedModule,
    QuileiaJavaWebCoreModule,
    QuileiaJavaWebHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    QuileiaJavaWebEntityModule,
    QuileiaJavaWebAppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class QuileiaJavaWebAppModule {}
