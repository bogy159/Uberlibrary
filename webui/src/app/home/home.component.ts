import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { RecordService } from "../shared/record.service";
import { Record } from "../record/record";
import { ModalModule, OverlayRenderer, DOMOverlayRenderer, Overlay, overlayConfigFactory } from 'angular2-modal';
import { Modal, BSModalContext, BootstrapModalModule } from "angular2-modal/plugins/bootstrap";
import { RecordModalComponent } from "../recordmodal/recordModal.component";

const MODAL_PROVIDERS = [
    Modal,
    Overlay,
    { provide: OverlayRenderer, useClass: DOMOverlayRenderer }
];

@Component({
    selector: 'appHome',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
    providers: [MODAL_PROVIDERS]
})
export class HomeComponent implements OnInit {

    private records: Record[] = [];
    private workingRecords: Record[] = [];

    constructor(private recordService: RecordService, vcRef: ViewContainerRef, public modal: Modal) {
        modal.overlay.defaultViewContainer = vcRef;
    }

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
        this.recordService.getReviews(r.identifier).subscribe(res => {r.reviews = res});
        return this.modal.open(RecordModalComponent, overlayConfigFactory({record: r}));
    }

}
