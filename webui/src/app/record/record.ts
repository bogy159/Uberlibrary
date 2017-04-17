import {Review} from "../review/review";

export interface Record {
    identifier: string;
    datestamp: Date;
    specs: string[];
    reviews: Review[];
    title: string;
    subject: string;
    creator: string;
    publisher: string;
    date: string;
    language: string;
    rights: string;
    coverage: string[];
    description: string[];
    type: string[];
    format: string[];
    identifierList: string[];
}
