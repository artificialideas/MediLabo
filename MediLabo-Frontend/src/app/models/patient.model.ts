export interface IPatient {
  id: number;
  firstName: string;
  lastName: string;
  birthdate: string;
  gender: string;
  phoneNumber: string;
  address: string;
}

export class Patient implements IPatient {
  constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public birthdate: string,
    public gender: string,
    public phoneNumber: string,
    public address: string,
  ) {}
}