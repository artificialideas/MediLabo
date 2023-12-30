import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
 
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public gatewayUrl: string = "http://localhost:9000";
  public username: string = "";
  public password: string = "";

  constructor(
    private router: Router
  ) {}

  public createCredentials(): string {
    return btoa(`${this.username}:${this.password}`);
  }

  public createHeaders(): HttpHeaders {
    const credentials = this.createCredentials();
    return new HttpHeaders().set('Authorization', `Basic ${credentials}`);
  }

  public logout() {
    this.username = "";
    this.password = "";
    this.router.navigate(
      ['/']
    );
  }
}