import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClusterListComponent } from './modules/cluster-list/cluster-list.component';
import { RecommendationsComponent } from './modules/cluster-list/recommendations/recommendations.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        redirectTo: '/clusters',
        pathMatch: 'full'
      },
      {
        path: 'clusters',
        component: ClusterListComponent
      },
      {
        path: 'recommendations',
        component: RecommendationsComponent
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
