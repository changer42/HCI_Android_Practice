package changhe.cs185.cs.ucsb.edu.changhescores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final String TEAM_ONE = "Team 1 Name";
    public static final String TEAM_TWO = "Team 2 Name";
    private DatePicker mDatePicker;
    private Button mButton;
    private LinearLayout mTeamAndScores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up datePicker
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
        datePickerSetUp(mDatePicker);
        setDefaultDate(mDatePicker);

        //set up button
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefaultDate(mDatePicker);
                final TeamAndScoreFragment frag1 =
                        (TeamAndScoreFragment) getSupportFragmentManager().findFragmentByTag(TEAM_ONE);
                frag1.setDefaultParam();
                final TeamAndScoreFragment frag2 =
                        (TeamAndScoreFragment) getSupportFragmentManager().findFragmentByTag(TEAM_TWO);
                frag2.setDefaultParam();
            }
        });

        //inflate fragments
        mTeamAndScores = (LinearLayout) findViewById(R.id.teamAndScores);

        //check instance state
        if(savedInstanceState == null){
            inflateFragment();
        }
    }

    private void inflateFragment(){
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction ft = fragmentManager.beginTransaction();

        final TeamAndScoreFragment teamOne = TeamAndScoreFragment.newInstance(TEAM_ONE);
        final TeamAndScoreFragment teamTwo = TeamAndScoreFragment.newInstance(TEAM_TWO);

        ft.add(mTeamAndScores.getId(), teamOne, TEAM_ONE);
        ft.add(mTeamAndScores.getId(), teamTwo, TEAM_TWO);

        ft.commit();
    }

    // TODO: should be in a viewer class
    public void datePickerSetUp(DatePicker datePicker) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
        final Date startDate, endDate;
        try {
            startDate = dateFormat.parse("Aug 16 2014");
            endDate = dateFormat.parse("May 25 2015");
            datePicker.setMinDate(startDate.getTime());
            datePicker.setMaxDate(endDate.getTime());
        } catch (ParseException e) {
            Log.e(this.getLocalClassName(), "Can't parse date");
            e.printStackTrace();
        }
    }

    // TODO: should be in a viewer class
    public void setDefaultDate(DatePicker datePicker){
        if(datePicker == null){
            Log.e(this.getLocalClassName(),"Failed update date to default date due to null opbject");
        }else {
            datePicker.updateDate(2014, 7, 16);
        }
    }

}
