package co.edureka.edurekamay12session;

import android.net.Uri;

public class Util {

    // Data about Database -> SQLite
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Customers.db";

    // Data about Table
    public static final String TAB_NAME = "Customers";

    public static final String COL_ID = "_ID"; // Primary Key
    public static final String COL_NAME = "NAME";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_EMAIL = "EMAIL";

    public static final String CREATE_TAB_QUERY = "create table Customers(" +
            "_ID integer primary key autoincrement," +
            "NAME varchar(256),"  +
            "EMAIL varchar(256)," +
            "PHONE varchar(256)"  +
            " )";

    public static final Uri CUSTOMER_URI = Uri.parse("content://co.edureka.edurekamay12session.mycontentprvider/"+TAB_NAME);


}
