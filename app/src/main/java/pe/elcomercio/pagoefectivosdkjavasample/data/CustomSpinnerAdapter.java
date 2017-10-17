package pe.elcomercio.pagoefectivosdkjavasample.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tohure on 16/10/17.
 */

public class CustomSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<Object> objects;

    public CustomSpinnerAdapter(Context context, List<Object> objects) {
        super();
        this.context = context;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);

            holder = new ItemViewHolder();

            holder.lblSpinnerItem = convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ItemViewHolder) convertView.getTag();
        }

        if (objects.get(position) instanceof TypeDefault) {
            TypeDefault entityDefault = (TypeDefault) objects.get(position);
            holder.lblSpinnerItem.setText(entityDefault.getName());
        }

        return convertView;
    }

    private static class ItemViewHolder {
        private TextView lblSpinnerItem;
    }
}
