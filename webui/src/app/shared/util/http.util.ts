import { Headers } from '@angular/http';

export class HttpUtil {

    public static getHeaders() : Headers     {
        let headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        headers.append('Content-Type', 'application/json');
        return headers;
    }
}