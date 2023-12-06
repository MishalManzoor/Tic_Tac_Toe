package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictactoegame.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding _binding;
    boolean isGameActive;
    boolean executed;
    int i = 0, j = 0;
    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int [][] winPosition = {{0 , 1 , 2}, {3, 4 , 5}, {6 , 7 ,8} , //horizontal win positions
            {0 , 4 , 8}, {2 , 4 , 6} , //diagonal win positions
            {0 , 3 , 6} , {1 , 4 , 7} , {2 , 5 , 8} //vertical win positions
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
    }

    @SuppressLint("SetTextI18n")
    public void PlayerTap(View v){
        ImageView image = (ImageView) v;

        // get the tag
        int tapImage = Integer.parseInt(image.getTag().toString());

        if(!isGameActive){
            resetGame(v);
        }
        if (gameState[tapImage] == 2 && isGameActive){
            gameState[tapImage] = activePlayer;

            if (activePlayer == 0){
                image.setImageResource(R.drawable.x);
                activePlayer = 1;
                _binding.status.setText("0's turn - Tap to play");
            }
            else {
                image.setImageResource(R.drawable.zero);
                activePlayer = 0;
                _binding.status.setText("X's turn - Tap to play");
            }
        }

        for(int [] win : winPosition) {

            if (gameState[win[0]] == gameState[win[1]] &&
             gameState[win[1]] == gameState[win[2]] &&
             gameState[win[0]] != 2){

                isGameActive = false;
                String winnerStr;
                if (gameState[win[0]] == 1){
                    i++;
                    winnerStr = "0 has won!";
                    String counter = "0 = "+i;
                    _binding.player1.setText(counter);
                 }
                else{
                    j++;
                    winnerStr = "X has won!";
                    String counter1 = "X = "+j;
                    _binding.player2.setText(counter1);
                }
                _binding.status.setText(winnerStr);
            }
        }
        // following code block checks if all boxes are occupied
        boolean isSquareEmpty = false;
        for(int check : gameState){
            if (check == 2){
                isSquareEmpty = true;
                break;
            }
        }
        if (isGameActive && !isSquareEmpty){
            _binding.status.setText("It's a Draw");
        }
    }

    public void resetGame(View v){
        isGameActive = true;
        activePlayer = 0;
        i = 0;
        j = 0;

        String counter = "0 = "+i;
        _binding.player1.setText(counter);

        String counter1 = "X = "+j;
        _binding.player2.setText(counter1);

        _binding.status.setText("");

        // set all the boxes empty
        for(int i = 0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        // set all the images to null
        _binding.image0.setImageResource(0);
        _binding.image1.setImageResource(0);
        _binding.image2.setImageResource(0);
        _binding.image3.setImageResource(0);
        _binding.image4.setImageResource(0);
        _binding.image5.setImageResource(0);
        _binding.image6.setImageResource(0);
        _binding.image7.setImageResource(0);
        _binding.image8.setImageResource(0);
    }
}
