import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { RezeptDetailPage } from './rezept-detail';

@NgModule({
  declarations: [
    RezeptDetailPage,
  ],
  imports: [
    IonicPageModule.forChild(RezeptDetailPage),
  ],
})
export class RezeptDetailPageModule {}
