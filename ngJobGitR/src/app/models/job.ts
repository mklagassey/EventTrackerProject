export class Job {
  id: number;
  title: string;
  company: object;

  constructor(
  id: number = 0,
  title: string = '',
  company: object = {}
  ) {
  this.id = id;
  this.title = title;
  this.company = company;
  }
}
