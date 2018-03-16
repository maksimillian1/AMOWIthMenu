package com.example.paimon.stillnotdone;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by paimon on 12.03.18.
 */

public class FragmentStartDefault extends android.support.v4.app.Fragment {
    ImageView startRakketa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_default, container, false);

        startRakketa = view.findViewById(R.id.startThis);
        startRakketa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Wooooops this in pipeline too", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
