import { TestBed } from '@angular/core/testing';
import { AssessmentService } from './assessment.service';

describe('NotesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AssessmentService = TestBed.get(AssessmentService);
    expect(service).toBeTruthy();
  });
});