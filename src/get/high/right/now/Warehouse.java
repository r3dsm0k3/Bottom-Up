package get.high.right.now;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Warehouse extends SQLiteOpenHelper {

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	 private static final int DATABASE_VERSION = 1;
	 
	 private SQLiteDatabase db;
	 private static Context currentContext;
	 private static final String DATABASE_NAME = "warehouseDB";
	 private static final String SMALL_TABLE_NAME = "smallDetails";
	 private static final String DB_CREATE_SMALL_DETAILS_QUERY = "create table smallDetails (id integer not null,"+
	 "type varchar(256) not null,"+"brand varchar(256) not null,"+"quantity float not null"+"price float not null)";
	 
	public Warehouse(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		currentContext = context;
	}
	public Warehouse (){
		super(currentContext,DATABASE_NAME, null, DATABASE_VERSION);
	}
	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(DB_CREATE_SMALL_DETAILS_QUERY);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	public Warehouse open() throws SQLException 
    {
        db = this.getWritableDatabase();
        return this;
    }
	public void deleteOurSmallTable(){
		db = this.getWritableDatabase();
		db.delete(SMALL_TABLE_NAME, null, null);
	}
	public long insertNewSmallEntry(MyBoozeObj obj){
		int id 			= obj.getId();
		String type 	= obj.getType();
		String brand 	= obj.getBrand();
		float quantity 	= obj.getQuantity();
		float price 	= obj.getPrice();
		
		ContentValues initialValues = new ContentValues();
		initialValues.put("id",id);
		initialValues.put("type",type);
		initialValues.put("brand", brand);
		initialValues.put("quantity",quantity);
		initialValues.put("price",price);
		
		db = this.getWritableDatabase();
		return db.insert(SMALL_TABLE_NAME, null, initialValues);
	}
	public List<MyBoozeObj> getAllSmallDetailsByType(String type,boolean sortbyPrice){
		db 					= this.getWritableDatabase();
		String query 		= "type="+type;
		Cursor ret 			= db.query(SMALL_TABLE_NAME, new String[] { "id", "brand","quantity", "price"},query, null, null,null, sortbyPrice ? "price DESC" : "brand ASC");
		MyBoozeObj obj 		= new MyBoozeObj();
		List<MyBoozeObj> lst= new ArrayList<MyBoozeObj>();
		if(ret.getCount()>0){
			ret.moveToFirst();
			while (ret.isAfterLast() == false) {
				int 	id 			= ret.getInt(ret.getColumnIndex("id"));
				String 	brand 		= ret.getString(ret.getColumnIndex("brand"));
				float 	quantity 	= ret.getFloat(ret.getColumnIndex("quantity"));
				float 	price 		= ret.getFloat(ret.getColumnIndex("price"));
				obj.setId(id);
				obj.setBrand(brand);
				obj.setPrice(price);
				obj.setQuantity(quantity);
				obj.setType(type);
				lst.add(obj);
				ret.moveToNext();
			}
			ret.close();
			
		}
		return lst;
		
	}
	public List<MyBoozeObj> getAllSmallDetailsByBrand(String brand){
		db 					= this.getWritableDatabase();
		String query 		= "type="+brand;
		Cursor ret			= null;
		try{
			ret 			= db.query(SMALL_TABLE_NAME, new String[] { "id", "type","quantity", "price"},query, null, null,null, null);
		}
		catch(Exception e){
			String str= e.getLocalizedMessage();
			e.printStackTrace();
			Log.e("OMG,Something terribly went wrong during getAllSmallDetailsByBrand Function(),go gO GO FIX IT", str);
		}
		
		MyBoozeObj obj 		= new MyBoozeObj();
		List<MyBoozeObj> lst= new ArrayList<MyBoozeObj>();
		if(ret.getCount()>0){
			ret.moveToFirst();
			while (ret.isAfterLast() == false) {
				int 	id 			= ret.getInt(ret.getColumnIndex("id"));
				String 	type 		= ret.getString(ret.getColumnIndex("type"));
				float 	quantity 	= ret.getFloat(ret.getColumnIndex("quantity"));
				float 	price 		= ret.getFloat(ret.getColumnIndex("price"));
				obj.setId(id);
				obj.setBrand(type);
				obj.setPrice(price);
				obj.setQuantity(quantity);
				obj.setType(brand);
				lst.add(obj);
				ret.moveToNext();
			}
			ret.close();
			
		}
		return lst;
	}

}
