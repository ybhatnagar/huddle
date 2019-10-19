import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ClusterListService } from '../cluster-list.service';

@Component({
  selector: 'recommendations',
  templateUrl: 'recommendations.component.html',
  styleUrls: ['recommendations.component.scss']
})
export class RecommendationsComponent {
  public selections = [];
  public recommendations = [{ name: 'DUMMY' }];
  public chartBefore = [];
  public chartAfter = [];
  public openRec = false;
  public recClicked: any;

  constructor(private readonly _service: ClusterListService,
    private readonly router: Router) { };

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.selections = this._service.getSelections();
    console.log(this.selections);
    this.loadRecommendations();
    this.loadChartData();
  }

  loadRecommendations() {
    this._service.getRecommendations(this.selections).subscribe((recom) => {
      this.recommendations = recom;
    }, (err) => {
      this.router.navigate(['clusters']);
    })
  }

  loadChartData() {
    this._service.getChartData().subscribe((data) => {
      this.chartBefore = data;
    })
    this._service.getChartData().subscribe((data) => {
      this.chartAfter = data;
    })
  }

  onRecClick(rec) {
    this.openRec = true;
    this.recClicked = rec;
  }

  onApply(rec) {
    let chart = this.chartAfter;
    this.chartAfter = null;
    let currentNode = chart.find((cluster) => {
      return cluster.name.indexOf(rec.currentNode) > -1;
    })
    let recommendedNode = chart.find((cluster) => {
      return cluster.name.indexOf(rec.recommendedNode) > -1;
    })
    let podIndex = currentNode.data.findIndex(pod => {
      return pod.name === rec.pod;
    });
    let movingPods = currentNode.data.splice(podIndex, 1);
    recommendedNode.data = recommendedNode.data.concat(movingPods);
    setTimeout(() => {
      this.chartAfter = chart;
      rec.applied = true;
    }, 100)
  }
}
