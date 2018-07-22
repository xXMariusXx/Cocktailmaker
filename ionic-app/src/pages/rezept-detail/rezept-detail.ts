import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {Rezepte} from "../../model/rezepte";

/**
 * Generated class for the RezeptDetailPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-rezept-detail',
  templateUrl: 'rezept-detail.html',
})
export class RezeptDetailPage {
  rezept: Rezepte;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.rezept = navParams.get("rezept")
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad RezeptDetailPage');
  }

}
