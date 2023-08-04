package com.example.tvintento2;

import android.app.SearchManager;
import android.content.Context;
import android.media.session.MediaSession;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.tvintento2.placeholder.PlaceholderContent;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class ParcialFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    MyPArcialRecyclerViewAdapter adapter;
    LinearLayout ly;
    List<PlaceholderContent.ParcialholderItem> originalData;
    Animation slideInAnimation;

    private JSONArray jsonResponse;
    private AdapterView.OnItemClickListener teamListener;

    public static final ArrayList<PlaceholderContent.ParcialholderItem> ITEMS = new ArrayList<>();
    public static final ArrayList<PlaceholderContent.ParcialholderItem> ITEMS_REVERSO = new ArrayList<PlaceholderContent.ParcialholderItem>();

    //public static final ArrayList<PlaceholderContent.ParcialholderItem> ITEMS_TEMP = new ArrayList<PlaceholderContent.ParcialholderItem>();
    public static final ArrayList<PlaceholderContent.ParcialholderItem> ITEMS_REVERSO_TEMP = new ArrayList<PlaceholderContent.ParcialholderItem>();


    public ParcialFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ParcialFragment newInstance(int columnCount) {
        ParcialFragment fragment = new ParcialFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parcial_list, container, false);

        if (view instanceof RecyclerView) {
            adapter = new MyPArcialRecyclerViewAdapter(PlaceholderContent.ITEMS);
            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
            fillview();

        }

        return view;
    }

    public void filterData(String query) {

        if (query.isEmpty()) {
            fillview();
        } else {
            adapter.filtro(query);
        }
    }

    private void fillview() {

        HttpRequestTask httpRequestTask = new HttpRequestTask("GET", null, new HttpRequestTask.OnHttpRequestCompleteListener() {
            @Override
            public void onHttpRequestComplete(String response) {
                try {
                    jsonResponse = new JSONArray(response);
                    ArrayList<PlaceholderContent.ParcialholderItem> datos = PlaceholderContent.ParcialholderItem.fromJson(jsonResponse);
                    adapter.refill(datos);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onHttpRequestError(String response) {

            }
        });
        httpRequestTask.execute("http://192.168.1.72:8000/api/alumno");
    }

}
