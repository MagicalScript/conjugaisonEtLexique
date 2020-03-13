package com.example.magicalscript.powerconjugaison;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.renderscript.Script;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
//import android.widget.ExpandableListAdapter;

import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

//import univ.biskra.french.ConjugateResult;
//import univ.biskra.french.getElementConjugation;
//import univ.biskra.french.VerbTemplate;
import com.gtranslate.Language;
import com.gtranslate.Translator;

import org.jdom.Element;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks , ZoomEvent {

//declaration


    @Override
    public void ZoomIN() {
        _ZoomIn();
        try{ShowData();}catch(Exception e){
            e.printStackTrace();}

    }

    @Override
    public void ZoomOut() {
        _ZoomOut();
        try{ShowData();}catch(Exception e){
            e.printStackTrace();}
    }

    List<String> listDataHeader;
    HashMap<String ,List<String>> ListTenses = new HashMap<String ,List<String>>();
    List<String> listDataHeader1;
    HashMap<String ,List<String>> ListTenses1 = new HashMap<String ,List<String>>();
    List<String> listDataHeader2;
    HashMap<String ,List<String>> ListTenses2 = new HashMap<String ,List<String>>();
    List<String> listDataHeader3;
    HashMap<String ,List<String>> ListTenses3 = new HashMap<String ,List<String>>();
    List<String> listDataHeader4;
    HashMap<String ,List<String>> ListTenses4 = new HashMap<String ,List<String>>();


    ExpandableListAdapter listAdapter;
    ExpandableListView ExpList;
//declaration
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //Conjuguer("String");



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                onSectionAttached(1);
                Intent intent = new Intent(this,lexique.class);
                startActivity(intent);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int X=26;
    public int Y=16;
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
         ZoomEvent zoomEvent ;


        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }



        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ZoomControls zoomCtr = (ZoomControls)rootView.findViewById(R.id.zoomControls);
            zoomCtr.setOnZoomInClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zoomEvent.ZoomIN();
                }
            });
            zoomCtr.setOnZoomOutClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zoomEvent.ZoomOut();
                }
            });
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
            try{
                zoomEvent = (ZoomEvent)activity;
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    public void _ZoomIn(){
        X+=2;
        Y+=2;
    }
    public void _ZoomOut(){
        if(Y>10){
            X-=2;
            Y-=2;
        }
    }


    Thread threadConjug = new Thread();
    Thread threadLoudDataConjug = new Thread();
    public void ConjugBtn(View v){

        if(!threadConjug.isAlive()){
            try{
                threadConjug =new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String verb = ((TextView)(findViewById(R.id.editText))).getText().toString();
                        Conjuguer(verb.toLowerCase());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
                threadConjug.start();}
            catch(Exception e){
                e.printStackTrace();}

        }


        //new ConjugTaitment().execute(verb.toLowerCase());

    }

    VerbTemplate vt;
    getElementConjugation gec;

    public void Conjuguer(String verb){
        try {

            while(threadLoudDataConjug.isAlive())
                wait(200);
            LoudConjugData();

            String[] pronoms = {"Je","Tu","Il/Elle","Nous","Vous","Ils/Elles","J'"};
            String template = vt.getTemplate(verb);
            if(!template.trim().equals("")){
                listDataHeader = new ArrayList<String>();
                listDataHeader1 = new ArrayList<String>();
                listDataHeader2 = new ArrayList<String>();
                listDataHeader3 = new ArrayList<String>();
                listDataHeader4 = new ArrayList<String>();

                Element ElemVerb = gec.getElemConjigaison(template);

                ConjugateResult cr = new ConjugateResult(ElemVerb,verb);

                //***************************indicative*************************

                listDataHeader.add(getResources().getString(R.string.present));

                listDataHeader.add(getResources().getString(R.string.imperfect));
                listDataHeader.add(getResources().getString(R.string.simplePast));
                listDataHeader.add(getResources().getString(R.string.passeCompose));
                listDataHeader.add("Plus-que-parfait");
                listDataHeader.add("Passé Antérieur");
                listDataHeader.add(getResources().getString(R.string.future));
                listDataHeader.add("Futur Antérieur");


                String[] a = cr.Conjug("indicative","present");
                List<String> IndPresent=new ArrayList<String>();
                IndPresent.add("<font  color='#FF4B0C'>Present</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        IndPresent.add((new StringBuilder()).append(pronoms[Je(a[i])]).append(" ").append(a[i]).append("<br>").toString());
                        //IndPassComp.add(Html.fromHtml ((new StringBuilder()).append(pronoms[Je(a[i])]).append(a[i]).append(" ").append(_a).append("<br>").toString()));

                    else
                    //IndPresent.add(Html.fromHtml ((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append("<br>").toString()));
                    IndPresent.add((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append("<br>").toString());

                }

                ListTenses.put(listDataHeader.get(0),IndPresent);

                String _template = vt.getTemplate("avoir");
                Element _ElemVerb = gec.getElemConjigaison(_template);
                ConjugateResult _cr = new ConjugateResult(_ElemVerb,"avoir");

                String _a = cr.Conjug("participle","past-participle")[0];



                a = cr.Conjug("indicative","imperfect");
                List<String> IndImparf=new ArrayList<String>();
                IndImparf.add("<font style='font-size:16px' color='#FF4B0C'>Imparfait</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                    IndImparf.add((new StringBuilder()).append(pronoms[Je(a[i])]).append(" ").append(a[i]).append("<br>").toString());
                            else
                    IndImparf.add((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append("<br>").toString());
                    //IndImparf.add(Html.fromHtml((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append("<br>").toString()));

                }

                ListTenses.put(listDataHeader.get(1),IndImparf);

                a = cr.Conjug("indicative","simple-past");
                List<String> IndPassSimple=new ArrayList<String>();
                IndPassSimple.add("<font style='font-size:16px' color='#FF4B0C'>Passé Simple</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        IndPassSimple.add((new StringBuilder()).append(pronoms[Je(a[i])]).append(" ").append(a[i]).append("<br>").toString());
                    else
                        //IndPassSimple.add(Html.fromHtml((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append("<br>").toString()));
                        IndPassSimple.add((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append("<br>").toString());
                }

                ListTenses.put(listDataHeader.get(2),IndPassSimple);

                a = _cr.Conjug("indicative","present");
                List<String> IndPassComp=new ArrayList<String>();
                IndPassComp.add("<font  color='#FF4B0C'>Passé Composé</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        IndPassComp.add((new StringBuilder()).append(pronoms[Je(a[i])]).append(" ").append(a[i]).append(" ").append(_a).append("<br>").toString());
                        //IndPassComp.add(Html.fromHtml ((new StringBuilder()).append(pronoms[Je(a[i])]).append(a[i]).append(" ").append(_a).append("<br>").toString()));

                    else
                        //IndPassComp.add(Html.fromHtml((new StringBuilder()).append(pronoms[i]).append(a[i]).append(" ").append(_a).append("<br>").toString()));
                        IndPassComp.add((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append(" ").append(_a).append("<br>").toString());

                }

                ListTenses.put(listDataHeader.get(3),IndPassComp);

                a = _cr.Conjug("indicative","imperfect");
                List<String> IndPlusQue=new ArrayList<String>();
                IndPlusQue.add("<font  color='#FF4B0C'>Plus-Que-parfait</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        IndPlusQue.add((new StringBuilder()).append(pronoms[Je(a[i])]).append(" ").append(a[i]).append(" ").append(_a).append("<br>").toString());
                        //IndPassComp.add(Html.fromHtml ((new StringBuilder()).append(pronoms[Je(a[i])]).append(a[i]).append(" ").append(_a).append("<br>").toString()));

                    else
                        //IndPassComp.add(Html.fromHtml((new StringBuilder()).append(pronoms[i]).append(a[i]).append(" ").append(_a).append("<br>").toString()));
                        IndPlusQue.add((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append(" ").append(_a).append("<br>").toString());

                }

                ListTenses.put(listDataHeader.get(4),IndPlusQue);


                a = _cr.Conjug("indicative","simple-past");
                List<String> IndPassAnt=new ArrayList<String>();
                IndPassAnt.add("<font  color='#FF4B0C'>Passé Antrérieur</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        IndPassAnt.add((new StringBuilder()).append(pronoms[Je(a[i])]).append(" ").append(a[i]).append(" ").append(_a).append("<br>").toString());
                        //IndPassComp.add(Html.fromHtml ((new StringBuilder()).append(pronoms[Je(a[i])]).append(a[i]).append(" ").append(_a).append("<br>").toString()));

                    else
                        //IndPassComp.add(Html.fromHtml((new StringBuilder()).append(pronoms[i]).append(a[i]).append(" ").append(_a).append("<br>").toString()));
                        IndPassAnt.add((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append(" ").append(_a).append("<br>").toString());

                }

                ListTenses.put(listDataHeader.get(5),IndPassAnt);



                a = cr.Conjug("indicative","future");
                List<String> IndFutur=new ArrayList<String>();
                IndFutur.add("<font  color='#FF4B0C'>Futur</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        IndFutur.add((new StringBuilder()).append(pronoms[Je(a[i])]).append(" ").append(a[i]).append("<br>").toString());
                        else
                    //IndFutur.add(Html.fromHtml((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append("<br>").toString()));
                    IndFutur.add((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append("<br>").toString());

                }

                ListTenses.put(listDataHeader.get(6),IndFutur);


                a = _cr.Conjug("indicative","future");
                List<String> IndFutAnt=new ArrayList<String>();
                IndFutAnt.add("<font  color='#FF4B0C'>Futur Antérieur</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        IndFutAnt.add((new StringBuilder()).append(pronoms[Je(a[i])]).append(" ").append(a[i]).append(" ").append(_a).append("<br>").toString());
                        //IndPassComp.add(Html.fromHtml ((new StringBuilder()).append(pronoms[Je(a[i])]).append(a[i]).append(" ").append(_a).append("<br>").toString()));

                    else
                        //IndPassComp.add(Html.fromHtml((new StringBuilder()).append(pronoms[i]).append(a[i]).append(" ").append(_a).append("<br>").toString()));
                        IndFutAnt.add((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append(" ").append(_a).append("<br>").toString());

                }

                ListTenses.put(listDataHeader.get(7),IndFutAnt);






                //***************************conditional*************************

                listDataHeader1.add("present");

                a = cr.Conjug("conditional","present");
                List<String> ConditPresent = new ArrayList<String>();
                ConditPresent.add("<font style='font-size:16px' color='#FF4B0C'>Présent</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        ConditPresent.add((new StringBuilder()).append(pronoms[Je(a[i])]).append(" ").append(a[i]).append("<br>").toString());
                        else
                    ConditPresent.add((new StringBuilder()).append(pronoms[i]).append(" ").append(a[i]).append("<br>").toString());
                }
                ListTenses1.put(listDataHeader1.get(0),ConditPresent);



                //***************************subjunctive*************************

                listDataHeader2.add("present");
                listDataHeader2.add("imperfect");


                String[] pronomsSub = {"que Je"," que Tu","qu'Il/Elle","que Nous","que Vous","qu'Ils/Elles","que J'"};

                a = cr.Conjug("subjunctive","present");
                List<String> SubjPresent = new ArrayList<String>();
                SubjPresent.add("<font style='font-size:16px' color='#FF4B0C'>Présent</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        SubjPresent.add((new StringBuilder()).append(pronomsSub[Je(a[i])]).append(" ").append(a[i]).append("<br>").toString());
                        else
                    SubjPresent.add((new StringBuilder()).append(pronomsSub[i]).append(" ").append(a[i]).append("<br>").toString());
                }
                ListTenses2.put(listDataHeader2.get(0),SubjPresent);

                a = cr.Conjug("subjunctive","imperfect");
                List<String> SubjImparf = new ArrayList<String>();
                SubjImparf.add("<font style='font-size:16px' color='#FF4B0C'>Imparfait</font>");
                for(int i =0;i<a.length;i++){
                    if(i==0)
                        SubjImparf.add((new StringBuilder()).append(pronomsSub[Je(a[i])]).append(" ").append(a[i]).append("<br>").toString());
                        else
                    SubjImparf.add((new StringBuilder()).append(pronomsSub[i]).append(" ").append(a[i]).append("<br>").toString());
                }
                ListTenses2.put(listDataHeader2.get(1),SubjImparf);



                //***************************imperative*************************

                listDataHeader3.add("imperative-present");


                a = cr.Conjug("imperative","imperative-present");
                List<String> ImperPresent = new ArrayList<String>();
                ImperPresent.add("<font style='font-size:16px' color='#FF4B0C'>Présent</font>");
                for(int i =0;i<a.length;i++){
                    ImperPresent.add((new StringBuilder()).append(" ").append(a[i]).append("<br>").toString());
                }
                ListTenses3.put(listDataHeader3.get(0),ImperPresent);



                //***************************participle*************************

                listDataHeader4.add("present-participle");
                listDataHeader4.add("past-participle");

                a = cr.Conjug("participle","present-participle");
                List<String> particPresent = new ArrayList<String>();
                particPresent.add("<font style='font-size:16px' color='#FF4B0C'>Présent</font>");
                for(int i =0;i<a.length;i++){
                    particPresent.add((new StringBuilder()).append(" ").append(a[i]).append("<br>").toString());
                }ListTenses4.put(listDataHeader4.get(0),particPresent);

                a = cr.Conjug("participle","past-participle");
                List<String> particPast = new ArrayList<String>();
                particPast.add("<font style='font-size:16px' color='#FF4B0C'>Passé</font>");
                for(int i =0;i<a.length;i++){
                    particPast.add((new StringBuilder()).append(" ").append(a[i]).append("<br>").toString());
                }ListTenses4.put(listDataHeader4.get(1),particPast);




                ShowData();

            }else runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getBaseContext(), "Ce verbe n'exist pas. Vérifiez son ortographe.", Toast.LENGTH_LONG).show();
                }
            })
           ;


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ShowData(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final int adapterGCount = listDataHeader.size();
                try {
                    ((LinearLayout)(findViewById(R.id.present))).removeAllViews();

                    String tyt;
                    for (int i = 0; i < adapterGCount; i++) {

                        int adapterChCount = ListTenses.get(listDataHeader.get(i)).size();
                        tyt="";
                        TextView __tv = new TextView(getApplication());
                        __tv.setText(Html.fromHtml(ListTenses.get(listDataHeader.get(i)).get(0)));
                        __tv.setTextSize(X);
                        ((LinearLayout)(findViewById(R.id.present))).addView(__tv);
                        for (int j = 1; j < adapterChCount; j++) {
                            tyt+=ListTenses.get(listDataHeader.get(i)).get(j);
                        }
                        TextView _tv = new TextView(getApplication());
                        _tv.setTextSize(Y);
                        _tv.setTextColor(Color.BLACK);
                        _tv.setText(Html.fromHtml(tyt));
                        ((LinearLayout)(findViewById(R.id.present))).addView(_tv);
                    }
                }catch (Exception e)
                {e.printStackTrace();}
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final int adapterGCount = listDataHeader1.size();
                try {
                    ((LinearLayout)(findViewById(R.id.conditional))).removeAllViews();
                }catch (Exception e){
                    e.printStackTrace();}
                String tyt;
                for (int i = 0; i < adapterGCount; i++) {

                    int adapterChCount = ListTenses1.get(listDataHeader1.get(i)).size();
                    tyt="";
                    TextView __tv = new TextView(getApplication());
                    __tv.setText(Html.fromHtml(ListTenses1.get(listDataHeader1.get(i)).get(0)));
                    __tv.setTextSize(X);
                    ((LinearLayout)(findViewById(R.id.conditional))).addView(__tv);
                    for (int j = 1; j < adapterChCount; j++) {
                        tyt+=ListTenses1.get(listDataHeader1.get(i)).get(j);
                    }
                    TextView _tv = new TextView(getApplication());
                    _tv.setTextSize(Y);
                    _tv.setTextColor(Color.BLACK);
                    _tv.setText(Html.fromHtml(tyt));
                    ((LinearLayout)(findViewById(R.id.conditional))).addView(_tv);
                }
            }
        });


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final int adapterGCount = listDataHeader2.size();
                try {
                    ((LinearLayout)(findViewById(R.id.subjunctive))).removeAllViews();
                }catch (Exception e){
                    e.printStackTrace();}
                String tyt;
                for (int i = 0; i < adapterGCount; i++) {

                    int adapterChCount = ListTenses2.get(listDataHeader2.get(i)).size();
                    tyt="";
                    TextView __tv = new TextView(getApplication());
                    __tv.setText(Html.fromHtml(ListTenses2.get(listDataHeader2.get(i)).get(0)));
                    __tv.setTextSize(X);
                    ((LinearLayout)(findViewById(R.id.subjunctive))).addView(__tv);
                    for (int j = 1; j < adapterChCount; j++) {
                        tyt+=ListTenses2.get(listDataHeader2.get(i)).get(j);
                    }
                    TextView _tv = new TextView(getApplication());
                    _tv.setTextSize(Y);
                    _tv.setTextColor(Color.BLACK);
                    _tv.setText(Html.fromHtml(tyt));
                    ((LinearLayout)(findViewById(R.id.subjunctive))).addView(_tv);
                }
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final int adapterGCount = listDataHeader3.size();
                try {
                    ((LinearLayout)(findViewById(R.id.imperative))).removeAllViews();
                }catch (Exception e){
                    e.printStackTrace();}
                String tyt;
                for (int i = 0; i < adapterGCount; i++) {

                    int adapterChCount = ListTenses3.get(listDataHeader3.get(i)).size();
                    tyt="";
                    TextView __tv = new TextView(getApplication());
                    __tv.setText(Html.fromHtml(ListTenses3.get(listDataHeader3.get(i)).get(0)));
                    __tv.setTextSize(X);
                    ((LinearLayout)(findViewById(R.id.imperative))).addView(__tv);
                    for (int j = 1; j < 3; j++) {
                        tyt+=ListTenses3.get(listDataHeader3.get(i)).get(j);
                    }
                    TextView _tv = new TextView(getApplication());
                    _tv.setTextSize(Y);
                    _tv.setTextColor(Color.BLACK);
                    _tv.setText(Html.fromHtml(tyt));
                    ((LinearLayout)(findViewById(R.id.imperative))).addView(_tv);
                }
            }
        });


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final int adapterGCount = listDataHeader4.size();
                try {
                    ((LinearLayout)(findViewById(R.id.participle))).removeAllViews();
                }catch (Exception e){
                    e.printStackTrace();}
                String tyt;
                for (int i = 0; i < adapterGCount; i++) {

                    int adapterChCount = ListTenses4.get(listDataHeader4.get(i)).size();
                    tyt="";
                    TextView __tv = new TextView(getApplication());
                    __tv.setText(Html.fromHtml(ListTenses4.get(listDataHeader4.get(i)).get(0)));
                    __tv.setTextSize(X);
                    ((LinearLayout)(findViewById(R.id.participle))).addView(__tv);
                    for (int j = 1; j < adapterChCount; j++) {
                        String tmt = ListTenses4.get(listDataHeader4.get(i)).get(j);
                        if(!tmt.equals(" null<br>"))
                            tyt+=tmt;
                    }
                    TextView _tv = new TextView(getApplication());
                    _tv.setTextSize(Y);
                    _tv.setTextColor(Color.BLACK);
                    _tv.setText(Html.fromHtml(tyt));
                    ((LinearLayout)(findViewById(R.id.participle))).addView(_tv);
                }
            }
        });



    }

    public int Je(String verbConjuge){
        char v = (verbConjuge.toCharArray())[0];

        if(v=='a' || v=='e' || v=='o' || v == 'é'){
            return 6;
        }else if(v=='<'){
            String tdt = verbConjuge.split(">")[1];
            char _v = (tdt.toCharArray())[0];
        if (_v == 'a' || _v == 'e' || _v == 'o' || _v == 'é') {
            return 6;
        }
    }

        return 0;
    }

    private class ConjugTaitment extends AsyncTask<String , Void , Void>{
        @Override
        protected Void doInBackground(String... params) {
            Conjuguer(params[0]);
            return null;
        }
    }

    public void justifyListViewHeightBasedOnChildren (ExpandableListView listView, ExpandableListAdapter _adapter,boolean expand) {


        ListAdapter adapter = listView.getAdapter();


        if(expand){
            int count = _adapter.getGroupCount();
            for (int position = 1; position <= count; position++)
                listView.expandGroup(position - 1);
        }

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            try {

                View listItem = adapter.getView(i, null, vg);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }catch(Exception e){
                e.printStackTrace();}
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }


    @Override
    protected void onStart() {
        threadLoudDataConjug = new Thread (new Runnable() {
            @Override
            public void run() {
                LoudConjugData();
            }
        });
        threadLoudDataConjug.start();

        super.onStart();
    }

    public void LoudConjugData(){
        try{if(vt == null || gec == null){
            AssetManager am = getAssets();
            InputStream  descriptor = am.open("verbs-fr.xml");
            //FileReader reader = new FileReader(descriptor.getFileDescriptor());
            vt = new VerbTemplate(descriptor);

            am = getAssets();
            descriptor = am.open("conjugation-fr.xml");
            gec = new getElementConjugation(descriptor);
        }}catch(IOException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();}
    }

    public void translateBtn(View v){
        new translateASync().execute();
    }

    String text;
    class translateASync extends AsyncTask<String,Void,Void>
    {
        @Override
        protected Void doInBackground(String... params) {
            try{Translator translate = Translator.getInstance();
                Language language = Language.getInstance();
                String trt = ((TextView)(findViewById(R.id.editText))).getText().toString();
                String lng = language.getNameLanguage(Locale.getDefault().getLanguage());
                text = translate.translate(trt,Language.FRENCH,lng);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(),text,Toast.LENGTH_LONG).show();
                    }
                });
 
                }
                catch(Exception e){
                    e.printStackTrace();}
                return null;
            }
    }
/*
    public void infinitive(){
        String verb = ((TextView)(findViewById(R.id.editText))).getText().toString();
        String infverb = vt.getInf(verb);
        if(!infverb.isEmpty())
        {
            Toast.makeText(this,"L'infinitife de "+ verb + "c'est "+infverb,Toast.LENGTH_LONG).show();
            ((TextView)(findViewById(R.id.editText))).setText(infverb);
        }
        else
        {
            Toast.makeText(getBaseContext(), "Ce verbe n'exist pas. Vérifiez son ortographe.", Toast.LENGTH_LONG).show();
        }

    }
    public void infinitive(View v){
        infinitive();
    }
*/
}

