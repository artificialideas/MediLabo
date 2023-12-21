import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Note } from '../models/note.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  public gatewayUrl: string = "http://localhost:9000/api/notes";

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  public findNote(patId: string): Observable<HttpResponse<Note>> {
    return this.http.get<Note>(`${this.gatewayUrl}/${patId}`, { headers: this.authService.createHeaders(), observe: 'response'});
  }

  public add(note: Note): Observable<HttpResponse<Note>> {
    return this.http.post<Note>(`${this.gatewayUrl}/add`, note, { headers: this.authService.createHeaders(), observe: 'response' });
  }
}
