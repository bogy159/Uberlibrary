import {Injectable} from "@angular/core";
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable
export class RecordService {
        constructor(
            private http: Http
        ) {}

        getRecords() {
            return this.http.get('http://localhost:8080/record/all').map((res: Response) => res.json());
        }
}