window.addEventListener("load", function (e) {
  console.log("script.js loaded");
  go();
});

function go() {
  loadJobs();
  loadCompanyList();
  loadLocationList();
  loadCategoryList();
  // CREATE JOB
  let newJobButt = document.getElementById("newJobButt");
  newJobButt.addEventListener("click", function (e) {
    e.preventDefault();
    let allJobsDiv = document.getElementById("jobTableDiv");
    allJobsDiv.style.display = "none";

    let newJobDiv = document.getElementById("newJob");
    newJobDiv.style.display = "block";
  });
  // saves job and resets form
  let saveJobButt = document.getElementById("saveJob");
  saveJobButt.addEventListener("click", function (e) {
    e.preventDefault();
    let newJob = getNewJobInfoFromForm();
	console.log(newJob);
    createNewJob(newJob);
    let newJobDiv = document.getElementById("newJob");
    newJobDiv.style.display = "none";
    document.getElementById("jobForm").reset();
  });

  //   // UPDATE/DELETE JOB

  // updates job and resets form
  let updateJobButt = document.getElementById("updateJob");
  updateJobButt.addEventListener("click", function (e) {
    e.preventDefault();
    let jobUpdated = updateJobInfoForm();
    updateJob(jobUpdated);
    let jobDetailsDiv = document.getElementById("jobDetails");
    jobDetailsDiv.style.display = "none";
    document.getElementById("jobForm").reset();
  });
  let deleteJobButt = document.getElementById("deleteJob");
  deleteJobButt.addEventListener('click', function (e) {
	e.preventDefault();
    let jobToDelete = updateJobInfoForm();
	let jobId = jobToDelete.id;
	console.log(jobId);
	deleteJob(jobId);
	let jobDetailsDiv = document.getElementById("jobDetails");
    jobDetailsDiv.style.display = "none";
    document.getElementById("jobForm").reset();
  })
}

function loadCompanyList() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/companies");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        let companies = JSON.parse(xhr.responseText);
        let companyDropDown = document.getElementById("companyId");
		let companyUpdate = document.getElementById('companyIdDetails');

        // console.log(companies);
        for (const company of companies) {
          let companyOption = document.createElement("option");
          companyOption.value = company.id;
             console.log(company.name);
          companyOption.textContent = company.name;
          companyOption.name = company.name;
          companyOption.description = company.description;
          companyOption.size = company.size;
          companyOption.phone = company.phone;
          companyOption.recruitingFor = company.recruitingFor;

          companyDropDown.appendChild(companyOption);
          companyUpdate.appendChild(companyOption);

		}
      } else {
        console.log("uh oh, no bueno");
      }
    }
  };
  xhr.send();
}

function loadLocationList() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/locations");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        let locations = JSON.parse(xhr.responseText);
        let locationDropDown = document.getElementById("locationId");
		let locationUpdate = document.getElementById("locationIdDetails");

        console.log(locations);
        for (const location of locations) {
          let locationOption = document.createElement("option");
          locationOption.value = location.id;
          //   console.log(companyOption.value);
          locationOption.textContent = location.city;
          locationDropDown.appendChild(locationOption);
		  locationUpdate.appendChild(locationOption);

        }
      } else {
        console.log("uh oh, no bueno");
      }
    }
  };
  xhr.send();
}

function loadCategoryList() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/categories");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        let categories = JSON.parse(xhr.responseText);
        let categoryDropDown = document.getElementById("categoryId");
        let categoryUpdate = document.getElementById("categoryIdDetails");

        console.log(categories);
        for (const category of categories) {
          let categoryOption = document.createElement("option");
          categoryOption.value = category.id;
          //   console.log(companyOption.value);
          categoryOption.textContent = category.name;
          categoryDropDown.appendChild(categoryOption);
		  categoryUpdate.appendChild(categoryOption);
        }
      } else {
        console.log("uh oh, no bueno");
      }
    }
  };
  xhr.send();
}

function loadJobs() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/jobs");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        console.log("in loadJobs w/ 200");
        let jobs = JSON.parse(xhr.responseText);
        displayJobs(jobs);
      } else {
        console.log("uh oh, no bueno");
      }
    }
  };
  xhr.send();
}

function displayJobs(jobs) {
  let table = document.getElementById("jobTable");
  table.textContent = "";
  for (const job of jobs) {
    let tr = document.createElement("tr");
    let td = document.createElement("td");
    td.id = job.id;
    td.textContent = job.title;
    td.title = job.title;
    td.name = job.name;
    td.pay = job.pay;
    td.skills = job.skills;
    td.description = job.description;
    td.company = job.company;
    td.location = job.location;
    td.category = job.category;
    td.active = job.active;

    tr.appendChild(td);
    table.appendChild(tr);
  }
  document.querySelectorAll("#jobTable td").forEach((e) =>
    e.addEventListener("click", function (event) {
      let jobClicked = event.target;
      id = jobClicked.value;
    //   console.log(jobClicked.title);
      // TODO - add function to clear table/addjob button/ display form prefilled with details from selected job
      displayJobDetails(jobClicked);
    })
  );
}

