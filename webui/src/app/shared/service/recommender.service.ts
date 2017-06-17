import {Injectable} from "@angular/core";
import { Http, Response } from '@angular/http';
import 'rxjs/Rx';
import 'rxjs/add/operator/map'
import {HttpUtil} from "../util/http.util";

@Injectable()
export class RecommenderService {

    constructor(private http: Http) {}

    getRecommendations(id: string) {
        return this.http
            .get('http://localhost:5000/get/'.concat(id).concat("/6"), {headers: HttpUtil.getHeaders()})
            .map((response: Response) => response.json())
    }
}