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

}
