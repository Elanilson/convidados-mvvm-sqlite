package com.apkdoandroid.convidados.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.apkdoandroid.convidados.constants.DataBaseConstants;

public class GuestDataHelper extends SQLiteOpenHelper {

    private static final String NOME = "convidados.db";
    private static final int VERSION = 1;

    // Query SQL para gerar a tabela
    private static final String CREATE_TABLE_GUEST = "create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
            + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
            + DataBaseConstants.GUEST.COLUMNS.NAME + " text, "
            + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);";

    /**
     * Construtor padrão
     */
    public GuestDataHelper( Context context) {
        super(context, NOME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_GUEST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
