// Angular Imports
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ClarityModule } from '@clr/angular';
import { ClusterCardComponent } from './cluster-card/cluster-card.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ClarityModule
  ],
  declarations: [
    ClusterCardComponent
  ],
  exports: [
    ClusterCardComponent
  ]
})
export class SharedModule {

}
