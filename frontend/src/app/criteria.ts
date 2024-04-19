import {Filter} from "./filter";

export interface Criteria {
  type: string;
  comparison: string;
  value: string;
  filter: Filter;
}
