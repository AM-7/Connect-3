package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;
    int numberOfTurns=0;
    int[][] winningStates={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    int[] currentState={2,2,2,2,2,2,2,2,2};
    boolean gameActive=true;


    public void dropIn(View view){

        ImageView counter=(ImageView) view;

        int tapped=Integer.parseInt(counter.getTag().toString());
        if(currentState[tapped]==2 && gameActive){

            currentState[tapped]=activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer==0){
                counter.setImageResource(R.drawable.o);
                numberOfTurns=numberOfTurns+1;

                activePlayer=1;

            }
            else{
                counter.setImageResource(R.drawable.x);
                numberOfTurns=numberOfTurns+1;

                activePlayer=0;
            }

            counter.animate().translationYBy(1500).setDuration(300);

            for(int[] winning : winningStates){

                if(currentState[winning[0]]==currentState[winning[1]] && currentState[winning[1]]==currentState[winning[2]]&& currentState[winning[0]]!=2){

                    String winner="";

                    if (activePlayer==0){
                        winner="Red";


                    }
                    else{
                        winner="Black";
                    }

                    Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                    TextView textView=(TextView)findViewById(R.id.textView);
                    textView.setText(winner+" has won!");
                    playAgainButton.setAlpha(1);
                    textView.setVisibility(View.VISIBLE);


                    gameActive=false;


                }
                else if(numberOfTurns==9){

                    gameActive=false;
                    Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                    TextView textView=(TextView)findViewById(R.id.textView);
                    textView.setText(" Its a tie!");
                    playAgainButton.setAlpha(1);
                    textView.setVisibility(View.VISIBLE);
                  ;


                }

            }

        }





    }
    public void playAgain(View view){
        Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
        TextView textView=(TextView)findViewById(R.id.textView);

        playAgainButton.setAlpha(0);
        textView.setVisibility(View.INVISIBLE);


        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

        for (int i=0;i<currentState.length;i++){
            currentState[i]=2;

        }
        numberOfTurns=0;
        activePlayer=0;
        gameActive=true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}