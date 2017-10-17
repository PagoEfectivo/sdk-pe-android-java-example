package pe.elcomercio.pagoefectivosdkjavasample.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.elcomercio.pagoefectivosdkjavasample.R;

/**
 * Created by tohure on 17/10/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchItemViewHolder> {
    private List<Integer> cipList;

    public SearchAdapter(List<Integer> cipList) {
        this.cipList = cipList;
    }

    @Override
    public SearchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edittext, parent, false);
        return new SearchItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchItemViewHolder holder, int position) {}

    @Override
    public int getItemCount() {
        return cipList.size();
    }

    class SearchItemViewHolder extends RecyclerView.ViewHolder {
        SearchItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
