export class UserService {

    private _clickedRecords: String[] = [];


    addRecord(recordId: string) {
        this._clickedRecords.push(recordId);
    }

    get clickedRecords(): String[] {
        return this._clickedRecords;
    }

    set clickedRecords(value: String[]) {
        this._clickedRecords = value;
    }
}