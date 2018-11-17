package com.example.mohammadbangoura.guineemedical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class LoginDatabase{
	public static final String KEY_ROWID = "Id";
	public static final String KEY_password = "password";
	public static final String KEY_numero_attes = "attestation";
	public static final String KEY_nom = "nom";

	private static final String DATABASE_NAME = "Medical";
	private static final String DATABASE_TABLE = "Record";
	private static final int DATABASE_VERSION = 2;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	ArrayList numero_list = new ArrayList();
	ArrayList nom_list = new ArrayList();
	static String[] all_numero_list;

	private static class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);}

		@Override
		public void onCreate(SQLiteDatabase db){
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_password
					+ " TEXT NOT NULL, " + KEY_numero_attes
					+ " TEXT NOT NULL, " + KEY_nom 
					+ " TEXT NOT NULL);");}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}}

	public long createPassword(String numero, String nom) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_password, numero);
		cv.put(KEY_numero_attes, numero);
		cv.put(KEY_nom, nom);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

    public boolean updatePassword(long lRow, String numero)
            throws SQLException {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_password, numero);
        return ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow,
                null) > 0;
    }

	public long createEntry(String numero, String nom) {
		ContentValues cv = new ContentValues();
//		cv.put(KEY_password, password);
		cv.put(KEY_numero_attes, numero);
		cv.put(KEY_nom, nom);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

    //mise a jour medecin
    public void updateEntry(long lRow, String numero, String nom)
            throws SQLException {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_numero_attes, numero);
        cvUpdate.put(KEY_nom, nom);

        ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow,
                null);
    }

	public String getpassword(){
		String[] columns = new String[] {KEY_ROWID, KEY_password, KEY_numero_attes, KEY_nom};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,	null, null);

		String result = "";
		int password = c.getColumnIndex(KEY_password);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			//numero_list.add(c.getString(password).toString()); //password into database
			result  += c.getString(password);
		}

//		if( c != null && c.moveToFirst() ) {
//			password = c.getColumnIndex(KEY_password);
//			result  = c.getString(password);
//		    c.close(); }

		return result;
	}

	public String getData(){
		String[] columns = new String[] {KEY_ROWID, KEY_password, KEY_numero_attes, KEY_nom};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,	null, null);

		String result = "";
		int iRow =    c.getColumnIndex(KEY_ROWID);
		int password = c.getColumnIndex(KEY_password);
		int numero =    c.getColumnIndex(KEY_numero_attes);
		int nom =   c.getColumnIndex(KEY_nom);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			numero_list.add(c.getString(numero).toString()); //numero in database
			nom_list.add(c.getString(nom).toString()); //nom in database
			//concatenate students info
			result  += c.getString(iRow) + " - "
					+  c.getString(password) + " - "
					+  c.getString(numero) + " - "
					+  c.getString(nom)+ "\n\n";
		}
	return result;}

	//supprimer medecin
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
