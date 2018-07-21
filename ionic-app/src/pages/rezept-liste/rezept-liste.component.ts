import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';


/**
 * Generated class for the LorenaActionSheetPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
@Component({
  selector: 'page-lorena-action-sheet',
  templateUrl: 'rezept-liste.component.html',

})
export class RezeptListe {


  constructor(private navParams: NavParams) {
    var zutaten = navParams.get("zutaten");
  }

  }


