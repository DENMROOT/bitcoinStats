import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
// import {Block} from "../charts/charts.component";

@Injectable()
export class BlocksService {
  constructor (
    private http: Http
  ) {}

  getBlocks() {
    return this.http.get(`/api/blocks`)
      .map((res:Response) => res.json());
  }

}
