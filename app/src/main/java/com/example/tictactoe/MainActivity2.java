package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.XMLFormatter;
public class MainActivity2 extends AppCompatActivity {
    boolean gameActive=true;
    public static int counter=0;
    int activePlayer=0;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},
                          {1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void playerTap(View view) {
        ImageView img=(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(!gameActive) {
            gameReset(view);
        }
        Button button1 =findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameReset(view);
            }
        });
        if(gameState[tappedImage]==2) {
            counter++;
            if(counter==9){
                gameActive=false;
            }
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-5000f);
            if(activePlayer ==0) {
                img.setImageResource(R.drawable.cross);
                activePlayer=1;
                TextView status=findViewById(R.id.status);
                status.setText("O's Turn To Play");
            } else
            {
                img.setImageResource(R.drawable.circle);
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                status.setText("X's Turn To Play");
            }
            img.animate().translationYBy(5000f).setDuration(50);
        }
        int f=0;
        for(int [] winPoss:winPos) {
            if (gameState[winPoss[0]] == gameState[winPoss[1]] &&
                    gameState[winPoss[2]] == gameState[winPoss[1]] &&
                    gameState[winPoss[0]] != 2) {
                f = 1;
                gameActive = false;
                if (gameState[winPoss[0]] == 0) {
                    Intent myIntnt=new Intent(MainActivity2.this,MainActivity4.class);
                    startActivity(myIntnt);
                    counter = 0;
                } else {
                    Intent myIntnt=new Intent(MainActivity2.this,MainActivity4.class);
                    startActivity(myIntnt);
                    counter = 0;
                }
            }
        }
        if(counter==9 && f==0)
        {
            TextView status=findViewById(R.id.status);
            status.setText("That A Draw");
        }
    }
    public void gameReset(View view){
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap To Play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}