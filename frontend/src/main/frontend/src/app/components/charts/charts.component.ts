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
    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
    "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
    "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
    "51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
    "61", "62", "63", "64", "65", "66", "67", "68", "69", "70",
    "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
    "81", "82", "83", "84", "85", "86", "87", "88", "89", "90",
    "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"
  ];
  public lineChartOptions: any = {
    responsive: true
  };
  public lineChartColors: Array<any> = [
    { // grey
      backgroundColor: '#ffc699',
      pointHoverBackgroundColor: '#ff8e5e',
      pointHoverBorderColor: '#ff6e45'
    }
  ];
  public lineChartLegend: boolean = true;
  public lineChartType: string = 'bar';

  chartData: ChartData;
  blocks: Block[];

  public loadBlocksData():void {
    this.blocksService.getStats().subscribe(
      (response) => {
        // Init chart data array
        let data:Array<any> = new Array();
        data[0] = {data: response.events, label: 'Mining time % against average'};
        // Init chart labels
        let labels : Array<string> = new Array();
        labels = response.labels;
        // Set the chart data
        this.lineChartData   = data;

        // labels should be dynamically updated but they are not :)
        this.lineChartLabels = labels;

      }, (error) => {
        console.log(error);
      });

    this.blocksService.getBlocks().subscribe(
      (response) => {
        this.blocks = response.data;
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
  labels: string[];
  events: number[];
}

export class BlocksData {
  data: Block[];
}

export class Block {
  hash: string;
  height: number;
  miningTime: number;
  block_time: number;
  arrival_time: number;
  confirmations: number;
  transactions: number;
  miningpool_name: string;
}

