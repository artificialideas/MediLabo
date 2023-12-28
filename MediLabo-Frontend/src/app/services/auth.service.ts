import { HttpClient, HttpHeaders, HttpResponse, HttpRequest } from '@angular/common/http';
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

  public login(user: any){
    const credentials = this.createCredentials(user.username, user.password);
    const headers = new HttpHeaders().set('Authorization', `Basic ${credentials}`);

    return this.http.get(this.gatewayUrl + "/login" , {headers: headers})
      .map((response: Response) => {
      // login successful if there's a jwt token in the response
      let user = response.json().principal;// the returned user object is a principal object
      if (user) {
        // store user details  in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(user));
      }
    });
  }

  public logout() {
    // remove user from local storage to log user out
    return this.http.post(this.gatewayUrl +"logout",{})
      .map((response: Response) => {
        localStorage.removeItem('currentUser');
      });
  }
}