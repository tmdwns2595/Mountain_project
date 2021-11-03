package org.jjcouple.termproject.camping_map;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jjcouple.termproject.R;
import org.jjcouple.termproject.mountain_map.kakao_mountain_map;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.CustomViewHolder> implements Filterable {
    private ArrayList<String> unFilteredlist;
    private ArrayList<String> filteredList;
    private Context context;

    public CustomAdapter2(ArrayList<String> arrayList, Context context) {
        this.unFilteredlist = arrayList;
        this.filteredList = arrayList;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list2, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter2.CustomViewHolder holder, int position) {
        holder.camping_address.setText(filteredList.get(position));
        holder.itemView.setOnClickListener(v -> {
            if (mListener != null){
                if (position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(v, position);
                    Intent intent = new Intent(context, kakao_camping_map.class);
                    intent.putExtra("camping_address", filteredList.get(position));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView camping_address;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            camping_address = itemView.findViewById(R.id.camping_address);

        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                Log.d("CustomAdapter2", charString);
                if(charString.isEmpty()) {
                    filteredList = unFilteredlist;
                } else {
                    ArrayList<String> filteringList = new ArrayList<>();
                    for(String name : unFilteredlist) {
                        if(name.contains(charString)) {
                            Log.d("CustomAdapter2", name);
                            filteringList.add(name);
                        }
                    }
                    filteredList = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<String>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
