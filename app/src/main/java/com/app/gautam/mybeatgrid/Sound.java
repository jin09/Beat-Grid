package com.app.gautam.mybeatgrid;

import android.content.Intent;

/**
 * Created by gautam on 14-10-2016.
 */

public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundID;

    public Sound(String AssetPath){
        mAssetPath = AssetPath;
        String[] components = mAssetPath.split("/");
        String filename = components[components.length-1];
        mName = filename.replace(".wav","");
    }

    public String getmAssetPath(){
        return mAssetPath;
    }

    public  String getmName(){
        return mName;
    }
    public Integer getmSoundID(){
        return mSoundID;
    }
    public void setmSoundID(Integer s){
        mSoundID = s;
    }
}
