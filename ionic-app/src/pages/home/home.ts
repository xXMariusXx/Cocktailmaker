import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { RezeptListe } from "../rezept-liste/rezept-liste.component";
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import {Zutaten} from "../../model/zutaten";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {
  zutaten: Zutaten[] = [];
  result : Observable<any>;
  zutatentoShow : Zutaten[] = [];
  constructor(public navCtrl: NavController, public httpClient: HttpClient) {
    this.result = this.httpClient.get('http://localhost:8080/zutaten');
    this.result
      .subscribe(data => {
        this.zutatentoShow = this.zutaten = data;

        console.log('my data: ', data);})}
    //this.initializeZutaten(this.zutaten);


  initializeZutaten() {
    //this.zutaten = 0;
  }

  getItems(ev: any) {
    // Reset items back to all of the items
    //this.initializeZutaten();

    // set val to the value of the searchbar
    const searchString = ev.target.value;

    // if the value is an empty string don't filter the items
    if (searchString &&  searchString.trim()  != '') {
      this.zutatentoShow = this.zutaten.filter((zutat) => {
        return (zutat.name.toLowerCase().indexOf(searchString.toLowerCase()) > -1);
      });
    }else if(searchString.trim()  == '') this.zutatentoShow = this.zutaten;
  }

  presentActionSheet(ev: any)
  {
    this.navCtrl.push(RezeptListe, {"zutaten": this.zutaten});

  }

}
