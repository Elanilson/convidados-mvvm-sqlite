package com.apkdoandroid.convidados.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.convidados.R;
import com.apkdoandroid.convidados.constants.GuestConstants;
import com.apkdoandroid.convidados.databinding.FragmentAllguestBinding;
import com.apkdoandroid.convidados.model.GuestModel;
import com.apkdoandroid.convidados.model.Resposta;
import com.apkdoandroid.convidados.view.adapter.GuestAdapter;
import com.apkdoandroid.convidados.view.listener.OnlistClick;
import com.apkdoandroid.convidados.viewmodel.AllGuestViewModel;

import java.util.List;


public class AllGuestFragment extends Fragment {

    private AllGuestViewModel viewModel;
    private FragmentAllguestBinding binding;

    private viewHolder mviewHolder = new viewHolder();
    private GuestAdapter adapter = new GuestAdapter();
    private int mFilter = 0 ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(this).get(AllGuestViewModel.class);

        binding = FragmentAllguestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mviewHolder.recyclerView = root.findViewById(R.id.recyclerViewAll);
        mviewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mviewHolder.recyclerView.setAdapter(adapter);


        OnlistClick onlistClick = new OnlistClick() {
            @Override
            public void onclick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);

                Intent intent = new Intent(getContext(),GuestFormActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onDelete(int id) {
                viewModel.deleta(id);
                viewModel.getList(mFilter);;
            }
        };

        adapter.attachListener(onlistClick);

        if(getArguments() != null){
            mFilter = getArguments().getInt(GuestConstants.Filter);

        }


        observers();



        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getList(mFilter);
    }

    private void observers() {
        viewModel.guestList.observe(getViewLifecycleOwner(), new Observer<List<GuestModel>>() {
            @Override
            public void onChanged(List<GuestModel> list) {
                adapter.attachList(list);
            }
        });
        viewModel.resposta.observe(getViewLifecycleOwner(), new Observer<Resposta>() {
            @Override
            public void onChanged(Resposta resposta) {
                Toast.makeText(getContext(), resposta.getMensagem(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  static  class  viewHolder{
        RecyclerView recyclerView;
    }


}