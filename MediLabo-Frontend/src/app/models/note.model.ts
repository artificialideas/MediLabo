export interface INote {
  id: string;
  date: string;
  patId: string;
  patient: string;
  note: string;
}

export class Note implements INote {
  constructor(
    public id: string,
    public date: string,
    public patId: string,
    public patient: string,
    public note: string,
  ) {}
}