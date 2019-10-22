// Angular Imports
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ClarityModule } from '@clr/angular';
import { SharedModule } from '../shared/shared.modules';
// This Module's Components
import { ClusterListComponent } from './cluster-list.component';
import { ClusterListService } from './cluster-list.service';

@NgModule({
  imports: [
    FormsModule,
    SharedModule,
    ClarityModule,
    CommonModule
  ],
  declarations: [
    ClusterListComponent
  ],
  exports: [
    ClusterListComponent
  ],
  providers: [
    ClusterListService
  ]
})
export class ClusterListModule {

}
