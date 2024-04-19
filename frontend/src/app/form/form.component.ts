import {Component, inject} from '@angular/core';
import {FormArray, FormBuilder, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterOutlet} from "@angular/router";
import {rowService} from "./row.service";
import {CriteriaService} from "../criteria.service"
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {provideNativeDateAdapter} from "@angular/material/core";
import {FilterService} from "../filter.service";
import {Filter} from "../filter";
import {Criteria} from "../criteria";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterOutlet, MatFormFieldModule, MatInputModule, MatDatepickerModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
  providers: [rowService,FilterService, CriteriaService, provideNativeDateAdapter()]
})
export class FormComponent {
  private fb = inject(FormBuilder).nonNullable;
  private rowService = inject(rowService);
  private criteriaService = inject(CriteriaService);
  private filterService = inject(FilterService);
  public showDatePicker: boolean[] = [false];
  //@TODO validation
  public filterForm = this.fb.group({
    filterInfo: this.fb.group({
      name: '',
    }),
    criteria: this.fb.array([
      this.fb.group({
        type: 'Amount',
        comparison: 'Higher',
        value: '0',
      }),
    ]),
  });

  constructor() {
    this.getTypes();
    this.onChangeType(0)
  }

  get criteriaGroups(): FormArray {
    return this.filterForm.get('criteria') as FormArray;
  }

  public types = <any>[]
  public comparisons = <any[]>[]
  public getTypes(): void {
    this.rowService.getType().subscribe(
      data => {
        this.types = data;
      },
      error => {
        console.log(error);
      }
    )
  }

  public addCriteria(): void {
    this.criteriaGroups.push(
      this.fb.group({
        type: 'Amount',
        comparison: 'Higher',
        value: '0',
      })
    );
    this.onChangeType(this.criteriaGroups.length - 1)
  }

  public onChangeType(i: number): void {
    this.rowService
      .getComparison((this.criteriaGroups.at(i).value).type)
      ?.subscribe(
        data => {
          this.comparisons[i] = data;
        },
        error => {
          console.log(error);
        }
      );
    this.showDatePicker[i] = this.criteriaGroups.at(i).value.type === "Date";
    this.updateFilterForm(this.criteriaGroups.at(i).value.type, i)
  }

  private updateFilterForm(type: string, i: number): void {
    switch (type) {
      case "Amount": {
        this.criteriaGroups.at(i).patchValue({
          type: "Amount",
          comparison: "Higher",
          value: "0",
        })
        break;
      }
      case "Title": {
        this.criteriaGroups.at(i).patchValue({
          type: "Title",
          comparison: "Starts with",
          value: "Text",
        })
        break;
      }
      case "Date": {
        this.criteriaGroups.at(i).patchValue({
          type: "Date",
          comparison: "Until",
          value: "01/01/2024",
        })
        break;
      }
      default: {
        break;
      }
    }
  }

  public removeCriteria(index: number): void {
    //@TODO Fix removeCriteria (indexes inside ngFor are weird add unique identifier and remove by that)
    if (this.criteriaGroups.length > 1) {
      this.criteriaGroups.removeAt(index);
    }
  }

  public resetForm(): void {
    //@TODO if top type is title or date before closing doesn't change comparison
    //@TODO if closed by clicking away resetForm()
    this.filterForm = this.fb.group({
      filterInfo: this.fb.group({
        name: '',
      }),
      criteria: this.fb.array([
        this.fb.group({
          type: 'Amount',
          comparison: 'Higher',
          value: '0',
        }),
      ]),
    });
    this.updateFilterForm("Amount", 0);
  }

  private formCriteria(filter: Filter): Criteria[] {
    let criteriaList: Criteria[] = [];
    const criteria = this.filterForm.get('criteria')?.value;
    if (criteria?.length) {
      for (let i=0; i<criteria.length;i++) {
        criteriaList.push({type: criteria[i].type, comparison: criteria[i].comparison, value: criteria[i].value, filter: filter})
      }
    }
    return criteriaList;
  }

  public postFilter(): void {
    this.filterService.addFilter(this.filterForm.get('filterInfo')?.value as Filter).subscribe(
      (response: Filter) => {
        console.log(response);
        this.postCriteria(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public postCriteria(filter: Filter): void {
    const criteria: Criteria[] = this.formCriteria(filter);
    this.criteriaService.addCriteria(criteria).subscribe(
      (response: Criteria[]) => {
        console.log(response)
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
    this.resetForm();
    this.getTypes();
    document.getElementById("close-button")!.click();
    window.location.reload();
  }
}
