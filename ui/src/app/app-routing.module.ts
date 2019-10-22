import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClusterListComponent } from './modules/cluster-list/cluster-list.component';

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
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
