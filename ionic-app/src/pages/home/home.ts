import { Component } from '@angular/core';
import {NavController} from 'ionic-angular';
import {RezeptListe} from "../rezept-liste/rezept-liste.component";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  zutaten: string[];

  constructor(public navCtrl: NavController) {
    this.initializeZutaten();
  }

  initializeZutaten() {
    this.zutaten = ['Vodka', 'Saft', 'wasser'];
  }

  getItems(ev: any) {
    // Reset items back to all of the items
    this.initializeZutaten();

    // set val to the value of the searchbar
    const searchString = ev.target.value;

    // if the value is an empty string don't filter the items
    if (searchString && searchString.trim() != '') {
      this.zutaten = this.zutaten.filter((zutat) => {
        return (zutat.toLowerCase().indexOf(searchString.toLowerCase()) > -1);
      })
    }
  }

  presentActionSheet(ev: any)
  {
    this.navCtrl.push(RezeptListe, {"zutaten": this.zutaten});

  }

  isMarked()
  {

  }
}
