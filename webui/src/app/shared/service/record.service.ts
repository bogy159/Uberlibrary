import {Injectable} from "@angular/core";
import { Http, Response } from '@angular/http';
import 'rxjs/Rx';
import {Record} from "../../record/record";
import {Review} from "../../review/review";
import {HttpUtil} from "../util/http.util";
import {isUndefined} from "util";

@Injectable()
export class RecordService {
    constructor(private http: Http) {}

    getRecords() {
        return this.http
            .get('http://develop:8080/record/all', {headers: HttpUtil.getHeaders()})
            .map((response: Response) => this.mapRecords(response));
    }

    getReviews(identifier: string) {
        return this.http
            .get('http://develop:8080/review/get/'.concat(identifier), {headers: HttpUtil.getHeaders()})
            .map((response: Response) => this.mapReviews(response))
    }

    postReview(identifier: string, review:Review) {
        console.log(this.createBody(review));

        return this.http
            .put("http://develop:8080/review/add/".concat(identifier), this.createBody(review), {headers: HttpUtil.getHeaders()})
    }

    private createBody(review: Review) {
        return "{\n"
            .concat("\"id\":\"").concat(review.id).concat("\",\n")
            .concat("\"datestamp\":").concat(review.datestamp.getTime().toString()).concat(",\n")
            .concat("\"user\":\"").concat(review.user).concat("\",\n")
            .concat("\"title\":\"").concat(review.title).concat("\",\n")
            .concat("\"body\":\"").concat(review.body).concat("\",\n")
            .concat("\"rating\":").concat(review.rating.toString()).concat("\n}")
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
        try {
        return response.json().map((r: any) => this.toReview(r));
        } catch (e) {
            return [];
        }
    }

    private toReview(r:any) {
        return <Review>({
            id: r.id,
            datestamp: new Date(r.datestamp),
            user: r.user,
            title: r.title,
            body: r.body,
            rating: r.rating
        });
    }
}
