package com.example.rxjavatest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavatest.bean.AuthorBean;
import com.example.rxjavatest.bean.Dog;
import com.example.rxjavatest.bean.Duck;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.example.rxjavatest.DataUtil.initAuthorList;


/**
 *
 */
public class MainActivity extends AppCompatActivity {
    private List<Dog> dogList;
    private List<Duck> duckList;
    private String TAG = getClass().getSimpleName();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle_view_display);
//        addSubscriber();
        createObservable();
        initLogger();
    }

    private void initLogger() {
//        1.初始化参数
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // 是否显示线程信息，默认为ture
                .methodCount(3)         // 显示的方法行数，默认为2
//                    .methodOffset(1)        // 隐藏内部方法调用到偏移量，默认为5
//                    .logStrategy() // 更改要打印的日志策略。
                .tag("custom tag")   // 每个日志的全局标记。默认PRETTY_LOGGER
                .build();
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
//        2.初始化要不要输出Log
//        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
//            @Override
//            public boolean isLoggable(int priority, @Nullable String tag) {
//                return BuildConfig.DEBUG;//
//            }
//        });
        //3.初始化是否拷贝文件
        //保存的路径： /storage/emulated/0
        Logger.addLogAdapter(new DiskLogAdapter());


    }

    /**
     * 用create创建Observable,添加单个事件
     */
    private void createObservable() {
        //创建被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {//emitter:发射器
                //传递事件
                emitter.onNext("貂蝉");
                emitter.onNext("小乔");
                emitter.onComplete();//onComplete()和onError（）不会同时执行。
            }
        });

//
//        //添加数组
//        Observable observable1 = Observable.just("大乔", "孙策");
//        observable1.subscribe(subscriber);
//        //添加数组变量
//        String[] weChatUser = {"貂蝉", "王昭君"};
//        Observable observable2 = Observable.from(weChatUser);
//        observable2.subscribe(subscriber);
        //创建观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Logger.d("被观察者发来的数据：" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Logger.d("执行了onCompleted()");
            }
        };
        //建立观察者和被观察者之间的订阅关系
        observable.subscribe(observer);
    }

    /**
     * 创建Observable ，可以添加事件数组，依次发送出去
     */
    private void formObservable() {
        String[] array = {"李白", "王昭君"};
        //创建被观察者
        //form形式
//        Observable<String> observable = Observable.fromArray(array);
        //just
        Observable<String> observable = Observable.just("上官婉儿", "娜可露露");
        //创建观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Logger.d("被观察者发来的数据：" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Logger.d("onComplete");
            }
        };
        //d订阅
        observable.subscribe(observer);

    }

    /**
     * 定义观察者的不完整回调，因为有时我们不需要关注OnError,OnCompleted，方便简化代码
     */
    @SuppressLint("CheckResult")
    private void incompleteCallBack() {
        //创建被观察者
        Observable<String> observable = Observable.just("上官婉儿", "娜可露露");
        //创建观察者的回调,rxjava和rxjava2的区别：Action0,Action1,Action2--->Action,Consumer,BiConsumer
        //OnNext
        Consumer<String> onNext = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.d("接收到的数据：" + s);
            }
        };
        //OnError
        Consumer<Throwable> onError = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.d(throwable);
            }
        };
        //OnCompleted
        Action onCompleted = new Action() {
            @Override
            public void run() throws Exception {
                Logger.d("onCompleted");
            }
        };

        //订阅

        observable.subscribe(onNext, onError);
    }

    /**
     * RxJava变换操作符-map,通过Function(Integer,Object)对被观察者发射出来的每个数据进行操作，输出Observer<Object>,可再进行map,或flatmap操作，
     * 被观察者可以看做是一个发射器，发布者。
     */
    @SuppressLint("CheckResult")
    private void mapOperator() {
        Observable.just(1, 2, 3, 4)//发射数据
                .map(new Function<Integer, Integer>() {//map转化数据：Integer转String
                    @SuppressLint("CheckResult")
                    @Override
                    public Integer apply(Integer integer) {
                        return integer + 1;
                    }
                })
                .subscribe(new Consumer<Integer>() {//观察者：收到数据并打印
                    @Override
                    public void accept(Integer s) {
                        Logger.d("map():" + s);
                    }
                });
    }

    /**
     * RxJava变换操作符---FlatMap()
     * 将Observable发射的数据集合变为Observable集合:即flapMap可以把一个被观察者集合转变成多个被观察者对象
     */
    @SuppressLint("CheckResult")
    private void flatMap() {
        Observable.fromArray(initAuthorList())
                .flatMap(new Function<List<AuthorBean>, ObservableSource<AuthorBean>>() {//input:authorList,
                    @Override
                    public ObservableSource<AuthorBean> apply(List<AuthorBean> authorBeans) throws Exception {
                        return Observable.fromIterable(authorBeans);
                    }
                })
                .flatMap(new Function<AuthorBean, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(AuthorBean authorBean) throws Exception {
                        Log.d(TAG, "作者：" + authorBean.getName());
                        return Observable.fromIterable(authorBean.getArticleList());//articleList转换成Observable<String>
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d(TAG, "文章：" + o.toString());
                    }
                });

    }

    @SuppressLint("CheckResult")
    public void ButtonClick(View view) {
//        mapOperator();
//        flatMap();
        schedulerTest();
    }

    /**
     * 线程调度
     */
    @SuppressLint("CheckResult")
    private void schedulerTest() {
        Observable.fromArray(DataUtil.getIntegerList())
                .flatMap(new Function<List<Integer>, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(List<Integer> integers) throws Exception {
                        return Observable.fromIterable(integers);
                    }
                })
                .subscribeOn(Schedulers.io())//在io线程读取数据，
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "线程切换测试：" + integer);//一条一条发射
                    }
                });
    }

}