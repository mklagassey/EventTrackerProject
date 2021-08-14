import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company';
import { Job } from 'src/app/models/job';
import { CompanyService } from 'src/app/services/company.service';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css']
})
export class JobListComponent implements OnInit {
  listAll: boolean = false;
  createJob: boolean = false;

  newJob: Job = new Job();
  selectedCompany: Company = new Company();

  jobs: Job[] = [];
  companies: Company[] = [];

  constructor(
    private jobServ: JobService,
    private companyServ: CompanyService
  ) { }

  ngOnInit(): void {
    this.loadJobs();
  }

  checkSelectedCompany(co: any) {
    this.companies.forEach(arrComp => {
      if (arrComp.name == co) {
        console.log("matched selected company to array company");
        this.selectedCompany = arrComp;
      }
    });
  }

  selectCompanyHandler (event: any) {
    //update the ui
    this.checkSelectedCompany(event.target.value);
    console.log(this.selectedCompany);

  }

  loadJobs() {
    this.jobServ.index().subscribe(
      jobs => {
        this.companyServ.index().subscribe(
          companies => { this.companies = companies },
          bad => console.error("Borked something in loadJobs() -> company index()")

        )
        this.jobs = jobs
      },
      fail => {
        console.log("No jobs for you!");

      }
    )
  }

  addJob(): void {
    this.newJob.company = this.selectedCompany;
    console.log(this.newJob.company);

    this.jobServ.create(this.newJob).subscribe(
      (data) => {
        console.log("In addJob()");

        this.loadJobs();
      },
      (err) => console.error('Observer got an error: ' + err)
    );
  }

}
