export interface IPatient {
  firstName: string;
  lastName: string;
  birthdate: string;
  gender: string;
  phoneNumber: string;
  address: string;
  id?: string;
}

export class Patient implements IPatient {
  constructor(
    public firstName: string,
    public lastName: string,
    public birthdate: string,
    public gender: string,
    public phoneNumber: string,
    public address: string,
    public id?: string,
  ) {}
}