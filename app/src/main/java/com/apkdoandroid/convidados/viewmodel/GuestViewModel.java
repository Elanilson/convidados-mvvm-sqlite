package com.apkdoandroid.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apkdoandroid.convidados.model.GuestModel;
import com.apkdoandroid.convidados.model.Resposta;
import com.apkdoandroid.convidados.repositorio.GuestRepositorio;

public class GuestViewModel extends AndroidViewModel {
    private GuestRepositorio mRepositorio;

    private MutableLiveData<GuestModel> mgUEST =  new MutableLiveData<>();
    public LiveData<GuestModel> guest = mgUEST;

    private  MutableLiveData<Resposta> mResult = new MutableLiveData<>();
    public LiveData<Resposta> result = mResult;

    public GuestViewModel(@NonNull Application application) {
        super(application);
        mRepositorio =  GuestRepositorio.getInstace(application.getApplicationContext());
    }

    public void save(GuestModel guest){
        if("".equals(guest.getNome()) || " ".equals(guest.getNome()) ){
            mResult.setValue(new Resposta(false,"Nome obrigatorio"));
            return;
        }

        if(guest.getId() == 0){
            if(mRepositorio.insert(guest)){
                mResult.setValue(new Resposta("Convidado inserido com sucesso"));
            }else{
                mResult.setValue(new Resposta(false,"Error inesperado"));
            }
        }else{
            if(mRepositorio.update(guest)){
                mResult.setValue(new Resposta("Convidado Atualizado com sucesso"));

            }else{
                mResult.setValue(new Resposta(false,"Error inesperado"));
            }

        }

    }

    public void load (int id){
        mgUEST.setValue(mRepositorio.load(id));
    }
}
