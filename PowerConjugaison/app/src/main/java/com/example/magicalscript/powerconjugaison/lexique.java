package com.example.magicalscript.powerconjugaison;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by Abdellah on 27/08/2015.
 */
public class lexique extends Activity{
    //selected_url selectedUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lexique);

        //String txt = "<font face=\"Comic Sans MS\"><span style=\"font-size:9pt\">Le verbe constitue </span></font><font face=\"Comic Sans MS\"><span style=\"font-size:9pt\"><b>le noyau de la phrase</b></span></font><font face=\"Comic Sans MS\"><span style=\"font-size:9pt\">. C'est autour de lui qu'elle s'organise. Le verbe est le seul mot qui varie en personne, en temps, en mode et en voix : </span></font><font face=\"Comic Sans MS\"><span style=\"font-size:9pt\"><b>il se conjugue.</b></span></font><font face=\"Comic Sans MS\"><span style=\"font-size:9pt\"><br>\n" +
        //                       "</body>\n";
        //String t=getResources().getString(R.string.ConjBtn);
        //((TextView)(findViewById(R.id.textView))).setText(Html.fromHtml(txt));


    }

    private void showLesson(String url){
        try {
            Intent intent = new Intent(this, lexique_lesson.class);

            intent.putExtra("url", url);
            startActivity(intent);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void BtnClk(View v){

        switch(v.getId()){
            case R.id.bn1:
                showLesson("file:///android_asset/1.html");
                break;
            case R.id.bn2:
                showLesson("file:///android_asset/2.html");
                break;
            case R.id.bn3:
                showLesson("file:///android_asset/3.html");
                break;
            case R.id.bn4:
                showLesson("file:///android_asset/4.html");
                break;
            case R.id.bn5:
                showLesson("file:///android_asset/5.html");
                break;
            case R.id.bn6:
                showLesson("file:///android_asset/6.html");
                break;
            case R.id.bn7:
                showLesson("file:///android_asset/7.html");
                break;
            case R.id.bn8:
                showLesson("file:///android_asset/8.html");
                break;
            case R.id.bn9:
                showLesson("file:///android_asset/9.html");
                break;
            case R.id.bn10:
                showLesson("file:///android_asset/10.html");
                break;
            case R.id.bn11:
                showLesson("file:///android_asset/11.html");
                break;
            case R.id.bn12:
                showLesson("file:///android_asset/12.html");
                break;
            case R.id.bn13:
                showLesson("file:///android_asset/13.html");
                break;
            case R.id.bn14:
                showLesson("file:///android_asset/14.html");
                break;
            case R.id.bn15:
                showLesson("file:///android_asset/15.html");
                break;
            case R.id.bn16:
                showLesson("file:///android_asset/16.html");
                break;
            case R.id.bn17:
                showLesson("file:///android_asset/17.html");
                break;
            case R.id.bn18:
                showLesson("file:///android_asset/18.html");
                break;
            case R.id.bn19:
                showLesson("file:///android_asset/19.html");
                break;
            case R.id.bn20:
                showLesson("file:///android_asset/20.html");
                break;
            case R.id.bn21:
                showLesson("file:///android_asset/21.html");
                break;
        }

    }
    //interface selected_url{
    //    public void _url(String url);
    //}
}
