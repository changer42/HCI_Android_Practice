package changhe.cs185.cs.ucsb.edu.changhescores;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

/**
 * Created by Hilda on 05/02/2017.
 */

public class TeamAndScoreFragment extends Fragment {
    public static final String TEAM_NAME_HINT = "teamHint";
    private AutoCompleteTextView mTeamName;
    private EditText mTeamScore;
    private String[] mTeams;
    private ArrayAdapter<String> mAdapter;
    private String mTeamNameHint;

    //default constructor
    public TeamAndScoreFragment() {

    }
    //actual constractor
    public static TeamAndScoreFragment newInstance(String teamName) {
        TeamAndScoreFragment fragment = new TeamAndScoreFragment();
        Bundle args = new Bundle();
        args.putString(TEAM_NAME_HINT, teamName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTeams = getResources().getStringArray(R.array.Teams);
        mAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_list_item_1, mTeams);
        if (getArguments() != null) {
            mTeamNameHint = getArguments().getString(TEAM_NAME_HINT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.team_and_score_view, container, false);
        mTeamName = (AutoCompleteTextView) view.findViewById(R.id.teamName);
        mTeamScore = (EditText) view.findViewById(R.id.teamScore);
        mTeamName.setAdapter(mAdapter);
        if (mTeamNameHint != null) {
            mTeamName.setHint(mTeamNameHint);
        }
        return view;
    }


    public void setDefaultParam(){
        mTeamScore.setText("");
        mTeamName.setText("");
        mTeamName.setHint(mTeamNameHint);
        mTeamScore.setHint("0");
    }
}
