import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Criteria} from "./criteria";
import {environment} from "../environments/testEnvironment";
@Injectable({
  providedIn: 'root'
})
export class CriteriaService {
  private apiUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }
  public getCriteria(): Observable<Criteria[]> {
    return this.http.get<Criteria[]>(`${this.apiUrl}/api/v1/criteria`);
  }

  public addCriteria(criteria: Criteria[]): Observable<Criteria[]> {
    return this.http.post<Criteria[]>(`${this.apiUrl}/api/v1/criteria/add`, criteria);
  }
}
