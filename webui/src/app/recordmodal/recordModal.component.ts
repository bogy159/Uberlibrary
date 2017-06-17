import { Component, OnInit } from '@angular/core';
import {Record} from "../record/record";
import {DialogComponent, DialogService} from "ng2-bootstrap-modal";

export interface RecordModal {
    record: Record;
    recommendations: Record[];
    secondRangRecommendations: Record[];
}

@Component({
  selector: 'app-recordModal',
  templateUrl: './recordModal.component.html',
  styleUrls: ['./recordModal.component.css']
})
export class RecordModalComponent extends DialogComponent<RecordModal, boolean> implements RecordModal {
    record: Record;
    recommendations: Record[];
    secondRangRecommendations: Record[];

    constructor(dialogService: DialogService) {
        super(dialogService);
    }

    datestamp() {
        return new Date(this.record.datestamp);
    }
}
