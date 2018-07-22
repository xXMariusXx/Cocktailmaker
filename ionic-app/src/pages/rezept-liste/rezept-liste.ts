import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import {Zutat} from "../../model/zutat";

/**
 * Generated class for the RezeptListe page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
@Component({
  selector: 'page-rezept-liste',
  templateUrl: 'rezept-liste.html',
})
export class RezeptListe {
  zutatenToShow: Zutat[];

  constructor(public navParams: NavParams) {
    console.log(navParams);
    this.zutatenToShow = navParams.get("zutaten");
  }
}
