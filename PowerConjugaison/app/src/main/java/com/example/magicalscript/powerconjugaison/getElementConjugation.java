package com.example.magicalscript.powerconjugaison;

/**
 * Created by Abdellah on 31/08/2015.
 */

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class getElementConjugation
{

    private void LoadVerbes(String Filename)
    {
        try
        {
            sxb = new SAXBuilder();
            document = sxb.build(new File(Filename));
            racine = document.getRootElement();
            ListeVerbes = racine.getChildren();
            verbesconj = ListeVerbes.iterator();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public getElementConjugation(){}
    public getElementConjugation(String FileTemplate)
    {
        LoadVerbes(FileTemplate);
    }

    private void LoadVerbes(InputStream Filename)
    {
        try
        {
            sxb = new SAXBuilder();
            document = sxb.build(Filename);
            racine = document.getRootElement();
            ListeVerbes = racine.getChildren();
            verbesconj = ListeVerbes.iterator();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public getElementConjugation(InputStream FileTemplate)
    {
        LoadVerbes(FileTemplate);
    }
    public Element getElemConjigaison(String verb)
    {
        Element result = null;
        String myverbe = verb;
        do
        {
            if(!verbesconj.hasNext())
                break;
            Element verbe = (Element)verbesconj.next();
            String att = verbe.getAttributeValue("name");
            if(att.equals(myverbe))
                result = verbe;
        } while(true);
        verbesconj=ListeVerbes.iterator();
        return result;
    }

    public void finalize()
    {
        sxb = null;
        document = null;
        racine = null;
        ListeVerbes = null;
        verbesconj = null;
        System.gc();
    }

    private SAXBuilder sxb;
    private Document document;
    private Element racine;
    private List ListeVerbes;
    private Iterator verbesconj;
}
