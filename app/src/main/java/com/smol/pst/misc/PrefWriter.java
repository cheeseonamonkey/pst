package com.smol.pst.misc;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.smol.pst.models.PasteList;

public class PrefWriter
{

    public static void setApiKey(Context context, String apiKey)
    {
        writePref(context, "apiKey" , apiKey);



    }



    public static void setPasteList(Context context, String pasteListJson)
    {
        //

        writePref(context, "prefPasteList",  pasteListJson);
    }




    //
    //read method is public, write method is private



    public static boolean isPrefSet(Context context, String prefKey)
    {
        boolean boolOut = false;

        try
        {
            String str = readPref(context, prefKey);

            //not set
            if(str == null || str.contains("NOT SET") || str.contains("NOTSET"))
                return false;
            else //is set
                return true;



        }catch(Exception exc)
        {
            Log.d("TAG", "Processor error 518: (caught and handled in logic) in isPref():  " + exc.getMessage() );
            return false;
        }

    }


    public static String readPref(Context context, String prefKey)
    {

        try
        {

            SharedPreferences localprefs = context.getSharedPreferences("pastePrefs", context.MODE_PRIVATE);
            String localstrPref = localprefs.getString(prefKey, null);//If there is no YOURKEY found null will be the default value.

            //if pref is set to something
            if(localstrPref != null)
            {
                Log.d("TAG", "Pref read - " + prefKey + " \t: " + localstrPref);
            }else
            {
                //pref not set
                Log.d("TAG", "Error 516 - Pref load not set. Returning a NOTSET: value\n\t - key: " + prefKey + "\t - data (read back): " + localstrPref);
                return "NOTSET:" + prefKey;


            }



            return localstrPref;
//}


        }catch(Exception exc)
        {

            Log.d("error", "Error in Processor: " + exc.getMessage());
            return "ERR" + "NOTSET:" + prefKey ;
        }

    }

    protected static void writePref(Context context, String prefKey, String prefData )
    {

        //Log.d("a", "writePref: " + context.toString() + " - " + prefKey + " - " + prefData);

        try
        {

/*
            //default/public preferences
            if(PREF_WRITE_MODE == DEFAULT_PREF_WRITE_MODE)
                PREF_WRITE_MODE = DEFAULT_PREF_WRITE_MODE;
            //regular preferences
            else if(PREF_WRITE_MODE == LOCAL_PREF_WRITE_MODE)
                PREF_WRITE_MODE = LOCAL_PREF_WRITE_MODE;
*/


            SharedPreferences.Editor localEditor = context.getSharedPreferences("pastePrefs", context.MODE_PRIVATE).edit();
            localEditor.putString(prefKey, prefData );
            localEditor.commit();


            //reads back the data to be sure it was saved:
            String localDebugCallbackData = readPref(context, prefKey);

            if(localDebugCallbackData == null || localDebugCallbackData == "")
                throw new Exception("Error 104 in PrefWriter - detected that the preference \"" + prefKey.toString() + "\" was not set when attempting to write ");



            Log.d("pref", "Pref written - " + prefKey + "\t : " + localDebugCallbackData.toString() + " (read back)");
            //
            //throw new Exception("Error 203 in Processor -- Local preference writing not yet implemented.");



        }catch(Exception exc)
        {


            Log.d("error", "Error in PrefWriter: " + exc.getMessage());
        }

    }



}
