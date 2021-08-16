import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category';
import { Company } from 'src/app/models/company';
import { Job } from 'src/app/models/job';
import { CategoryService } from 'src/app/services/category.service';
import { CompanyService } from 'src/app/services/company.service';
import { JobService } from 'src/app/services/job.service';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';


@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css']
})
export class JobListComponent implements OnInit {
  listAll: boolean = false;
  createJob: boolean = false;
  editJob: boolean = false;

  newJob: Job = new Job();
  selectedJob: Job = new Job();
  selectedCompany: Company = new Company();
  selectedCategory: Category = new Category();
  selectedLocation: Location = new Location();

  jobs: Job[] = [];
  companies: Company[] = [];
  categories: Category[] = [];
  locations: Location[] = [];

  constructor(
    private jobServ: JobService,
    private companyServ: CompanyService,
    private categoryServ: CategoryService,
    private locationServ: LocationService
  ) { }

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.loadJobs();
    this.loadCompanies();
    this.loadCategories();
    this.loadLocations();
  }

  checkSelectedCompany(co: any) {
    this.companies.forEach(arrComp => {
      if (arrComp.name == co || arrComp.id == co.id) {
        this.selectedCompany = arrComp;
      }
    });
  }

  checkSelectedCategory(cat: any) {
    this.categories.forEach(arrCat => {
      if (arrCat.name == cat || arrCat.id == cat.id) {
        this.selectedCategory = arrCat;
      }
    });
  }

  checkSelectedLocation(loc: any) {
    this.locations.forEach(arrLoc => {
      if (arrLoc.city == loc || arrLoc.id == loc.id) {
        this.selectedLocation = arrLoc;
      }
    });
  }

  selectCompanyHandler (event: any) {
    //update the ui
    this.checkSelectedCompany(event.target.value);
  }

  selectCategoryHandler (event: any) {
    //update the ui
    this.checkSelectedCategory(event.target.value);
  }

  selectLocationHandler (event: any) {
    //update the ui
    this.checkSelectedLocation(event.target.value);
  }

  loadCompanies() {
    this.companyServ.index().subscribe(
      companies => { this.companies = companies },
      bad => console.error("Borked something in loadJobs()")
    )
  }

  loadCategories() {
    this.categoryServ.index().subscribe(
      categories => { this.categories = categories },
      bad => console.error("Borked something in loadcategories()")
    )
  }

  loadLocations() {
    this.locationServ.index().subscribe(
      locations => { this.locations = locations },
      bad => console.error("Borked something in loadcategories()")
    )
  }

  loadJobs() {
    this.jobServ.index().subscribe(
      jobs => {
        this.jobs = jobs;
      },
      fail => {
        console.log("No jobs for you!");

      }
    )
  }

  addJob(): void {
    this.newJob.company = this.selectedCompany;
    this.newJob.category = this.selectedCategory;
    this.newJob.location = this.selectedLocation;

    console.log(this.newJob.location);

    this.jobServ.create(this.newJob).subscribe(
      (data) => {
        this.createJob = false;
        console.log("In addJob()");

        this.loadJobs();
      },
      (err) => console.error('Observer got an error: ' + err)
    );
  }

  updateJob() {

    this.selectedJob.company = this.selectedCompany;
    this.selectedJob.category = this.selectedCategory;
    this.selectedJob.location = this.selectedLocation;
    console.log("In updateJob() with : " + JSON.stringify(this.selectedJob));


    this.jobServ.update(this.selectedJob).subscribe(
      (data) => {this.selectedJob;
      this.selectedJob = new Job();
      this.editJob = false;
      this.reload();
      },

      (err) => console.error('Observer got an error: ' + err)
    );
  }

  selectJob(jobIn: Job) {
  this.selectedJob.id = jobIn.id;
  this.selectedJob.title = jobIn.title;
  this.selectedJob.name = jobIn.name;
  this.selectedJob.skills = jobIn.skills;
  this.selectedJob.description = jobIn.description;
  this.selectedJob.pay = jobIn.pay;
  this.selectedJob.active = jobIn.active;

  this.checkSelectedCompany(jobIn.company);
  this.checkSelectedLocation(jobIn.location);
  this.checkSelectedCategory(jobIn.category);

  // this.selectedJob.company = jobIn.company;
  // this.selectedJob.location = jobIn.location;
  // this.selectedJob.category = jobIn.category;
  console.log("in selectJob() with selectedJob location: " + JSON.stringify(this.selectedLocation.city));

    }

}
