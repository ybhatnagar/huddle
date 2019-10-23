// Angular Imports
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ClarityModule } from '@clr/angular';
import { SharedModule } from '../shared/shared.modules';
// This Module's Components
import { ClusterListComponent } from './cluster-list.component';
import { ClusterListService } from './cluster-list.service';
import { ConnectionChartComponent } from './connection-chart/connection-chart.component';
import { LatencyChartComponent } from './latency-chart/latency-chart.component';
import { RecommendationsComponent } from './recommendations/recommendations.component';

@NgModule({
  imports: [
    FormsModule,
    SharedModule,
    ClarityModule,
    CommonModule
  ],
  declarations: [
    ClusterListComponent,
    RecommendationsComponent,
    ConnectionChartComponent,
    LatencyChartComponent
  ],
  exports: [
    ClusterListComponent,
    RecommendationsComponent,
    ConnectionChartComponent,
    LatencyChartComponent
  ],
  providers: [
    ClusterListService
  ]
})
export class ClusterListModule {

}
