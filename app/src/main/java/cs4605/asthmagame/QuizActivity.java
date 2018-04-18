package cs4605.asthmagame;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    private DatabaseHandler db = new DatabaseHandler(this);
    private TextView questionLabel;
    private RadioGroup answerOptions;
    private RadioButton answerButtonOne;
    private RadioButton answerButtonTwo;
    private RadioButton answerButtonThreeOptional;
    private TextView nextQuestionButton;
    private String question;
    private String correctAnswer;
    private String explanation;
    private RadioButton selectedAnswer;
    private int quizCount = 0;
    private static final int QUESTIONS_ASKED = 3; // 3 total questions - 1 for sake of indexing = 2

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionLabel = findViewById(R.id.question_label);
        answerOptions = findViewById(R.id.answer_options);
        answerButtonOne = findViewById(R.id.answer_button_one);
        answerButtonTwo = findViewById(R.id.answer_button_two);
        answerButtonThreeOptional = findViewById(R.id.answer_button_three);

        nextQuestionButton = findViewById(R.id.next_question_button);
        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(view);
            }
        });

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/chalkboard_regular.ttf");
        Typeface boldtype = Typeface.createFromAsset(getAssets(),"fonts/chalkboard_bold.ttf");

        questionLabel.setTypeface(boldtype);
        answerButtonOne.setTypeface(type);
        answerButtonTwo.setTypeface(type);
        answerButtonThreeOptional.setTypeface(type);
        nextQuestionButton.setTypeface(boldtype);

        getQuestions();
        nextQuizQuestion();
    }

    @Override
    public void onBackPressed() {
        Intent activityIntent = new Intent(QuizActivity.this, MainMenuActivity.class);
        startActivity(activityIntent);
        finish();
    }

    private void getQuestions() {
        ArrayList<QuizQuestions> questions = db.getQuizQuestions();

        for (int i = 0; i < QUESTIONS_ASKED; i++) {
            ArrayList<String> array = new ArrayList<>();
            array.add(questions.get(i).get_category()); // Category
            array.add(questions.get(i).get_question()); // Question
            array.add(questions.get(i).get_answer()); // Right answer
            array.add(questions.get(i).get_option2()); // Alternate choice 1
            array.add(questions.get(i).get_option3()); // Alternate choice 2 (optional)
            array.add(questions.get(i).get_explanation()); // Explanation
            array.add(questions.get(i).get_answered_correctly()); // Answered correctly or not ("Y" or "N", default is "N")

            quizArray.add(array);
        }

        nextQuizQuestion();
    }

    public void nextQuizQuestion() {
        answerOptions.clearCheck();

        ArrayList<String> quiz = quizArray.get(quizCount);

        question = quiz.get(1);
        questionLabel.setText(question);
        correctAnswer = quiz.get(2);
        explanation = quiz.get(5);
        ArrayList<String> answers = new ArrayList<>();

        if (quiz.get(4).equals("")) {
            answerButtonThreeOptional.setVisibility(View.INVISIBLE);
            answers.add(quiz.get(2));
            answers.add(quiz.get(3));
            Collections.shuffle(answers);
            answerButtonOne.setText(answers.get(0));
            answerButtonTwo.setText(answers.get(1));
        } else {
            answerButtonThreeOptional.setVisibility(View.VISIBLE);
            answers.add(quiz.get(2));
            answers.add(quiz.get(3));
            answers.add(quiz.get(4));
            Collections.shuffle(answers);
            answerButtonOne.setText(answers.get(0));
            answerButtonTwo.setText(answers.get(1));
            answerButtonThreeOptional.setText(answers.get(2));

        }
    }

    public void checkAnswer(View view) {
        int selectedAnswerId = answerOptions.getCheckedRadioButtonId();
        selectedAnswer = findViewById(selectedAnswerId);
        String selectedAnswerText = selectedAnswer.getText().toString();

        int dialogAlertTitle;
        if (selectedAnswerText.equals(correctAnswer)) {
            dialogAlertTitle = R.drawable.correct_icon;

            //SQL update query to prevent question from being shown again if answered correctly
            db.setQuestionResult("Yes", question);
        } else {
            dialogAlertTitle = R.drawable.wrong_icon;
        }
        LayoutInflater factory = LayoutInflater.from(QuizActivity.this);
        final View v = factory.inflate(R.layout.dialog_format, null);
        TextView text = v.findViewById(R.id.explanation_text);
        text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/chalkboard_bold.ttf"));
        text.setText(explanation);
        ImageView header = v.findViewById(R.id.dialog_header);
        header.setImageDrawable(getResources().getDrawable(dialogAlertTitle));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        builder.setTitle("");
        builder.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (quizCount == QUESTIONS_ASKED - 1) {
                    // TODO: Redirect to results screen where user wins a canister award
                    Intent activityIntent = new Intent(QuizActivity.this, MainMenuActivity.class);
                    startActivity(activityIntent);
                    finish();
                } else {
                    quizCount++;
                    nextQuizQuestion();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        alert.setIcon(getResources().getDrawable(dialogAlertTitle));

        Button button = alert.getButton(AlertDialog.BUTTON_POSITIVE);
        button.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/chalkboard_bold.ttf"));
        button.setTextColor(getResources().getColor(R.color.theme_green));
        button.setTextSize(24);

        alert.getWindow().setBackgroundDrawableResource(R.color.notification_background);
        builder.setCancelable(false);
    }
}
