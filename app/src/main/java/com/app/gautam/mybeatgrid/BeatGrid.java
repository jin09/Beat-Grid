package com.app.gautam.mybeatgrid;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gautam on 14-10-2016.
 */

public class BeatGrid {
    private static final String TAG = "BeaatGrid";
    private static final String SOUNDS_FOLDER = "sample_sound";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;


    public BeatGrid(Context context){
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    public void play(Sound sound){
        Integer soundID = sound.getmSoundID();
        if(soundID == null){
            return;
        }
        mSoundPool.play(soundID, 1.0f, 1.0f,1,0, 1.0f);
    }

    private void loadSounds() {
        String[] soundNames = new String[0];
        try{
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.d(TAG,"FOUND "+soundNames.length+" sounds");
        }catch (IOException ioe){
            Log.d(TAG,"Could not list !! ",ioe);
        }
        for(String filename: soundNames){
            String assetPath = SOUNDS_FOLDER+"/"+filename;
            Sound sound = new Sound(assetPath);
            try {
                load(sound);
            } catch (IOException e) {
                Log.e(TAG,"Could not load sound !!"+filename,e);
                e.printStackTrace();
            }
            mSounds.add(sound);
        }
    }
    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getmAssetPath());
        int soundID = mSoundPool.load(afd, 1);
        sound.setmSoundID(soundID);
    }
    public List<Sound> getSounds(){
        return mSounds;
    }
    public void release(){
        mSoundPool.release();
    }
}
