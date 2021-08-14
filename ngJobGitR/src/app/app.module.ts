import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JobService } from './services/job.service';
import { JobListComponent } from './components/job-list/job-list.component';

@NgModule({
  declarations: [
    AppComponent,
    JobListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // For two-way data bind ngModel kinda stuff
    HttpClientModule
  ],
  providers: [
    JobService // Where we put the injectables to use in constructors
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
