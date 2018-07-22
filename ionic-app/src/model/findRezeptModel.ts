import {Zutat} from "./zutat";

export enum searchType {
  Exclusuve,
  Subset
}
export interface findRezeptModel{
  searchtype: searchType;
  zutaten: Zutat[];
}
