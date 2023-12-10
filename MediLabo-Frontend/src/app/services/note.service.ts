import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Note } from '../models/note.model';

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  public gatewayUrl: string = "http://localhost:9000/notes";

  constructor(
    private http: HttpClient
  ) {}

  public findNote(patId: string): Observable<HttpResponse<Note>> {
    return this.http.get<Note>(`${this.gatewayUrl}/${patId}`, { observe: 'response'});
  }

  public add(note: Note): Observable<HttpResponse<Note>> {
    return this.http.post<Note>(`${this.gatewayUrl}/add`, note, { observe: 'response' });
  }
}
