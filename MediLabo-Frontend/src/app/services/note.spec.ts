import { TestBed } from '@angular/core/testing';
import { NoteService } from 'src/app/services/note.service';

describe('NotesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NoteService = TestBed.get(NoteService);
    expect(service).toBeTruthy();
  });
});