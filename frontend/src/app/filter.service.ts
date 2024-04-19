import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../environments/testEnvironment";
import {Filter} from "./filter";
@Injectable({
  providedIn: 'root'
})
export class FilterService {
  private apiUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }
  public getFilters(): Observable<Filter[]> {
    return this.http.get<Filter[]>(`${this.apiUrl}/api/v1/filter`);
  }

  public addFilter(filter: Filter): Observable<Filter> {
    return this.http.post<Filter>(`${this.apiUrl}/api/v1/filter/add`, filter);
  }
}
