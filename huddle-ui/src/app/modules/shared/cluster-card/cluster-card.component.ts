import { Component, Input } from '@angular/core';

@Component({
    selector: 'cluster-card',
    templateUrl: 'cluster-card.component.html',
    styleUrls: ['cluster-card.component.scss']
})
export class ClusterCardComponent {
    @Input() cluster: any = {};
    ngOnInit(): void {
        //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
        //Add 'implements OnInit' to the class.
        this.cluster.podsSelected = this.cluster.podsSelected || [];
    }

    onCardClick() {

    }

    // updateSelectList(pod) {
    //     this.cluster.podsSelected = this.cluster.podsSelected || [];
    //     if (pod.isSelected) {
    //         this.cluster.podsSelected.push(pod);
    //     } else {
    //         let index = this.cluster.podsSelected.findIndex((p) => {
    //             return p.podIp === pod.podIp;
    //         });
    //         this.cluster.podsSelected.splice(index, 1);
    //     }
    // }
}
