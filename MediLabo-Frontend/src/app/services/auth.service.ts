import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
 
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public gatewayUrl: string = "http://localhost:9000";

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  public createCredentials(username: string, password: string): string {
    return btoa(`${username}:${password}`);
  }

  public createHeaders(): HttpHeaders {
    const credentials = this.createCredentials(environment.auth.user, environment.auth.pwd);
    return new HttpHeaders().set('Authorization', `Basic ${credentials}`);
  }

  public getAccess(credentials: any): Observable<HttpResponse<boolean>> {
    return this.http.post<boolean>(this.gatewayUrl, credentials, { observe: 'response'});
  }

  public logout() {
    sessionStorage.setItem('userData', '');
    this.router.navigate(['/']);
  }
}