package com.apkdoandroid.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apkdoandroid.convidados.constants.GuestConstants;
import com.apkdoandroid.convidados.model.GuestModel;
import com.apkdoandroid.convidados.model.Resposta;
import com.apkdoandroid.convidados.repositorio.GuestRepositorio;

import java.util.List;

public class AllGuestViewModel extends AndroidViewModel {

    private GuestRepositorio mRepositorio;

    private MutableLiveData<List<GuestModel>> mlista = new MutableLiveData<>();
    public LiveData<List<GuestModel>>guestList = mlista;

    private MutableLiveData<Resposta> mRespota = new MutableLiveData<>();
    public LiveData<Resposta> resposta = mRespota;

    public AllGuestViewModel(@NonNull Application application) {
        super(application);
        this.mRepositorio = GuestRepositorio.getInstace(application.getApplicationContext());
    }

    public void getList(int filter){
        if(filter == GuestConstants.CONFIRMATION.NOT_CONFIRMED){
            mlista.setValue(mRepositorio.getAll());
        }else if(filter == GuestConstants.CONFIRMATION.PRESENT){
            mlista.setValue(mRepositorio.getPresentes());
        }else if(filter == GuestConstants.CONFIRMATION.AUSENTE){
            mlista.setValue(mRepositorio.getAusentes());
        }


    }

    public void deleta(int id){
        if(mRepositorio.delete(id)){
            mRespota.setValue(new Resposta("Removido com sucesso"));
        }else{
            mRespota.setValue(new Resposta(false,"Error inesperado"));
        }
    }
}