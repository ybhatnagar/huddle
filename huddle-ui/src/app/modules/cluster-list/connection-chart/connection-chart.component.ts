import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import * as Highcharts from 'highcharts';
import { ClusterListService } from '../cluster-list.service';


declare var require: any;
let Boost = require('highcharts/modules/boost');
let noData = require('highcharts/modules/no-data-to-display');
let More = require('highcharts/highcharts-more');

Boost(Highcharts);
noData(Highcharts);
More(Highcharts);
noData(Highcharts);

@Component({
  selector: 'connection-chart',
  templateUrl: 'connection-chart.component.html',
  styleUrls: ['connection-chart.component.scss']
})
export class ConnectionChartComponent {
  @Input() chartData;
  @Input() allowDrag = false;
  @ViewChild('chartEl', { static: false }) chartElement: ElementRef;
  private options;
  private seriesData;

  public loadingGraph = false;

  constructor(private readonly _service: ClusterListService) { };

  ngOnInit() {
    this.loadingGraph = true;
    this._service.getChartData().subscribe((data) => {
      this.seriesData = data;
      setTimeout(() => {
        this.loadGraph();
        this.loadingGraph = false;
      }, 1000)
    })

  }

  loadGraph() {
    if (!this.chartElement) {
      return;
    }
    this.options = {
      chart: {
        renderTo: this.chartElement.nativeElement,
        type: 'packedbubble',
        backgroundColor: 'transparent',
        height: '100%'
      },
      credits: {
        enabled: false
      },
      legend: {
        enabled: false
      },
      colors: ['#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE',
        '#DB843D', '#92A8CD', '#A47D7C', '#B5CA92'],
      title: {
        text: null
      },
      tooltip: {
        useHTML: true,
        pointFormat: '<b>{point.name}:</b> {point.value}'
      },
      plotOptions: {
        packedbubble: {
          minSize: '20%',
          maxSize: '100%',
          zMin: 0,
          zMax: 1000,
          layoutAlgorithm: {
            gravitationalConstant: 0.05,
            splitSeries: true,
            seriesInteraction: false,
            dragBetweenSeries: this.allowDrag,
            parentNodeLimit: true,
            parentNodeOptions: {
              bubblePadding: 30
            }
          },
          dataLabels: {
            enabled: true,
            format: '{point.name}',
            parentNodeFormat: '{point.series.name}',
            // filter: {
            //   property: 'y',
            //   operator: '>',
            //   value: 250
            // },
            style: {
              color: '#333',
              textOutline: 'none',
              fontWeight: 'normal'
            }
          }
        }
      },
      series: this.seriesData
    };
    new Highcharts.Chart(this.options);
  }
}
