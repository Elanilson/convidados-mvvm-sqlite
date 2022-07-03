package com.apkdoandroid.convidados.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.apkdoandroid.convidados.constants.DataBaseConstants;
import com.apkdoandroid.convidados.constants.GuestConstants;
import com.apkdoandroid.convidados.model.GuestModel;

import java.util.ArrayList;
import java.util.List;

public class GuestRepositorio {

    private static GuestRepositorio INSTACE;
    private GuestDataHelper mHelper;

    private GuestRepositorio(Context context) {
        mHelper = new GuestDataHelper(context);
    }


    public static GuestRepositorio getInstace(Context context){
        if(INSTACE == null){
            INSTACE = new GuestRepositorio(context);
        }
        return INSTACE;
    }

    private List<GuestModel> getList(String selection,String[] selectionArgs){
        List<GuestModel> list = new ArrayList<>();
        try{
            GuestModel guest = null;
            SQLiteDatabase db = mHelper.getReadableDatabase();

            String tabela = DataBaseConstants.GUEST.TABLE_NAME;
            String[] columns = {DataBaseConstants.GUEST.COLUMNS.ID,
                    DataBaseConstants.GUEST.COLUMNS.NAME,
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE};


            Cursor cursor = db.query(tabela,columns,selection,selectionArgs,null,null,null,null);

            if(cursor != null && cursor.getCount() > 0){

                while (cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID));
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME));
                    int present = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE));
                    list.add(new GuestModel(id,nome,present));

                }
            }
            if(cursor != null){
                cursor.close();
            }
            return list;
        }catch (Exception e){
            return list;
        }
    }

    public List<GuestModel> getAll(){
        return getList(null,null);

    }
    public List<GuestModel> getPresentes(){
            String selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + "= ?";
            String[] args ={String.valueOf(GuestConstants.CONFIRMATION.PRESENT)};
            return getList(selection,args);

    }

    public List<GuestModel> getAusentes(){

            String selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + "= ?";
            String[] args ={String.valueOf(GuestConstants.CONFIRMATION.AUSENTE)};
            return getList(selection,args);

    }

    public GuestModel load(int id){
        try{
            GuestModel guest = null;
            SQLiteDatabase db = mHelper.getReadableDatabase();

            String tabela = DataBaseConstants.GUEST.TABLE_NAME;
            String[] columns = {DataBaseConstants.GUEST.COLUMNS.ID,
                    DataBaseConstants.GUEST.COLUMNS.NAME,
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE};
            String selection = DataBaseConstants.GUEST.COLUMNS.ID + "= ?";
            String[] args ={String.valueOf(id)};

            Cursor cursor = db.query(tabela,columns,selection,args,null,null,null,null);

            if(cursor != null && cursor.getCount() > 0){
                cursor.moveToFirst();
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME));
                int present = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE));


                guest = new GuestModel(id,nome,present);
            }
            if(cursor != null){
                cursor.close();
            }
            return  guest;

        }catch (Exception e){
            System.out.println("Error ao buscar: "+e.getMessage());
            return  null;

        }
    }

    public Boolean insert(GuestModel guest){
        try{
            SQLiteDatabase db = mHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(DataBaseConstants.GUEST.COLUMNS.NAME,guest.getNome());
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE,guest.getConfirmation());

            db.insert(DataBaseConstants.GUEST.TABLE_NAME,null,values);
          //  db.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public Boolean update(GuestModel guest){
        try{

            SQLiteDatabase db = mHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(DataBaseConstants.GUEST.COLUMNS.NAME,guest.getNome());
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE,guest.getConfirmation());

            String where = DataBaseConstants.GUEST.COLUMNS.ID+" = ?";
            String[] args = {String.valueOf(guest.getId())};

            db.update(DataBaseConstants.GUEST.TABLE_NAME,values,where,args);
           // db.close();
            return true;

        }catch (Exception e){
            System.out.println(e.getMessage());

            return  false;
        }
    }

    public Boolean delete(int id){
        try{

            SQLiteDatabase db = mHelper.getWritableDatabase();

            String where = DataBaseConstants.GUEST.COLUMNS.ID+" = ?";
            String[] args = {String.valueOf(id)};

            db.delete(DataBaseConstants.GUEST.TABLE_NAME,where,args);
         //   db.close();
            return true;

        }catch (Exception e){
            System.out.println(e.getMessage());

            return  false;
        }
    }
}
