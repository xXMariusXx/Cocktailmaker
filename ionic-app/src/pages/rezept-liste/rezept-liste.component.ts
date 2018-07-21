import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import {Rezepte} from "../../model/rezepte";
import {findPostModel} from "../../model/findRezept";

@Component({
  selector: 'page-rezept-liste',
  templateUrl: 'rezept-liste.component.html',
})
export class RezeptListe {
  rezepte: Rezepte[] = [];
  result : Observable<any>;
  rezeptetoShow : Rezepte[] = [];

  gewaehlteZutatenWeb : findPostModel[] = [];

  constructor(public navParams: NavParams, public httpClient: HttpClient) {
    this.gewaehlteZutatenWeb = navParams.get("gewaehlteZutaten");

    this.result = this.httpClient.post('http://localhost:8080/rezept/find', this.gewaehlteZutatenWeb);
    this.result
      .subscribe(data => {
        this.rezeptetoShow = this.rezepte = data;

        console.log('my data: ', data);})}


  getItems(ev: any) {
    // Reset items back to all of the items
    //this.initializeZutaten();

    // set val to the value of the searchbar
    const searchString = ev.target.value;

    // if the value is an empty string don't filter the items
    if (searchString &&  searchString.trim()  != '') {
      this.rezeptetoShow = this.rezepte.filter((zutat) => {
        return (zutat.name.toLowerCase().indexOf(searchString.toLowerCase()) > -1);
      });
    }else if(searchString.trim()  == '') this.rezeptetoShow = this.rezepte;
  }

}
