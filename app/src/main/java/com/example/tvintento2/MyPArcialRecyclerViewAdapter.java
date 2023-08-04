package com.example.tvintento2;

import static android.content.ContentValues.TAG;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvintento2.databinding.FragmentParcialBinding;
import com.example.tvintento2.placeholder.PlaceholderContent;
import com.example.tvintento2.placeholder.PlaceholderContent.ParcialholderItem;
import com.example.tvintento2.databinding.FragmentParcialBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPArcialRecyclerViewAdapter extends RecyclerView.Adapter<MyPArcialRecyclerViewAdapter.ViewHolder> {

    Context context;
    private ArrayList<PlaceholderContent.ParcialholderItem> mValues;
    private ArrayList<PlaceholderContent.ParcialholderItem> mValuesOriginal;
    AdapterView.OnItemClickListener mListener;

    public MyPArcialRecyclerViewAdapter(ArrayList<PlaceholderContent.ParcialholderItem> items) {
        mValuesOriginal = new ArrayList<>(items);
        mValues = new ArrayList<>(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("DATA_DEBUG", "Total items in mValuesOriginal: " + mValuesOriginal.size());

        //View v = LayoutInflater.from(context).inflate(R.layout.fragment_parcial_list, parent, false);
        return new ViewHolder(FragmentParcialBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        //return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.name.setText(mValues.get(position).getNombre());
        holder.matricula.setText(mValues.get(position).getMatricula());
        holder.curp.setText(mValues.get(position).getCurp());
        holder.sexo.setText(mValues.get(position).getSexo());
        holder.edad.setText(String.valueOf(mValues.get(position).getEdad()));
        Glide.with(holder.imageView.getContext()).load(mValues.get(position).getImagen())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void filtro(String s) {
        List<PlaceholderContent.ParcialholderItem> tempList = new ArrayList<>(mValuesOriginal);
        Log.d("FILTER_DEBUG", "Filtering with query: " + tempList);
        mValues.clear();
        Log.d("FILTER_DEBUG", "Filtering with query: " + tempList);
        for (PlaceholderContent.ParcialholderItem item : tempList) {
            if (item.getNombre().toLowerCase().contains(s.toLowerCase()) ||
                    item.getMatricula().toLowerCase().contains(s.toLowerCase()) ||
                    item.getCurp().toLowerCase().contains(s.toLowerCase()) ||
                    item.getSexo().toLowerCase().contains(s.toLowerCase()) ||
                    String.valueOf(item.getEdad()).toLowerCase().contains(s.toLowerCase())) {
                mValues.add(item);
            }
        }
        notifyDataSetChanged();
    }

    public void refill(ArrayList<PlaceholderContent.ParcialholderItem> teamItems)
    {
        try {
            mValues = teamItems;
            mValuesOriginal = teamItems;
            notifyDataSetChanged();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public PlaceholderContent.ParcialholderItem mItem;
        public TextView name, sexo, matricula, edad, curp;
        public ImageView imageView;
        private Animation scaleInAnimation;
        private LinearLayout linearLayout;

        public ViewHolder(FragmentParcialBinding binding) {
            super(binding.getRoot());
            //mImageView = binding.imageView;

            name = binding.name;
            matricula = binding.matricula;
            curp = binding.curp;
            sexo = binding.sexo;
            edad = binding.edad;
            imageView = binding.imageView2;
            linearLayout = binding.linear;

            scaleInAnimation = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.fade_out);
            linearLayout.startAnimation(scaleInAnimation);
        }



        @Override
        public String toString() {
            return "ViewHolder{" +
                    ", name=" + name +
                    ", sexo=" + sexo +
                    ", matricula=" + matricula +
                    ", edad=" + edad +
                    ", curp=" + curp +
                    '}';
        }
    }
}