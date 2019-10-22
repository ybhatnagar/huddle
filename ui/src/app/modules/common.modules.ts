// Angular Imports
import { NgModule } from '@angular/core';
import { ClusterListModule } from './cluster-list/cluster-list.module';
import { SharedModule } from './shared/shared.modules';

@NgModule({
  imports: [
    SharedModule,
    ClusterListModule
  ],
  declarations: [
  ],
  exports: [
  ]
})
export class CommonHuddleModule {

}
