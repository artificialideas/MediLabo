import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../models/patient.model';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  public gatewayUrl: string = "http://localhost:9000/patients";

  constructor(
    private http: HttpClient
  ) {}

  public findAll(): Observable<HttpResponse<Patient[]>> {
    return this.http.get<Patient[]>(`${this.gatewayUrl}/`, { observe: 'response'});
  }

  public findPatient(firstName: string, lastName: string): Observable<HttpResponse<Patient>> {
    return this.http.get<Patient>(`${this.gatewayUrl}/${firstName}-${lastName}`, { observe: 'response'});
  }

  public add(patient: Patient): Observable<HttpResponse<Patient>> {
    return this.http.post<Patient>(`${this.gatewayUrl}/`, patient, { observe: 'response' });
  }

  public update(firstName: string, lastName: string, patient: Patient): Observable<HttpResponse<Patient>> {
    return this.http.put<Patient>(`${this.gatewayUrl}/${firstName}-${lastName}`, patient, { observe: 'response' });
  }

  public delete(firstName: string, lastName: string): Observable<HttpResponse<Patient[]>> {
    return this.http.delete<Patient[]>(`${this.gatewayUrl}/${firstName}-${lastName}`, { observe: 'response' });
  }
}
