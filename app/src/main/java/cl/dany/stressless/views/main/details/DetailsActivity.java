package cl.dany.stressless.views.main.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import cl.dany.stressless.R;
import cl.dany.stressless.models.Pending;

public class DetailsActivity extends AppCompatActivity {
    public static final String USER_ID = "cl.dany.stressless.views.KEY.USER_ID";
    private Pending pending ;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long idLong = getIntent().getLongExtra(USER_ID,0);
        setContentView(R.layout.activity_details);
        pending = Pending.findById(Pending.class,idLong);
        getSupportActionBar().setTitle(pending.getName());
         editText = findViewById(R.id.detailTv);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(pending.getDescription() != null)
        {
            editText.setText(pending.getDescription());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pending.setDescription(editText.getText().toString());
        pending.save();
    }
}
