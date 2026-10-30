package com.afundacion.fp.clips;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClipsAdapter extends RecyclerView.Adapter<ClipViewHolder>{
    private ClipsList clipsToBeShown;
    public ClipsAdapter(ClipsList clips) {
        this.clipsToBeShown = clips;
    }

    @NonNull
    @Override
    public ClipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cellView = inflater.inflate(R.layout.recycler_view_cell, parent, false);
        ClipViewHolder cellViewHolder = new ClipViewHolder(cellView);
        return cellViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClipViewHolder holder, int position) {
        Clip dataForThisCell = this.clipsToBeShown.getClips().get(position);
        holder.bindClip(dataForThisCell);

    }

    @Override
    public int getItemCount() {
        return this.clipsToBeShown.getClips().size();
    }
}
