export interface FullPatient extends IPatient {
  risk?: string;
}

export interface IPatient {
  id?: string;
  firstName: string;
  lastName: string;
  birthdate: string;
  gender: string;
  phoneNumber: string;
  address: string;
}

export class Patient implements IPatient {
  constructor(
    public firstName: string,
    public lastName: string,
    public birthdate: string,
    public gender: string,
    public phoneNumber: string,
    public address: string,
    public id?: string
  ) {}
}