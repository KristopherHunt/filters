import {Criteria} from "./criteria";

export interface Filter {
  name: string;
  id?: number;
  criteria: Criteria[];
}
