package pe.elcomercio.pagoefectivosdkjavasample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.elcomercio.pagoefectivosdkjavasample.R;
import pe.elcomercio.pagoefectivosdkjavasample.model.PaymentMethodEntity;

/**
 * Created by tohure on 17/10/17.
 */

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentItemViewHolder> {

    private List<PaymentMethodEntity> paymentMethodList;

    //Listener for onClick adapter
    private PaymentMethodAdapter.OnItemClickListener listener;
    public interface OnItemClickListener { void onItemClickMethodPayment(int typeItem); }
    public void setOnItemClickListener(OnItemClickListener listener) { this.listener = listener; }

    public PaymentMethodAdapter(List<PaymentMethodEntity> paymentMethodList) {
        this.paymentMethodList = paymentMethodList;
    }

    @Override
    public PaymentItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_method_payment, parent, false);
        return new PaymentItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PaymentItemViewHolder holder, int position) {
        holder.lblPaymentMethod.setText(paymentMethodList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return paymentMethodList.size();
    }

    class PaymentItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lblPaymentMethod;

        PaymentItemViewHolder(View itemView) {
            super(itemView);
            lblPaymentMethod = itemView.findViewById(R.id.lblPaymentMethod);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClickMethodPayment(paymentMethodList.get(getAdapterPosition()).getType());
            }
        }
    }
}
