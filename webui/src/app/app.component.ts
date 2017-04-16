import { Component } from '@angular/core';
import {RecordService} from "./shared/record.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [RecordService]
})
export class AppComponent {
}
