export interface INote {
  id: string;
  patId: string;
  patient: string;
  note: string;
}

export class Note implements INote {
  constructor(
    public id: string,
    public patId: string,
    public patient: string,
    public note: string,
  ) {}
}