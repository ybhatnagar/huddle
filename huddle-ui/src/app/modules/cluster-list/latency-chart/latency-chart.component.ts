import * as am4core from "@amcharts/amcharts4/core";
// import * as am4charts from "@amcharts/amcharts4/charts";
import * as am4plugins_forceDirected from "@amcharts/amcharts4/plugins/forceDirected";
import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'latency-chart',
  templateUrl: 'latency-chart.component.html',
  styleUrls: ['latency-chart.component.scss']
})
export class LatencyChartComponent {
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

    let cluster = '/assets/images/kubernetes.png';
    let pods = '/assets/images/pods.png';


    // Set data
    series.data = [{
      "name": "Cluster #1",
      "id": "Cluster #1",
      "image": cluster,
      "children": [{
        "name": "pods #1",
        "id": "pods #1",
        "value": 1,
        "image": pods
      }, {
        "name": "pods #2",
        "id": "pods #2",
        "value": 1,
        "image": pods,
        "link": ["pods #4"]
      }, {
        "name": "pods #3",
        "id": "pods #3",
        "value": 1,
        "image": pods,
        "link": ["pods #13"]
      }, {
        "name": "pods #4",
        "id": "pods #4",
        "value": 1,
        "image": pods,
        "link": ["pods #3"]
      }]
    }, {
      "name": "Cluster #12",
      "id": "Cluster #12",
      "image": cluster,
      "children": [{
        "name": "pods #11",
        "id": "pods #11",
        "value": 1,
        "image": pods
      }, {
        "name": "pods #12",
        "id": "pods #12",
        "value": 1,
        "image": pods
      }, {
        "name": "pods #13",
        "id": "pods #13",
        "value": 1,
        "image": pods
      }]
    }];

    // Set up data fields
    series.dataFields.value = "value";
    series.dataFields.name = "name";
    series.dataFields.id = "id";
    series.dataFields.children = "children";
    series.dataFields.linkWith = "link";

    // Add labels
    series.nodes.template.label.text = "{name}";
    series.nodes.template.label.valign = "bottom";
    series.nodes.template.label.fill = am4core.color("#000");
    series.nodes.template.label.dy = 10;
    series.nodes.template.tooltipText = "{name}: [bold]{value}[/]";
    series.fontSize = 10;
    series.minRadius = 30;
    series.maxRadius = 30;

    // Configure circles
    series.nodes.template.circle.fill = am4core.color("#fff");

    // Configure icons
    var icon = series.nodes.template.createChild(am4core.Image);
    icon.propertyFields.href = "image";
    icon.horizontalCenter = "middle";
    icon.verticalCenter = "middle";
    icon.width = 40;
    icon.height = 40;
    series.centerStrength = 0.4;
  }
}
