import { Component } from '@angular/core';
import {RecordService} from "./shared/service/record.service";
import {RecommenderService} from "./shared/service/recommender.service";
import {UserService} from "./shared/service/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [
      RecordService,
      RecommenderService,
      UserService
  ]
})
export class AppComponent {
}
