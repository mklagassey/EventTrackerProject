export class Company {
  // private int id;
	// private String name;
	// private String description;
	// private String size;
	// private String phone;

  // private Company recruitingFor;

  id: number;
  name: string;
  description: string;
  size: string;
  phone: string;
  recruitingFor: object;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    size: string = '',
    phone: string = '',
    recruitingFor: object =[]
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.size = size;
    this.phone = phone;
    this.recruitingFor = recruitingFor;


  }
}
