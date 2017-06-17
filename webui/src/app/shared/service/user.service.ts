export class UserService {

    private _clickedRecords: string[] = [];


    addRecord(recordId: string) {
        this._clickedRecords.push(recordId);
        console.log(this._clickedRecords);
    }

    get clickedRecords(): string[] {
        return this._clickedRecords;
    }
}