package com.example.bango_000.fc;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class LoginDatabase{
	public static final String KEY_ROWID = "Id";
	public static final String KEY_CLASS_ID = "classid";
	public static final String KEY_STUDENT_names = "studentname";
	public static final String KEY_PARENT_phones = "parentphone_N";

	private static final String DATABASE_NAME = "fcmo";
	private static final String DATABASE_TABLE = "school";
	private static final int DATABASE_VERSION = 2;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	ArrayList names_list = new ArrayList();
	ArrayList phones_list = new ArrayList();
	static String[] all_names_list;

	private static class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);}

		@Override
		public void onCreate(SQLiteDatabase db){
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CLASS_ID
					+ " TEXT NOT NULL, " + KEY_STUDENT_names
					+ " TEXT NOT NULL, " + KEY_PARENT_phones 
					+ " TEXT NOT NULL);");}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}}

	public long createEntry(String classid, String name, String phone_number) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_CLASS_ID, classid);
		cv.put(KEY_STUDENT_names, name);
		cv.put(KEY_PARENT_phones, phone_number);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData(){
		String[] columns = new String[] {KEY_ROWID, KEY_CLASS_ID, KEY_STUDENT_names, KEY_PARENT_phones};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,	null, null);

		String result = " ";
		int iRow =    c.getColumnIndex(KEY_ROWID);
		int classid = c.getColumnIndex(KEY_CLASS_ID);
		int name =    c.getColumnIndex(KEY_STUDENT_names);
		int phone =   c.getColumnIndex(KEY_PARENT_phones);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			names_list.add(c.getString(name).toString()); //name in database
			phones_list.add(c.getString(phone).toString()); //phone in database
			//concatenate students info
			result  += c.getString(iRow) + " - " + c.getString(classid) + " - "
					+  c.getString(name) + " - "
					+  c.getString(phone)+ "\n\n";
		}
	return result;}

	public void updateEntry(long lRow, String classid, String name, String phone)
			throws SQLException {
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_CLASS_ID, classid);
		cvUpdate.put(KEY_STUDENT_names, name);
		cvUpdate.put(KEY_PARENT_phones, phone);

		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow,
				null);
	}

	public void deleteEntry(long lRow1) throws SQLException {
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
	}

	public LoginDatabase(Context c) {
		ourContext = c;
	}

	public LoginDatabase open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}
}
