import { Injectable } from "@angular/core";
import { of } from "rxjs";

@Injectable()
export class rowService {
  constructor() {}

  public getType() {
    return of([
      {id: 1, type: "Amount"},
      {id: 2, type: "Title"},
      {id: 3, type: "Date"},
    ]);
  }

  public getComparison(type: string) {
    switch (type) {
      case "Amount": {
        return of([
          {id: 1, comparison: "Higher"},
          {id: 2, comparison: "Lower"},
          {id: 3, comparison: "Equal"}
        ]);
      }
      case "Title": {
        return of([
          {id: 4, comparison: "Starts with"},
          {id: 5, comparison: "Ends with"},
          {id: 6, comparison: "Contains"},
        ]);
      }
      case "Date": {
        return of([
          {id: 7, comparison: "Until"},
          {id: 8, comparison: "From"},
          {id: 9, comparison: "At"},
        ]);
      }
      default: {
        return null;
      }
    }
  }
}
