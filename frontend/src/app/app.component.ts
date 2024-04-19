import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Criteria} from "./criteria";
import {CriteriaService} from "./criteria.service";
import {HttpClientModule, HttpErrorResponse} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {FormComponent} from "./form/form.component";
import {Filter} from "./filter";
import {FilterService} from "./filter.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpClientModule, CommonModule,FormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  public criteria: Criteria[] = [];
  public filterList: Filter[] = [];
  constructor(private criteriaService: CriteriaService, private filterService: FilterService) { }

  ngOnInit() {
    this.getCriteria();
    this.getFilters();
  }

  public nonModal(boolean: boolean) {
    if (boolean) {
      document.getElementById("filterModal")!.classList.add("modeless");
    } else {
      document.getElementById("filterModal")!.classList.remove("modeless");
    }
  }

  public getFilters(): void {
    this.filterService.getFilters().subscribe(
      (response: Filter[]) => {
        this.filterList = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getCriteria(): void {
    this.criteriaService.getCriteria().subscribe(
      (response: Criteria[]) => {
        this.criteria = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
    }
    );
  }
}
