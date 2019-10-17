import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class ClusterListService {
  constructor(private readonly http: HttpClient) { }
  private readonly baseUrl = './assets/json';
  private clusterSelections = [];

  public getClusters(): Observable<any> {
    return this.http.get(`${this.baseUrl}/cluster-list.json`);
  }
  public getRecommendations(selections): Observable<any> {
    return this.http.get(`${this.baseUrl}/cluster-recommendations.json`, selections);
    // return this.http.post(`${this.baseUrl}/cluster-recommendations.json`, selections);
  }

  public holdSelections(clusterListSelection) {
    this.clusterSelections = clusterListSelection;
  }
  public getSelections() {
    return this.clusterSelections;
  }
}