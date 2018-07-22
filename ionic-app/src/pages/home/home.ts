import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { RezeptListe } from "../rezept-liste/rezept-liste";
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import {Zutat} from "../../model/zutat";
import {findRezeptModel, searchType} from "../../model/findRezeptModel";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {
  apiResult : Observable<any>;

  zutaten: Zutat[] = [];
  zutatenToShow : Zutat[] = [];
  zutatenSelected : Zutat[] = [];

  constructor(public navCtrl: NavController, public httpClient: HttpClient) {
    this.apiResult = this.httpClient.get('http://localhost:8080/zutaten');
    this.apiResult.subscribe(data => {
        this.zutatenToShow = this.zutaten = data;
    })
  }

  getItems(ev: any) {
    // set val to the value of the searchbar
    const searchString = ev.target.value;

    // if the value is an empty string don't filter the items
    if (searchString && searchString.trim()  != '') {
      this.zutatenToShow = this.zutaten.filter((zutat) => {
        return (zutat.name.toLowerCase().indexOf(searchString.toLowerCase()) > -1);
      });
    } else if (searchString.trim() == '') this.zutatenToShow = this.zutaten;
  }

  updateChecked(ev: any, zutat: Zutat) {
    if (ev.checked)
      this.zutatenSelected.push(zutat);
    else
      this.removeElement(this.zutatenSelected, zutat)
  }

  presentErgebnisListe(ev: any) {
    this.navCtrl.push(RezeptListe, {"zutaten": <findRezeptModel>{searchtype:searchType.Subset , zutaten: this.zutatenSelected}});
  }

  removeElement<T>(arr: T[] , element: T){
    arr.forEach( (item, index) => {
      if(item === element) arr.splice(index,1);
    });
  }
}
