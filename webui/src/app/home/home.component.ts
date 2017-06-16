import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { RecordService } from "../shared/service/record.service";
import { Record } from "../record/record";
import {DialogService} from "ng2-bootstrap-modal";
import {RecordModalComponent} from "../recordmodal/recordModal.component";
import {RecommenderService} from "../shared/service/recommender.service";
import {UserService} from "../shared/service/user.service";
import 'rxjs/Rx';
import {isUndefined} from "util";

@Component({
    selector: 'appHome',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    private records: Record[] = [];
    private workingRecords: Record[] = [];

    constructor(
        private recordService: RecordService,
        private recommenderService: RecommenderService,
        private userService: UserService,
        private dialogService: DialogService
    ) {}

    ngOnInit() {
        this.recordService.getRecords().subscribe(res => {this.records = res; this.workingRecords = res});
    }

    filterRecords(string: String) {
        let s = string.toLowerCase();
        this.workingRecords = this.records.filter(
            r => r.title.toLowerCase().indexOf(s) >= 0 ||
            r.subject.toLowerCase().indexOf(s) >= 0 ||
            r.creator.toLowerCase().indexOf(s) >= 0
        );
    }

    openRecordModal(r: Record) {
        let recommendations: Record[] = [];
        this.userService.addRecord(r.identifier);
        this.recordService.getReviews(r.identifier).subscribe(res => {r.reviews = res});
        this.recommenderService.getRecommendations(r.identifier).subscribe(res => {
            for (let key in res) {
                let record = this.records.filter(r => r.identifier === key).pop();
                if (record != null && !isUndefined(record)) {
                    recommendations.push(record);
                }
            }
        });

        console.log(recommendations);
        this.dialogService.addDialog(RecordModalComponent, {record: r, recommendations: recommendations});
    }

}
