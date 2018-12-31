package com.example.expansion;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //private ImageView iView;
    //private Button button;
    private float scale = 1f;
    private ScaleGestureDetector detector;

    ViewGroup w_view_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iView=(ImageView)findViewById(R.id.imageView);
        //button = (Button)findViewById(R.id.button);
        detector = new ScaleGestureDetector(this,new ScaleListener());

        String[][] array = new String[10][2];
        // Header部追加
        String[] w_header_array = "勇気,元気,パワー,気合,強い,弱い".split(",");

        // TableLayoutのグループを取得
        w_view_group = (ViewGroup) findViewById(R.id.search_table);

        //行追加
        getLayoutInflater().inflate(R.layout.layout_table, w_view_group);
        TableRow w_table_row = (TableRow) w_view_group.getChildAt(0);


        // 配列分ループ(header部)
        for (int i = 0; i < w_header_array.length; i++) {
            ((TextView) (w_table_row.getChildAt(i))).setText(w_header_array[i]);
            ((TextView) (w_table_row.getChildAt(i))).setTypeface(Typeface.DEFAULT_BOLD);
        }

        // 配列分ループ(header部)
       for (int i = 0; i < 10; i++) {
            getLayoutInflater().inflate(R.layout.layout_table, w_view_group);
        }

        //ここでリスナー設定しとくことで全ボタンに設定できる気がする
        w_view_group.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //これでボタン上でもスワイプが可能
                return detector.onTouchEvent(event);
            }
        });





    }

    public boolean onTouchEvent(MotionEvent event) {
        //re-route the Touch Events to the ScaleListener class
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            w_view_group.setScaleX(scale);
            w_view_group.setScaleY(scale);
            return true;
        }

    }
}