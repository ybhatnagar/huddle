// Angular Imports
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.modules';
// This Module's Components
import { ClusterListComponent } from './cluster-list.component';
import { ClusterListService } from './cluster-list.service';
import { ConnectionChartComponent } from './connection-chart/connection-chart.component';
import { RecommendationsComponent } from './recommendations/recommendations.component';

@NgModule({
  imports: [
    SharedModule,
    CommonModule
  ],
  declarations: [
    ClusterListComponent,
    RecommendationsComponent,
    ConnectionChartComponent
  ],
  exports: [
    ClusterListComponent,
    RecommendationsComponent,
    ConnectionChartComponent
  ],
  providers: [
    ClusterListService
  ]
})
export class ClusterListModule {

}
