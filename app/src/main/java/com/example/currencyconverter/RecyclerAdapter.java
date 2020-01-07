package com.example.currencyconverter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
//favorites' adapter
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PostHolder> {

    private List<Product> fProductList = new ArrayList<>();

    LayoutInflater inflater;
    //int previousPosition = 0;

    public RecyclerAdapter(List<Product> products) {
        this.fProductList = products;

    }


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //View view = inflater.inflate(R.layout.recycler_row,parent,false);
        View view = inflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);

        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.productName.setText(fProductList.get(position).getCurrencyName());
        holder.productImage.setImageResource(fProductList.get(position).getImageId());
        holder.productRates.setText(Double.toString(fProductList.get(position).getCurrencyRates()));
        //holder.productRates.setText(fProductList.get(position).getCurrencyRates());

    }

    @Override
    public int getItemCount() {
        return fProductList.size();
    }

    public static class PostHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {


        TextView productName, productRates;
        ImageView productImage, deleteproduct;
        Product selectedProduct;
        CardView cardView;

        public PostHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.rv_flag);
            productName = itemView.findViewById(R.id.rv_name);
            productRates = itemView.findViewById(R.id.rv_rates);
            cardView = itemView.findViewById(R.id.mCardView);
            cardView.setOnCreateContextMenuListener(this);

            //silme butonu için alttaki yorum satırını kaldır
            //deleteproduct.setOnClickListener(this);

        }






        /*@Override
        public void onClick(View v) {
            if (v == deleteproduct) {
                //deleteProduct(getLayoutPosition());
            }

        }*/

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select an Option");
            menu.add(this.getAdapterPosition(),122,0,"Delete this item");



        }

    }

    public void removeItem(int position){

        fProductList.remove(position);
        notifyDataSetChanged();
    }

}
