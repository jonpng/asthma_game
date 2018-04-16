package cs4605.asthmagame;

import java.util.Date;

public class Canister {
    //private variables
    private Date _loginDate;
    private int _canisterCount;

    public Canister(Date loginDate, int canisterCount) {
        this._loginDate = loginDate;
        this._canisterCount = canisterCount;
    }

    public int get_canisterCount() {
        return this._canisterCount;
    }

    public Date get_loginDate() {
        return this._loginDate;
    }
}
