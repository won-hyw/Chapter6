package kr.hs.emirim.w2019.chapter6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chrono1;
    RadioGroup rg;
    CalendarView calendar;
    TimePicker time;
    TextView textResult;
    RadioButton radioCal, radioTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p6_1);

        chrono1 = findViewById(R.id.chrono1);
        rg = findViewById(R.id.rg);
        calendar = findViewById(R.id.calendar);
        time = findViewById(R.id.time_pick);
        textResult = findViewById(R.id.text_result);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnDone = findViewById(R.id.btn_done);

        rg.setOnCheckedChangeListener(radioListener);

        btnStart.setOnClickListener(btnListener);
        btnDone.setOnClickListener(btnListener);
        
        calendar.setOnDateChangeListener(calendarListener);
    }

    RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            calendar.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
            switch (checkedId){
                case R.id.radio_date:
                    calendar.setVisibility(View.VISIBLE);
                    break;
                case R.id.radio_time:
                    time.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_start:
                    chrono1.setBase(SystemClock.elapsedRealtime());
                    chrono1.start();
                    chrono1.setTextColor(Color.RED);
                    break;
                case R.id.btn_done:
                    chrono1.stop();
                    chrono1.setTextColor(Color.BLUE);
                    textResult.setText(y + "년 " + m + "월 " + d + "일 ");
                    textResult.append(time.getCurrentHour() + "시 " + time.getCurrentMinute() + "분 예약완료됨");
                    break;
            }
        }
    };
    
    int y, m, d;
    
    CalendarView.OnDateChangeListener calendarListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            y = year;
            m = month+1; // 0부터 시작하므로 1을 더해줘야 한다.
            d = dayOfMonth;
        }
    };
}