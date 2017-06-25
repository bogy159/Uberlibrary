import { Component, OnInit } from '@angular/core';
import {Record} from "../record/record";
import {DialogComponent, DialogService} from "ng2-bootstrap-modal";
import {Review} from "../review/review";
import {RecordService} from "../shared/service/record.service";
import { UUID } from 'angular2-uuid';

export interface RecordModal {
    record: Record;
    recommendations: Record[];
    secondRangRecommendations: Record[];
    reviews: Review[];
    recordService: RecordService;
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
    reviews: Review[];
    recordService: RecordService;

    formActive: Boolean;
    reviewSent: Boolean;

    ratingValue: number = 0;
    titleValue: string = "";
    bodyValue: string = "";
    nameValue: string = "";

    constructor(dialogService: DialogService) {
        super(dialogService);
        this.formActive = false;
        this.reviewSent = false;

    }

    datestamp() {
        return new Date(this.record.datestamp);
    }

    enableForm() {
        this.formActive = true;
    }

    submit() {
        this.formActive = false;
        this.reviewSent = true;

        this.recordService.postReview(
            this.record.identifier,
            <Review>({
                id: UUID.UUID(),
                datestamp: new Date(),
                user: this.nameValue,
                title: this.titleValue,
                body: this.bodyValue,
                rating: this.ratingValue
            })
        ).subscribe();

    }
}
