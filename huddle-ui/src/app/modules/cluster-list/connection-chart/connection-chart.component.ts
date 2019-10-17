import { Component, Input } from '@angular/core';

@Component({
    selector: 'connection-chart',
    templateUrl: 'connection-chart.component.html',
    styleUrls: ['connection-chart.component.scss']
})
export class ConnectionChartComponent {
    @Input() chartData;
}
