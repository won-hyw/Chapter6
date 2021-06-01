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
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chrono1;
    RadioGroup rg;
    DatePicker datePick;
    TimePicker time;
    TextView textResult;
    RadioButton radioCal, radioTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p6_1);

        chrono1 = findViewById(R.id.chrono1);
        rg = findViewById(R.id.rg);
        datePick = findViewById(R.id.date_pick);
        time = findViewById(R.id.time_pick);
        textResult = findViewById(R.id.text_result);

        chrono1.setOnClickListener(chronoListener);
        textResult.setOnLongClickListener(textListener);
        rg.setOnCheckedChangeListener(radioListener);

        // calendar.setOnDateChangeListener(calendarListener);
    }

    RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            datePick.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
            switch (checkedId){
                case R.id.radio_date:
                    datePick.setVisibility(View.VISIBLE);
                    break;
                case R.id.radio_time:
                    time.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    View.OnClickListener chronoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            chrono1.setBase(SystemClock.elapsedRealtime());
            chrono1.start();
            chrono1.setTextColor(Color.RED);
            rg.setVisibility(View.VISIBLE);
            datePick.setVisibility(View.VISIBLE);
        }
    };

    View.OnLongClickListener textListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            chrono1.stop();
            chrono1.setTextColor(Color.BLUE);
            textResult.setText(datePick.getYear() + "년 " + (datePick.getMonth()+1) + "월 " + datePick.getDayOfMonth() + "일 ");
            textResult.append(time.getCurrentHour() + "시 " + time.getCurrentMinute() + "분 예약완료됨");
            rg.setVisibility(View.INVISIBLE);
            datePick.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
            return false;
        }
    };
}