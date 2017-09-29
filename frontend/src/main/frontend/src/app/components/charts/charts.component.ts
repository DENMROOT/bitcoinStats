import {Component} from '@angular/core';
import {BlocksService} from '../service/blocks.service'

@Component({
  selector: 'line-chart-demo',
  templateUrl: './charts.component.html'
})

export class LineChartDemoComponent {
  constructor(private blocksService: BlocksService) {
  }

  // lineChart
  public lineChartData: Array<any> = [
    {data: [10, 20, 10, 20, 10, 20, 10], label: 'Series A'}
  ];
  public lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
  public lineChartOptions: any = {
    responsive: true
  };
  public lineChartColors: Array<any> = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#ffb97e',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLegend: boolean = true;
  public lineChartType: string = 'bar';

  blocks: BlockWrapper;

  public loadBlocksData():void {
    this.blocksService.getBlocks().subscribe(data => this.blocks = JSON.parse(data));

    let _lineChartLabels :Array<any> = new Array(100);
    let blocks = this.blocks;

    for (let i = 0; i < _lineChartLabels.length; i++) {
      _lineChartLabels[i] = blocks;
    }

    this.lineChartLabels = _lineChartLabels;

    // this.lineChartData = [{data: [10, 20, 30, 40, 50, 60, 50], label: 'Series XXX'}]
  }

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }
}

export interface BlockWrapper {
  blocks: Block[]
}

export interface Block {
  hash: string;
  height: number;
  miningTime: number;
}
