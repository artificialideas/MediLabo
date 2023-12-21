import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environment/environment';
 
@Injectable()
export class AuthService {
  public createHeaders(): HttpHeaders {
    const credentials = btoa(`${environment.auth.user}:${environment.auth.pwd}`);
    return new HttpHeaders().set('Authorization', `Basic ${credentials}`);
  }
}