package com.apkdoandroid.convidados.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.apkdoandroid.convidados.R;
import com.apkdoandroid.convidados.constants.GuestConstants;
import com.apkdoandroid.convidados.model.GuestModel;
import com.apkdoandroid.convidados.model.Resposta;
import com.apkdoandroid.convidados.viewmodel.GuestViewModel;

public class GuestFormActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestViewModel mViewModel;
    private int mGuestId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_form);

        mViewModel = new ViewModelProvider(this).get(GuestViewModel.class);

        mViewHolder.editNome = findViewById(R.id.editTexNome);
        mViewHolder.radioNConfirmado = findViewById(R.id.radioButtonNaoConfirmado);
        mViewHolder.radioPresente = findViewById(R.id.radioButtonPresente);
        mViewHolder.radioAusente = findViewById(R.id.radioButtonAusente);
        mViewHolder.btnSalvar = findViewById(R.id.buttonSalvar);
        
        setListner();
        setObservers();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mGuestId = bundle.getInt("id");
            mViewModel.load(mGuestId);

        }
    }

    private void setObservers() {
        mViewModel.guest.observe(this, new Observer<GuestModel>() {
            @Override
            public void onChanged(GuestModel guestModel) {
                    mViewHolder.editNome.setText(guestModel.getNome());
                    mViewHolder.radioNConfirmado.setChecked(guestModel.getConfirmation() == GuestConstants.CONFIRMATION.NOT_CONFIRMED);
                    mViewHolder.radioPresente.setChecked(guestModel.getConfirmation() == GuestConstants.CONFIRMATION.PRESENT);
                    mViewHolder.radioAusente.setChecked(guestModel.getConfirmation() == GuestConstants.CONFIRMATION.AUSENTE);


            }
        });

        mViewModel.result.observe(this, new Observer<Resposta>() {
            @Override
            public void onChanged(Resposta resposta) {
                Toast.makeText(GuestFormActivity.this, resposta.getMensagem(), Toast.LENGTH_SHORT).show();
                if(resposta.isSucess() ){
                    finish();
                }else{

                }

            }
        });
    }


    private void setListner() {
        mViewHolder.btnSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSalvar){
            handleSave();

        }
    }

    private void handleSave() {
        String nome = mViewHolder.editNome.getText().toString();
        int confirmation = 0;
        if(mViewHolder.radioPresente.isChecked()){
            confirmation = GuestConstants.CONFIRMATION.PRESENT;
        }else if(mViewHolder.radioAusente.isChecked()){
            confirmation = GuestConstants.CONFIRMATION.AUSENTE;
        }

        GuestModel guest = new GuestModel(mGuestId,nome,confirmation);

        mViewModel.save(guest);
    }

    private static class ViewHolder{
        EditText editNome;
        RadioButton radioNConfirmado,radioPresente,radioAusente;
        Button btnSalvar;
    }
}