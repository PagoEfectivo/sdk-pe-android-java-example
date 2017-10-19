package pe.elcomercio.pagoefectivosdkjavasample.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipSearch;
import pe.elcomercio.pagoefectivosdkjavasample.R;

/**
 * Created by tohure on 19/10/17.
 */

public class ResultSearchAdapter extends RecyclerView.Adapter<ResultSearchAdapter.ResultSearchItemViewHolder> {

    private List<CipSearch> cipSearches;

    public ResultSearchAdapter(List<CipSearch> cipSearches) {
        this.cipSearches = cipSearches;
    }

    @Override
    public ResultSearchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_search, parent, false);
        return new ResultSearchItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResultSearchItemViewHolder holder, int position) {
        holder.lblCipSearch.setText(String.valueOf(cipSearches.get(position).getCip()));
        holder.lblTransactionCode.setText(cipSearches.get(position).getTransactionCode());
        holder.lblAmount.setText(String.valueOf(cipSearches.get(position).getAmount()));
        holder.lblCurrency.setText(cipSearches.get(position).getCurrency());
        holder.lblStatus.setText(String.valueOf(cipSearches.get(position).getStatus()));
        holder.lblStatusName.setText(cipSearches.get(position).getStatusName());
        holder.lblDateCreation.setText(cipSearches.get(position).getDateCreation());
        holder.lblDateExpire.setText(cipSearches.get(position).getDateExpiry());
        holder.lblDatePayment.setText(cipSearches.get(position).getDatePayment());
        holder.lblDateRemoval.setText(cipSearches.get(position).getDateRemoval());
    }

    @Override
    public int getItemCount() {
        return cipSearches.size();
    }

    class ResultSearchItemViewHolder extends RecyclerView.ViewHolder {

        private TextView lblCipSearch;
        private TextView lblTransactionCode;
        private TextView lblAmount;
        private TextView lblCurrency;
        private TextView lblStatus;
        private TextView lblStatusName;
        private TextView lblDateCreation;
        private TextView lblDateExpire;
        private TextView lblDatePayment;
        private TextView lblDateRemoval;

        ResultSearchItemViewHolder(View itemView) {
            super(itemView);

            lblCipSearch = itemView.findViewById(R.id.lblCipSearch);
            lblTransactionCode = itemView.findViewById(R.id.lblTransactionCode);
            lblAmount = itemView.findViewById(R.id.lblAmount);
            lblCurrency = itemView.findViewById(R.id.lblCurrency);
            lblStatus = itemView.findViewById(R.id.lblStatus);
            lblStatusName = itemView.findViewById(R.id.lblStatusName);
            lblDateCreation = itemView.findViewById(R.id.lblDateCreation);
            lblDateExpire = itemView.findViewById(R.id.lblDateExpire);
            lblDatePayment = itemView.findViewById(R.id.lblDatePayment);
            lblDateRemoval = itemView.findViewById(R.id.lblDateRemoval);

        }
    }
}
