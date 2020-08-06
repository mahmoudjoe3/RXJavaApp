package com.mahmoudjoe333.rxjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cold observables
         coldObservable(); //every time one subscribe Observable repeat
        
         //Hot observables
         connectObservable();
         publishSubject();  //the observer listen to the observables where it is place
         behaviorSubject();  //like publish subject but he can see the last operation before it enter
         replaySubject(); //like publish subject but he stop the first observer until he listen to old data and after that let the first observer to continue
         AsyncSubject(); //the observer can see the last data only
    }




    private void coldObservable() {

        Observable<Long> cold= Observable.intervalRange(0,6,0,1, TimeUnit.SECONDS);
        cold.subscribe(i-> Log.d(TAG, "coldObservable: student 1 = "+i));
        sleep(3);
        cold.subscribe(i-> Log.d(TAG, "coldObservable: student 2 = "+i));
    }
    private void connectObservable() {
        ConnectableObservable<Long>hot=ConnectableObservable.intervalRange(0,5,0,1,TimeUnit.SECONDS).publish();

        hot.connect(); //start publishing data
        sleep(1);
        hot.subscribe(i-> Log.d(TAG, "connectableObservable: student 1 "+i));
        sleep(2);
        hot.subscribe(i-> Log.d(TAG, "connectableObservable: student 2 "+i));
    }
    private void publishSubject() {
        PublishSubject<String> publish = PublishSubject.create();
        publish.subscribe(i-> Log.d(TAG, "publishSubject: student 1 "+i));
        publish.onNext("A");
        sleep(1);
        publish.onNext("B");
        sleep(1);
        publish.onNext("C");
        sleep(1);
        publish.onNext("D");
        sleep(1);
        publish.onNext("E");
        sleep(1);
        publish.subscribe(i-> Log.d(TAG, "publishSubject: student 2 "+i));
        publish.onNext("F");
        sleep(1);
        publish.onNext("G");
        sleep(1);
        publish.onNext("H");
        sleep(1);
        publish.onNext("I");
        sleep(1);
        publish.onNext("J");
        sleep(1);
    }
    private void behaviorSubject() {
        BehaviorSubject<String> behavior = BehaviorSubject.create();
        behavior.subscribe(i-> Log.d(TAG, "publishSubject: student 1 "+i));
        behavior.onNext("A");
        sleep(1);
        behavior.onNext("B");
        sleep(1);
        behavior.onNext("C");
        sleep(1);
        behavior.onNext("D");
        sleep(1);
        behavior.onNext("E");
        sleep(1);
        behavior.subscribe(i-> Log.d(TAG, "publishSubject: student 2 "+i));
        behavior.onNext("F");
        sleep(1);
        behavior.onNext("G");
        sleep(1);
        behavior.onNext("H");
        sleep(1);
        behavior.onNext("I");
        sleep(1);
        behavior.onNext("J");
        sleep(1);
    }
    private void replaySubject() {
        ReplaySubject<String> replay = ReplaySubject.create();
        replay.subscribe(i-> Log.d(TAG, "publishSubject: student 1 "+i));
        replay.onNext("A");
        sleep(1);
        replay.onNext("B");
        sleep(1);
        replay.onNext("C");
        sleep(1);
        replay.onNext("D");
        sleep(1);
        replay.onNext("E");
        sleep(1);
        replay.subscribe(i-> Log.d(TAG, "publishSubject: student 2 "+i));
        replay.onNext("F");
        sleep(1);
        replay.onNext("G");
        sleep(1);
        replay.onNext("H");
        sleep(1);
        replay.onNext("I");
        sleep(1);
        replay.onNext("J");
        sleep(1);
    }
    private void AsyncSubject() {
        AsyncSubject<String> Async = AsyncSubject.create();
        Async.subscribe(i-> Log.d(TAG, "publishSubject: student 1 "+i));
        Async.onNext("A");
        sleep(1);
        Async.onNext("B");
        sleep(1);
        Async.onNext("E");
        sleep(1);
        Async.subscribe(i-> Log.d(TAG, "publishSubject: student 2 "+i));
        Async.onNext("F");
        sleep(1);
        Async.onNext("J");
        sleep(1);
        Async.onComplete();
    }



    private void sleep(int second) {
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}