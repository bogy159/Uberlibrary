import { Component, OnInit } from '@angular/core';
import {CloseGuard, DialogRef, ModalComponent} from "angular2-modal";
import {Record} from "../record/record";

export class RecordModalContext {
  public record: Record;
}

@Component({
  selector: 'app-recordModal',
  templateUrl: './recordModal.component.html',
  styleUrls: ['./recordModal.component.css']
})
export class RecordModalComponent implements CloseGuard, ModalComponent<RecordModalContext> {
  context: RecordModalContext;

  constructor(public dialog: DialogRef<RecordModalContext>) {
    this.context = dialog.context;
  }
}
