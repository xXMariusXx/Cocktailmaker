import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { HomePage } from '../pages/home/home';
import { TabsPage } from '../pages/tabs/tabs';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { RezeptListe } from "../pages/rezept-liste/rezept-liste";
import { ServiceProvider } from '../providers/service/service';
import { HttpClientModule } from "@angular/common/http";
import {RezeptDetailPage} from "../pages/rezept-detail/rezept-detail";

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    TabsPage,
    RezeptListe,
    RezeptDetailPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpClientModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    TabsPage,
    RezeptListe,
    RezeptDetailPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    ServiceProvider
  ]
})
export class AppModule {}
