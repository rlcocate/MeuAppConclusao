package nossafirma.com.br.meuapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.LinkedList;
import java.util.List;

import nossafirma.com.br.meuapp.model.Beer;
import nossafirma.com.br.meuapp.model.Login;
import nossafirma.com.br.meuapp.model.Region;
import nossafirma.com.br.meuapp.model.Store;

public class StoreDAO {

    private DBHelper dbHelper;
    private Beer beer;
    private Region region;
    private Store store;

    public static final String TABELA_STORE = "Store";
    public static final String TABELA_BEER = "Beer";
    public static final String TABELA_REGION = "Region";

    public static final String COLUNA_ID = "id"; // Identity
    public static final String COLUNA_NAME = "name";
    public static final String COLUNA_REGION_ID = "regionId";
    public static final String COLUNA_BEER_ID = "beerId";
    public static final String COLUNA_VALUE = "value";

    public StoreDAO(Context context) {
        dbHelper = new DBHelper(context);

        region = new Region();
        beer = new Beer();
        store = new Store();
    }

    public List<Store> getAll() {

        List<Store> stores = new LinkedList<>();
        Store store = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT id       = S.id," +
                       "       name     = S.name," +
                       "       regionId = S.regionId," +
                       "       region   = R.name," +
                       "       beerId   = S.beerId," +
                       "       beer     = B.name," +
                       "       value    = S.value," +
                       "  FROM " + TABELA_STORE + " S " +
                       " LEFT JOIN " + TABELA_REGION + " R ON (S." + COLUNA_REGION_ID + " = " + "R.id)" +
                       " LEFT JOIN " + TABELA_BEER + "   B ON (S." + COLUNA_BEER_ID + "   = " + "B.id)";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                store = new Store();
                store.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                store.setName(cursor.getString(cursor.getColumnIndex(COLUNA_NAME)));
                store.setRegion(new Region(cursor.getInt(3), null, cursor.getString(4)));
                store.setBeer(new Beer(cursor.getInt(5), cursor.getString(6)));
                store.setValue(cursor.getDouble(cursor.getColumnIndex(COLUNA_VALUE)));
                stores.add(store);
            } while (cursor.moveToNext());
        }
        return stores;
    }

    public Store getBy(String name) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String colunas[] = {
                COLUNA_ID,
                COLUNA_NAME,
                COLUNA_REGION_ID,
                COLUNA_BEER_ID,
                COLUNA_VALUE
        };

        String where = "name = '" + name + "'";
        Cursor cursor = db
                .query(true, TABELA_STORE, colunas, where, null, null, null, null, null);
        store = null;
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                store = new Store();
                store.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                store.setName(cursor.getString(cursor.getColumnIndex(COLUNA_NAME)));
                store.setRegion(null);
                store.setBeer(null);
                store.setValue(cursor.getDouble(cursor.getColumnIndex(COLUNA_VALUE)));
            }
        }
        return store;
    }

    public void add(Store store) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (getBy(store.getName()) == null) {

            ContentValues values = new ContentValues();

            values.put(COLUNA_NAME, store.getName());
            values.put(COLUNA_REGION_ID, store.getRegion().getId());
            values.put(COLUNA_BEER_ID, store.getRegion().getId());
            values.put(COLUNA_VALUE, store.getValue());

            long resultado = db.insert(TABELA_STORE, null, values);

            db.close();
        }
    }
}