package com.app.gautam.mybeatgrid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by gautam on 12-10-2016.
 */
public class BeatGridFragment extends Fragment {

    private BeatGrid mBeatGrid;

    public static BeatGridFragment newInstance(){
        return new BeatGridFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeatGrid = new BeatGrid(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beat_grid, container, false);

        RecyclerView recyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_beat_grid_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SoundAdapter(mBeatGrid.getSounds()));
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatGrid.release();
    }

    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button mButton;
        private Sound mSound;
        public SoundHolder(LayoutInflater inflater, ViewGroup container){
            super(inflater.inflate(R.layout.list_item_sound, container, false));
            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(this);
        }
        public void bindSound(Sound sound){
            mSound = sound;
            mButton.setText(mSound.getmName());
        }

        @Override
        public void onClick(View v) {
            mBeatGrid.play(mSound);
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> mSounds ;

        public SoundAdapter(List<Sound> sounds){
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
