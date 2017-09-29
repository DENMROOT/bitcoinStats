import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { ChartsModule } from 'ng2-charts';

import { AppComponent } from './app.component';
import { LineChartDemoComponent } from './components/charts/charts.component'
import { BlocksService } from "./components/service/blocks.service";

@NgModule({
  declarations: [
    AppComponent,
    LineChartDemoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ChartsModule
  ],
  providers: [BlocksService],
  bootstrap: [AppComponent]
})
export class AppModule { }
