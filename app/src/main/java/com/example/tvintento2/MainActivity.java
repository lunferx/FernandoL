package com.example.tvintento2;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvintento2.placeholder.PlaceholderContent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends FragmentActivity implements HttpRequestTask.OnHttpRequestCompleteListener, SearchView.OnQueryTextListener {
    private TextView tempTextView;
    private EditText nbHours;
    private EditText nbMinutes;
    private EditText nbSeconds;
    private TextView timerTextView;
    private Button startButton;
    private Button pauseButton;
    private Button resetButton;
    private Button startButtonTemp;
    private Button pauseButtonTemp;
    private Button resetButtonTemp;
    private Button intervalButtonTemp;
    private Button parcialButton;
    private boolean isTimerRunning = false;
    private boolean isTempRunning = false;
    private int seconds = 0;
    private int secondsTemp = 0;

    // Fragment
    private SearchView searchView;
    FrameLayout fragment_container;
    ParcialFragment parcialFragment;
    FragmentTransaction ft;
    FrameLayout fragment_container_temp;
    ParcialFragment parcialFragmentTemp;
    FragmentTransaction ftTemp;
    MyPArcialRecyclerViewAdapter adapter;

    private Handler handler;
    String tiempoParcial = "00:00:00";
    private Handler handlerTemp;
    String tiempoParcialTemp = "00:00:00";

    private static final String API_BASE_URL = "http://192.168.123.40:8000/api/alumno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_container = findViewById(R.id.fragment_container);
        parcialFragment = ParcialFragment.newInstance(1);
        parcialFragmentTemp = ParcialFragment.newInstance(1);

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parcialFragment, "Cronometro");
        ft.commit();
        handler = new Handler();
        handlerTemp = new Handler();

        searchView = findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onHttpRequestComplete(String response) {
        Log.d("JSON_RESPONSE", response);
    }

    @Override
    public void onHttpRequestError(String error) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        parcialFragment.filterData(s);
        return false;
    }
}

