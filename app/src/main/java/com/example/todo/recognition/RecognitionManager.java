package com.example.todo.recognition;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.example.todo.MainActivity;

public class RecognitionManager implements  LanguageAvailabilityListener{

    private SpeechRecognizer mSpeechRecognizer;
    private Activity mActivity;

    public RecognitionManager(MainActivity act)
    {
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(act);
        mSpeechRecognizer.setRecognitionListener(act);
        mActivity = act;
    }

    public void start() {
        Intent detailsIntent = new Intent(RecognizerIntent.ACTION_GET_LANGUAGE_DETAILS);
        mActivity.sendOrderedBroadcast(detailsIntent, null, new LanguageDetailsChecker(this), null, Activity.RESULT_OK, null, null);
    }

    public void destroy()
    {
        if(mSpeechRecognizer != null)
        {
            mSpeechRecognizer.destroy();
        }
    }

    @Override
    public void onLanguageAvailabilityCheck(boolean availability) {
        if(availability)
        {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en-US");
            intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "en-US");

            intent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{"en-US"});

            mSpeechRecognizer.startListening(intent);
        }
        else
        {
            Toast.makeText(mActivity, "Voice recognition is not supported on the device.", Toast.LENGTH_SHORT).show();
        }
    }
}
