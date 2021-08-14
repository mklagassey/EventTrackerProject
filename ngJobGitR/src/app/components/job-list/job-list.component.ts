import { Component, OnInit } from '@angular/core';
import { Job } from 'src/app/models/job';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css']
})
export class JobListComponent implements OnInit {

  jobs: Job[] = [];

  constructor(
    private jobServ: JobService
  ) { }

  ngOnInit(): void {
    this.loadJobs();
  }

  loadJobs() {
    this.jobServ.index().subscribe(
      jobs => {
        this.jobs = jobs
      },
      fail => {
        console.log("No jobs for you!");

      }
    )
  }

}
