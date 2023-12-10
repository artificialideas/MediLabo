export interface INote {
  id?: string;
  date?: Date;
  patId: string;
  patient: string;
  note: string;
}

export class Note implements INote {
  constructor(
    public patId: string,
    public note: string,
    public patient: string,
    public id?: string,
    public date?: Date,
  ) {}
}