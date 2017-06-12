import {Injectable} from "@angular/core";
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/Rx';
import {Record} from "../record/record";
import {Review} from "../review/review";

@Injectable()
export class RecordService {
    constructor(private http: Http) {}

    getRecords() {
        return this.http
            .get('http://localhost:8080/record/all', {headers: this.getHeaders()})
            .map((response: Response) => this.mapRecords(response));
    }

    getReviews(identifier: string) {
        return this.http
            .get('http://localhost:8080/review/get/'.concat(identifier), {headers: this.getHeaders()})
            .map((response: Response) => this.mapReviews(response))
    }

    private getHeaders() {
        let headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        return headers;
    }

    private mapRecords(response: Response) {
        return response.json().map((r: any) => this.toRecord(r));
    }

    private toRecord(r: any) {
        return <Record>({
            identifier: r.identifier,
            datestamp: r.datestamp,
            specs: r.specs,
            title: r.title,
            subject: r.subject,
            creator: r.creator,
            publisher: r.publisher,
            date: r.date,
            language: r.language,
            rights: r.rights,
            coverage: r.coverage,
            description: r.description,
            type: r.type,
            format: r.format,
            identifierList: r.identifierList,
            reviews: r.reviews
        });
    }

    private mapReviews(response: Response) {
        return [];
    }
}
