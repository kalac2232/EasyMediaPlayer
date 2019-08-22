package cn.kalac.easymediaplayer;

import android.app.Fragment;
import android.os.Bundle;


import java.util.ArrayList;

/**
 * @author kalac.
 * @date 2019/8/18 15:27
 */
public class LifeListenerFragment extends Fragment {

    private ArrayList<LifeListener> listeners = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (LifeListener listener : listeners) {
            if (listener != null) {
                listener.onCreate(savedInstanceState);
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        for (LifeListener listener : listeners) {
            if (listener != null) {
                listener.onStart();
            }
        }

    }


    @Override
    public void onResume() {
        super.onResume();

        for (LifeListener listener : listeners) {
            if (listener != null) {
                listener.onResume();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        for (LifeListener listener : listeners) {
            if (listener != null) {
                listener.onStop();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        for (LifeListener listener : listeners) {
            if (listener != null) {
                listener.onPause();
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        for (LifeListener listener : listeners) {
            if (listener != null) {
                listener.onDestroy();
            }
        }
    }


    public void addLifeListener(LifeListener listener) {

        if (listener != null) {
            listeners.add(listener);
        }

    }

    public void removeLifeListener(LifeListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }

}