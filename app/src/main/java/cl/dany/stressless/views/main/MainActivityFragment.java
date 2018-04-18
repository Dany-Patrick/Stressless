package cl.dany.stressless.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.dany.stressless.R;
import cl.dany.stressless.adapters.PendingClickListener;
import cl.dany.stressless.adapters.PendingsAdapter;
import cl.dany.stressless.models.Pending;
import cl.dany.stressless.views.main.details.DetailsActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements PendingClickListener {
    public static final String USER_ID = "cl.dany.stressless.views.KEY.USER_ID";
    private PendingsAdapter pendingsAdapter;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//4
        RecyclerView recyclerView = view.findViewById(R.id.pendingRv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);



        pendingsAdapter = new PendingsAdapter(this );
        recyclerView.setAdapter(pendingsAdapter);

    }
    public void updateList(Pending pending)
    {
        pendingsAdapter.update(pending);

    }

    @Override
    public void clickedId(Long id) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(USER_ID, id);
        startActivity(intent);
    }
}
