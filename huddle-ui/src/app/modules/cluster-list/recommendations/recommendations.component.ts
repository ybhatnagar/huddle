import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ClusterListService } from '../cluster-list.service';

@Component({
  moduleId: module.id,
  selector: 'recommendations',
  templateUrl: 'recommendations.component.html',
  styleUrls: ['recommendations.component.scss']
})
export class RecommendationsComponent {
  public selections = [];
  public recommendations = {};
  public chartBefore = {};
  public chartAfter = {};
  constructor(private readonly _service: ClusterListService,
    private readonly router: Router) { };

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.selections = this._service.getSelections();
    console.log(this.selections);
    this.loadRecommendations();
  }

  loadRecommendations() {
    this._service.getRecommendations(this.selections).subscribe((recom) => {
      this.recommendations = recom;
    }, (err) => {
      this.router.navigate(['clusters']);
    })
  }


}
