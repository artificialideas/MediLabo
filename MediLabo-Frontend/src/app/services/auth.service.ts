import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environment/environment';
 
@Injectable()
export class AuthService {
  public gatewayUrl: string = "http://localhost:9000";

  constructor(
    private router: Router
  ) {}

  public createCredentials(username: string, password: string): string {
    return btoa(`${username}:${password}`);
  }

  public createHeaders(): HttpHeaders {
    const credentials = this.createCredentials(environment.auth.user, environment.auth.pwd);
    return new HttpHeaders().set('Authorization', `Basic ${credentials}`);
  }

  public logout() {
    sessionStorage.removeItem('medilaboData');
    this.router.navigate(['/login']);
  }
}