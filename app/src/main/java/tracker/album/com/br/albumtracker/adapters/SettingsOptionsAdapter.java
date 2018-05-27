package tracker.album.com.br.albumtracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


import tracker.album.com.br.albumtracker.R;
import tracker.album.com.br.albumtracker.handlers.SettingsOptions;

/**
 * Created by Leonardo Assunção on 07/04/2016.
 */
public class SettingsOptionsAdapter extends RecyclerView.Adapter<SettingsOptionsAdapter.MyViewHolder> {

    private List<SettingsOptions> optionsList;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView option;
        public View mView;


        public MyViewHolder(View view) {
            super(view);
            mView = view;
            option = (TextView) view.findViewById(R.id.option);

        }
    }

    public SettingsOptionsAdapter(List<SettingsOptions> optionsList) {
        this.optionsList = optionsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.settings_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SettingsOptions option = optionsList.get(position);
        holder.option.setText(option.getOptions());
        holder.option.setTag(position);

    }

    @Override
    public int getItemCount() {
        return optionsList.size();
    }
}
