import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Note } from '../models/note.model';

@Injectable({
  providedIn: 'root'
})
export class NotesService {
  public gatewayUrl: string = "http://localhost:9000/notes";

  constructor(
    private http: HttpClient
  ) {}

  public findAll(): Observable<HttpResponse<Note[]>> {
    return this.http.get<Note[]>(`${this.gatewayUrl}/`, { observe: 'response'});
  }

  public findNote(patId: string): Observable<HttpResponse<Note>> {
    return this.http.get<Note>(`${this.gatewayUrl}/${patId}`, { observe: 'response'});
  }

  public add(note: Note): Observable<HttpResponse<Note>> {
    return this.http.post<Note>(`${this.gatewayUrl}/add`, note, { observe: 'response' });
  }

  public update(id: string, note: Note): Observable<HttpResponse<Note>> {
    return this.http.put<Note>(`${this.gatewayUrl}/${id}`, note, { observe: 'response' });
  }

  public delete(id: string): Observable<HttpResponse<Note[]>> {
    return this.http.delete<Note[]>(`${this.gatewayUrl}/${id}`, { observe: 'response' });
  }
}
