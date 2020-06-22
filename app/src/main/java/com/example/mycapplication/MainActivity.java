package com.example.mycapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicInteger;
public class MainActivity extends AppCompatActivity {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJni());
    }

    private final AtomicInteger counter = new AtomicInteger(0);
    private AsyncTask mAsyncTask;

    @Override
    public void onStart() {
        super.onStart();
        mAsyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                while (!isCancelled()) {
                    Log.d("AsyncTask", "count: " + counter.get());
                    counter.incrementAndGet();
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAsyncTask.cancel(true);
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        new Thread(() -> {
//            while (true) {
//                Log.d("Thread", "count: " + counter.get());
//                counter.incrementAndGet();
//            }
//        }).start();
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Observable.fromCallable(() -> {
//            while (true) {
//                Log.e("RxJava", "count: " + counter.get);
//                counter.incrementAndGet();
//            }
//        }).subscribeOn(Schedulers.computation()).subscribe();
//    }

//    override fun onStart() {
//        super.onStart()
//        CoroutineScope(Dispatchers.Main).launch {
//            withContext(Dispatchers.Default) {
//                while (true) {
//                    Log.d("Coroutines", "count: ${counter.get()}")
//                    counter.incrementAndGet()
//                }
//            }
//        }
//    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJni();
    public native int add();
    public native int sub();
    public native int multiply();
    public native int divide();
}
