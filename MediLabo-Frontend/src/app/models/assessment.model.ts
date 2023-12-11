export interface IAssessment {
    patId: string;
    status: Risk;
}
  
export class Assessment implements IAssessment {
    constructor(
        public patId: string,
        public status: Risk,
    ) {}
}

enum Risk {
    NONE,
    BORDERLINE,
    DANGER,
    EARLY_ONSET
}