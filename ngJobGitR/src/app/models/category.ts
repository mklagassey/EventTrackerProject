export class Category {
  // private int id;
	// private String name;

  id: number;
  name: string;

  constructor(
    id: number = 0,
    name: string = ''
  ) {
    this.id = id;
    this.name = name;
  }
}
