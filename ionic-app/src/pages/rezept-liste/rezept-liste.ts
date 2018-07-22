import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import {Zutat} from "../../model/zutat";
import {HttpClient} from "@angular/common/http";
import {Rezepte} from "../../model/rezepte";

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
  gefundeneRezepte:Rezepte[] = [];
  constructor(public navParams: NavParams, public httpClient: HttpClient) {
    console.log(navParams);
    this.zutatenToShow = navParams.get("zutaten");
    httpClient.post("http://localhost:8080/rezept/find", this.zutatenToShow).subscribe( (data:Rezepte[])=> {
      this.gefundeneRezepte = this.gefundeneRezepte.concat(data);
    });
  }
}
