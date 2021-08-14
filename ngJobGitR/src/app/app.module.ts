import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JobService } from './services/job.service';
import { JobListComponent } from './components/job-list/job-list.component';
import { HomeComponent } from './components/home/home.component';
import { CompanyComponent } from './components/company/company.component';
import { CompanyService } from './services/company.service';

@NgModule({
  declarations: [
    AppComponent,
    JobListComponent,
    HomeComponent,
    CompanyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // For two-way data bind ngModel kinda stuff
    HttpClientModule
  ],
  providers: [
    JobService, // Where we put the injectables to use in constructors
    CompanyService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
