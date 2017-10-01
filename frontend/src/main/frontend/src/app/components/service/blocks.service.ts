import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import {ChartData, BlocksData} from "../charts/charts.component";
import {Observable} from "rxjs";

@Injectable()
export class BlocksService {
  constructor (
    private http: Http
  ) {}

  getStats() :Observable<ChartData> {
    return this.http.get(`/api/v1/stats`)
      .map((res:Response) => res.json());
  }

  getBlocks() :Observable<BlocksData> {
    return this.http.get(`/api/v1/blocks`)
      .map((res:Response) => res.json());
  }

}
