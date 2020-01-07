
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
//all currencies' adapter
public class AdapterRecycler2 extends RecyclerView.Adapter<AdapterRecycler2.PostHolder> {

    private List<Product> aProductList = new ArrayList<>();
    private ArrayList<Integer> favImgs = new ArrayList<Integer>();
    private ArrayList<String> favNames = new ArrayList<>();
    private ArrayList<String> favRates = new ArrayList<>();



    LayoutInflater inflater;
    //int previousPosition = 0;

    public AdapterRecycler2(List<Product> products) {
        this.aProductList = products;
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

        holder.productName.setText(aProductList.get(position).getCurrencyName());
        holder.productImage.setImageResource(aProductList.get(position).getImageId());
        holder.productRates.setText(aProductList.get(position).getCurrencyRates().toString());


    }

    @Override
    public int getItemCount() {
        return aProductList.size();
    }

    public static class PostHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {


        TextView productName, productRates;
        ImageView productImage, deleteproduct,checked;
        Product selectedProduct;
        CardView cardView;

        public PostHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.rv_flag);
            productName = itemView.findViewById(R.id.rv_name);
            productRates = itemView.findViewById(R.id.rv_rates);
            cardView = itemView.findViewById(R.id.mCardView);
            cardView.setOnCreateContextMenuListener(this);


        }

        //kullanılan dataların tek tek çekimi


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select an Option");
            menu.add(this.getAdapterPosition(),121,0,"Add to favorites list");


        }

        public ImageView getChecked(){return checked;}

    }


    public void addItem(int position){
        favNames.add(aProductList.get(position).getCurrencyName());
        favImgs.add(aProductList.get(position).getImageId());
        notifyDataSetChanged();
    }



    public List<String> getFavNames(){
        return favNames;
    }

    public List<Integer> getFavImgs(){
        return favImgs;
    }
}
