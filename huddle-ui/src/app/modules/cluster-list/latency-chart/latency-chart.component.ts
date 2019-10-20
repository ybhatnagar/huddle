import * as am4core from "@amcharts/amcharts4/core";
// import * as am4charts from "@amcharts/amcharts4/charts";
import * as am4plugins_forceDirected from "@amcharts/amcharts4/plugins/forceDirected";
import { Component, ElementRef, Input, ViewChild } from '@angular/core';

@Component({
  selector: 'latency-chart',
  templateUrl: 'latency-chart.component.html',
  styleUrls: ['latency-chart.component.scss']
})
export class LatencyChartComponent {
  @Input() series = [];
  @ViewChild('latencyChartEl', { static: false }) chartElement: ElementRef;

  ngOnInit() {
    setTimeout(() => {
      this.loadGraph();
    }, 1000)
  }

  loadGraph() {
    // Create chart
    let chart = am4core.create(this.chartElement.nativeElement, am4plugins_forceDirected.ForceDirectedTree);

    // Create series
    var series = chart.series.push(new am4plugins_forceDirected.ForceDirectedSeries());

    // let cluster = '/assets/images/kubernetes.png';
    // let pods = '/assets/images/pods.png';


    // Set data
    series.data = this.series;

    // Set up data fields
    series.dataFields.value = "value";
    series.dataFields.name = "name";
    series.dataFields.id = "id";
    series.dataFields.children = "children";
    series.dataFields.linkWith = "link";
    series.dataFields.color = "color";

    // Add labels
    series.nodes.template.label.text = "{name}";
    // series.nodes.template.label.valign = "bottom";
    series.nodes.template.label.fill = am4core.color("#000");
    // series.nodes.template.label.dy = 10;
    // series.nodes.template.tooltipText = "{name}: [bold]{value}[/]";
    series.fontSize = 10;
    series.linkWithStrength = 0;
    series.minRadius = 15;
    series.maxRadius = 40;
    series.centerStrength = 0.3;

    // Configure circles
    // series.nodes.template.circle.fill = am4core.color("#fff");

    // // Configure icons
    // var icon = series.nodes.template.createChild(am4core.Image);
    // icon.propertyFields.href = "image";
    // icon.horizontalCenter = "middle";
    // icon.verticalCenter = "middle";
    // icon.width = 40;
    // icon.height = 40;
  }
}
