import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Assessment } from "../models/assessment.model";
import { AuthService } from "./auth.service";

@Injectable({
    providedIn: 'root'
})
export class AssessmentService {
    public gatewayUrl: string = "http://localhost:9000/assessments";

    constructor(
      private http: HttpClient,
      private authService: AuthService
    ) {}

    public findRisk(patId: string): Observable<HttpResponse<Assessment>> {
        return this.http.get<Assessment>(`${this.gatewayUrl}/${patId}`, { headers: this.authService.createHeaders(), observe: 'response'});
    }
}
