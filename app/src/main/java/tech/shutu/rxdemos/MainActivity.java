package tech.shutu.rxdemos;

<<<<<<< HEAD
import android.nfc.Tag;
=======
>>>>>>> 0a803e53823dc9d0b68f5f94fb289386284cddb4
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
<<<<<<< HEAD

import java.sql.ResultSet;
=======
import android.widget.Toast;

>>>>>>> 0a803e53823dc9d0b68f5f94fb289386284cddb4
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static String[] titleList = {"create", "just", "from", "flatMap", "timer", "interval", "threads", "zip"};

    private ListView listView;
    private RxListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transformingType(view);
            }
        });
        listView = (ListView) findViewById(R.id.lv_main);
        listView.setAdapter(new RxListAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch ((String) parent.getAdapter().getItem(position)) {
                    case "create":
                        createOperatorCreate(view);
                        break;
                    case "just":
                        createOpJust(fab);
                        break;
                    case "from":
                        createOpFrom(fab);
                        break;
                    case "flatMap":
                        transformingType(fab);
                        break;
                    case "timer":
                        createTimer(fab);
                        break;
                    case "interval":
                        createInterval(fab);
                        break;
                    case "threads":
                        crossThreads();
                        break;
                    case "zip":
                        createZIP();
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createInterval(final View view) {
        Observable.interval(3000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return "我是 Interval" + String.valueOf(aLong);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String result) {
                        Snackbar.make(view, result + "", Snackbar.LENGTH_LONG).show();

                    }
                });
    }

    private void createTimer(final View view) {
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return "我是 Timer = " + String.valueOf(aLong);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String result) {
                        Snackbar.make(view, result + "", Snackbar.LENGTH_LONG).show();
                    }
                });
    }


    public void createOperatorCreate(final View view) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
<<<<<<< HEAD
                subscriber.onNext("this is create");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String result) {
                Snackbar.make(view, result + "", Snackbar.LENGTH_LONG).show();
            }
        });
=======
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("我是创建者传过来的参数");
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onNext(String s) {
                                   Toast.makeText(MainActivity.this, s + " subscriber", Toast.LENGTH_SHORT).show();
                               }
                           }
                /*
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(MainActivity.this, s + " action", Toast.LENGTH_SHORT).show();
                    }
                }
                */
                );
>>>>>>> 0a803e53823dc9d0b68f5f94fb289386284cddb4
    }

    public void createOpJust(final View view) {
        Observable.just("Hello World!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            s += "I'm the god!";
                        }
                        return s;
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "1";
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "2";
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "3";
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "4";
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String result) {
                        Snackbar.make(view, result + "", Snackbar.LENGTH_LONG).show();
                    }
                });


    }

    private Observable<String> getResult(String s) {
        return Observable.just(s + " this is post result");
    }

    private void createOpFrom(final View view) {
        Observable.from(titleList)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String result) {
                        Log.e(TAG, "result ==== " + result);
                    }
                });
    }

    public void transformingType(final View view) {
        Observable.just("post params")
                .flatMap(new Func1<String, Observable<?>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return getResult(s);
                    }
                }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Object o) {
                if (o instanceof String) {
                    Snackbar.make(view, o + "", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * E/MainActivity: 我在 (1)线程: 30409
     * E/MainActivity: 我在 (1)线程: 30409 (2)线程: 30408
     * E/MainActivity: 我在 (1)线程: 30409 (2)线程: 30408 (3)线程: 30407
     * E/MainActivity: 我在 (1)线程: 30409 (2)线程: 30408 (3)线程: 30407   (4)线程: 30406
     * E/MainActivity: 我在 (1)线程: 30409 (2)线程: 30408 (3)线程: 30407   (4)线程: 30406   (5)线程: 30405
     * E/MainActivity: 我在 (1)线程: 30409 (2)线程: 30408 (3)线程: 30407   (4)线程: 30406   (5)线程: 30405   (6)线程: 30405
     */
    public void crossThreads() {
        Observable.just("我在")
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        s += " (1)线程: " + Thread.currentThread().getId();
                        Log.e(TAG, s);
                        return s;
                    }
                })
                .observeOn(Schedulers.computation())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        s += " (2)线程: " + Thread.currentThread().getId();
                        Log.e(TAG, s);
                        return s;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        s += " (3)线程: " + Thread.currentThread().getId();
                        Log.e(TAG, s);
                        return s;
                    }
                })
                .observeOn(Schedulers.computation())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        s += "   (4)线程: " + Thread.currentThread().getId();
                        Log.e(TAG, s);
                        return s;
                    }
                })
                .observeOn(Schedulers.newThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        s += "   (5)线程: " + Thread.currentThread().getId();
                        Log.e(TAG, s);
                        return s;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        s += "   (6)线程: " + Thread.currentThread().getId();
                        Log.e(TAG, s);
                    }
                });
    }

    public void createZIP() {
        Observable.zip(Observable.just("我是第一个观察者"), Observable.just("我是第二个观察者"),
                new Func2<String, String, String>() {
                    @Override
                    public String call(String s1, String s2) {
                        Log.e(TAG, "  s1 = " + s1 + " , s2 = " + s2);
                        return s1 + s2;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String result) {
                        Log.e(TAG, "result = " + result);
                    }
                });
    }

<<<<<<< HEAD
=======

>>>>>>> 0a803e53823dc9d0b68f5f94fb289386284cddb4
    public static class RxListAdapter extends BaseAdapter {
        private List<String> list = Arrays.asList(titleList);

        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item_1, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (list != null && list.size() > 0) {
                holder.text1.setText(list.get(position));
            }
            return convertView;
        }

        static class ViewHolder {
            @BindView(android.R.id.text1)
            TextView text1;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
