export class Location {
  // private int id;

	// private String city;
	// private String state;
	// private String country;

  id: number;
  city: string;
  state: string;
  country: string;

  constructor(
    id: number = 0,
    city: string = '',
    state: string = '',
    country: string = ''
  ) {
    this.id = id;
    this.city = city;
    this.state = state;
    this.country = country;
  }
}
