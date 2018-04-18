package cl.dany.stressless.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import cl.dany.stressless.R;
import cl.dany.stressless.data.Queries;
import cl.dany.stressless.models.Pending;

public class PendingsAdapter extends RecyclerView.Adapter<PendingsAdapter.ViewHolder>{

    private List<Pending> pendingList = new Queries().pendings();
    private PendingClickListener listener ;

    public PendingsAdapter(PendingClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pending, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Pending pendings = pendingList.get(position);
        holder.textView.setText(pendings.getName());
        holder.checBox.setChecked(pendings.isDone());

        holder.checBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int auxPosition = holder.getAdapterPosition();
                            Pending auxPending = pendingList.get(auxPosition);
                            auxPending.setDone(true);
                            auxPending.save();
                            pendingList.remove(auxPosition);
                            notifyItemRemoved(auxPosition);
                        }
                    },400);
                }



            }
        });
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pending auxPending = pendingList.get(holder.getAdapterPosition());
                listener.clickedId(auxPending.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingList.size();
    }
    public void update(Pending pending) {
        pendingList.add(pending);
        notifyDataSetChanged();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checBox;
        private TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);
            checBox = itemView.findViewById(R.id.pendingCb);
            textView = itemView.findViewById(R.id.pendingTv);

        }
    }

}