function displayJobDetails(jobToUpdate) {
  let updateJobDiv = document.getElementById("jobDetails");
  let updateForm = document.getElementById("updateJobForm");
  let companySel = document.getElementById("companyIdDetails");
  let company = jobToUpdate.company;
  let locationSel = document.getElementById("locationIdDetails");
  let location = jobToUpdate.location;
  let categorySel = document.getElementById("categoryIdDetails");
  let category = jobToUpdate.category;
  //   console.log(jobToUpdate.title);
  updateForm.id.value = jobToUpdate.id;
  updateForm.title.value = jobToUpdate.title;
  updateForm.name.value = jobToUpdate.name;
  updateForm.pay.value = jobToUpdate.pay;
  updateForm.skills.value = jobToUpdate.skills;
  updateForm.description.value = jobToUpdate.description;
  updateForm.active.checked = jobToUpdate.active;

  let opts = companySel.options;
  for (let index = 0; index < opts.length; index++) {
    let element = opts[index];
    if (element.name === company.name) {
      companySel.selectedIndex = index;
    }
  }
  let locOpts = locationSel.options;
  //   console.log(locOpts[3].city);
  for (let index = 0; index < locOpts.length; index++) {
    let element = locOpts[index];
    if (element.textContent === location.city) {
      locationSel.selectedIndex = index;
    }
  }
  // console.log(category.id);
  let catOpts = categorySel.options;
  for (let index = 0; index < catOpts.length; index++) {
    let element = catOpts[index];
	if (category === null) {
		categorySel.selectedIndex = 0;
	} else if (element.textContent === category.name) {
      categorySel.selectedIndex = index;
    }
  }
  updateJobDiv.style.display = "block";
}

function updateJobInfoForm() {
  let job = {};
  let company = {};
  let location = {};
  let category = {};
  let jobUpdateForm = document.getElementById("updateJobForm");

  job.id = jobUpdateForm.id.value;
  job.title = jobUpdateForm.title.value;
  job.name = jobUpdateForm.name.value;
  job.pay = jobUpdateForm.pay.value;
  job.skills = jobUpdateForm.skills.value;
  job.description = jobUpdateForm.description.value;
  job.active = jobUpdateForm.active.checked;

  console.log(jobUpdateForm.companyIdDetails.value);

  company.id = jobUpdateForm.companyIdDetails.value;
  job.company = company;
  location.id = jobUpdateForm.locationIdDetails.value;
  job.location = location;
  category.id = jobUpdateForm.categoryIdDetails.value;
  job.category = category;
  return job;
}

function getNewJobInfoFromForm() {
  let newJob = {};
  let company = {};
  let location = {};
  let category = {};
  let jobForm = document.getElementById("jobForm");

  newJob.title = jobForm.title.value;
  newJob.name = jobForm.name.value;
  newJob.pay = jobForm.pay.value;
  newJob.skills = jobForm.skills.value;
  newJob.description = jobForm.description.value;

  company.id = jobForm.companyId.value;
  newJob.company = company;
  location.id = jobForm.locationId.value;
  newJob.location = location;
  category.id = jobForm.categoryId.value;
  newJob.category = category;
  //   console.log(newJob);
  return newJob;
}

function createNewJob(newJob) {
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "api/jobs", true);

  xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON request body

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status == 200 || xhr.status == 201) {
        // Ok or Created
        var data = JSON.parse(xhr.responseText);
        console.log("In the createNewJob method");
        loadJobs();
      } else {
        console.log("POST request failed.");
        console.error(xhr.status + ": " + xhr.responseText);
      }
    }
  };

  var newJobJson = JSON.stringify(newJob); // Convert JS object to JSON string

  xhr.send(newJobJson);
}

function updateJob(job) {
  var xhr = new XMLHttpRequest();
  xhr.open("PUT", "api/jobs", true);

  xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON request body

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status == 200 || xhr.status == 201) {
        // Ok or Created
        var data = JSON.parse(xhr.responseText);
        console.log("In the updateJob method w/success");
        loadJobs();
      } else {
        console.log("PUT request failed.");
        console.error(xhr.status + ": " + xhr.responseText);
      }
    }
  };

  var updateJobJson = JSON.stringify(job); // Convert JS object to JSON string

  xhr.send(updateJobJson);
}

function deleteJob(jobId) {
	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", "api/jobs/" + jobId, true);
  
	// xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON request body
  
	xhr.onreadystatechange = function () {
	  if (xhr.readyState === 4) {
		if (xhr.status == 200 || xhr.status == 204) {
		  // Ok or Created
		//   var data = JSON.parse(xhr.responseText);
		  console.log("In the deleteJob method w/success");
		  loadJobs();
		} else {
		  console.log("DELETE request failed.");
		  console.error(xhr.status + ": " + xhr.responseText);
		}
	  }
	};
  
	// var updateJobJson = JSON.stringify(job); // Convert JS object to JSON string
  
	xhr.send();
  }