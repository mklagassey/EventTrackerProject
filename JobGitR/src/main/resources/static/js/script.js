window.addEventListener("load", function (e) {
  console.log("script.js loaded");
  go();
});

function go() {
  loadJobs();
  loadCompanyList();
  // CREATE JOB
  let newJobButt = document.getElementById("newJobButt");
  newJobButt.addEventListener("click", function (e) {
    e.preventDefault();
    let newJobDiv = document.getElementById("newJob");
    newJobDiv.style.display = "block";
  });
  let saveJobButt = document.getElementById("saveJob");
  saveJobButt.addEventListener("click", function (e) {
    e.preventDefault();
    let newJob = getNewJobInfoFromForm();
    createNewJob(newJob);
  });
  // UPDATE/DELETE JOB
  // TODO
}

function loadCompanyList() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/companies");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        let companies = JSON.parse(xhr.responseText);
        let companyDropDown = document.getElementById("companyId");

        console.log(companies);
        for (const company of companies) {
          let companyOption = document.createElement("option");
		  companyOption.value = company.id;
		//   console.log(companyOption.value);
          companyOption.textContent = company.name;
          companyDropDown.appendChild(companyOption);
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

  for (const job of jobs) {
    let tr = document.createElement("tr");
    let td = document.createElement("td");
    td.textContent = job.title;
    tr.appendChild(td);
    table.appendChild(tr);
  }
}

function getNewJobInfoFromForm() {
  let newJob = {};
  let company = {};
  let jobForm = document.getElementById("jobForm");
  newJob.title = jobForm.title.value;
  newJob.name = jobForm.name.value;
  newJob.pay = jobForm.pay.value;
  newJob.skills = jobForm.skills.value;
  newJob.description = jobForm.description.value;
  company.id = jobForm.companyId.value;
  newJob.company = company;
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
