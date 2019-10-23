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
  public getChartData(): Observable<any> {
    return this.http.get(`${this.baseUrl}/series-data.json`);
  }
  public getLatencyChartData(): Observable<any> {
    return this.http.get(`${this.baseUrl}/latency-series-data.json`);
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