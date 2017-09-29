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
    {data: [], label: 'Series A'}
  ];
  public lineChartLabels: Array<any> = [
    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "11", "12", "13", "4", "5", "6", "7", "8", "9", "10",
    "21", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "31", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "41", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "51", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "61", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "71", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "81", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "91", "2", "3", "4", "5", "6", "7", "8", "9", "10"
  ];
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

  chartData: ChartData;

  public loadBlocksData():void {
    this.blocksService.getBlocks().subscribe(
      (response) => {
        // Init chart data array
        let data:Array<any> = new Array();
        data[0] = {data: response.events, label: 'Events'};
        // Init chart labels
        let labels : Array<any> = new Array();
        labels[0] = response.labels;
        // Set the chart data
        this.lineChartData   = data;
        this.lineChartLabels = labels;

      }, (error) => {
        console.log(error);
      });
  }

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }
}

export class ChartData {
  labels: number[];
  events: number[];
}
