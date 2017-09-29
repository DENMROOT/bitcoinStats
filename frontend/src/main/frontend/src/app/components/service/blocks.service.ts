import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import {ChartData} from "../charts/charts.component";
import {Observable} from "rxjs";

@Injectable()
export class BlocksService {
  constructor (
    private http: Http
  ) {}

  getBlocks() :Observable<ChartData> {
    return this.http.get(`/api/stats`)
      .map((res:Response) => res.json());
  }

  // getBlocks() :Observable<ChartData> {
  //   return this.http.get(`/api/blocks`)
  //     .map(this.extractData);
  // }
  //
  // private extractData(res: Response) {
  //   let body = res.json();
  //   console.log("Body Data = " + body.data);
  //   return body.data || {};
  // }

}
