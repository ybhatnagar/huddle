import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ClusterListService } from './cluster-list.service';

@Component({
  selector: 'cluster-list',
  templateUrl: 'cluster-list.component.html',
  styleUrls: ['cluster-list.component.scss']
})
export class ClusterListComponent {
  public clusterList = [];
  constructor(private readonly _service: ClusterListService,
    private readonly router: Router) { };

  ngOnInit(): void {
    this.loadClusters();
  }

  loadClusters() {
    this._service.getClusters().subscribe((cl) => {
      this.clusterList = cl.cluster;
    }, (err) => {
      console.error(err);
    })
  }

  isSelected() {
    return this.clusterList.some((cl) => {
      return cl.podsSelected && cl.podsSelected.length;
    })
  }

  onAnalyise() {
    let selection = [];
    this.clusterList.forEach(element => {
      if (element.podsSelected && element.podsSelected.length) {
        selection.push({
          clusterName: element.clusterName,
          pods: element.podsSelected
        })
      }
    });
    this._service.holdSelections(selection);
    this.router.navigate(['/recommendations'])
  }
}
