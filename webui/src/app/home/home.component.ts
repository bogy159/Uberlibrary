import { Component, OnInit } from '@angular/core';
import {RecordService} from "../shared/record.service";
import {Record} from "../record/record";

@Component({
  selector: 'appHome',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private records: Record[] = [];
  private workingRecords: Record[] = [];

  constructor(private recordService: RecordService) {}

  ngOnInit() {
    this.recordService.getRecords().subscribe(res => this.records = res);
    this.workingRecords = this.records;
  }

}
