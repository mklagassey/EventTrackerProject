export class Job {

	// @CreationTimestamp
	// private LocalDateTime posted;

	// @UpdateTimestamp
	// private LocalDateTime updated;

  id: number;
  title: string;
  name: string;
  skills: string;
  description: string;
  pay: number;
  active: boolean;
  company: object;
  location: object;
  category: object;


  constructor(
  id: number = 0,
  title: string = '',

  name: string = '',
  skills: string = '',
  description: string = '',
  pay: number = 0,
  active: boolean = false,

  company: object = {},

  location: object = {id: 1},
  category: object = {id: 1}

  ) {
  this.id = id;
  this.title = title;
  this.name = name;
  this.skills = skills;
  this.description = description;
  this.pay = pay;
  this.active = active;
  this.company = company;
  this.location = location;
  this.category = category;
  }
}
