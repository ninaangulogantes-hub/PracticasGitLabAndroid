package com.afundacion.fp.clips;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;

public class ClipViewHolder extends RecyclerView.ViewHolder{

    private TextView textView;
    private Clip clip;

    public ClipViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textViewRecycler);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clipId = clip.getId();
                Context context = view.getContext();
                Toast.makeText(context,
                        "Clicked on cell with clipId: " + clip.getId(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra(VideoActivity.INTENT_CLIP_ID, clipId);
                intent.putExtra(VideoActivity.INTENT_CLIP_URL, clip.getUrlVideo());
                context.startActivity(intent);
            }
        });
    }
    public void bindClip(Clip clip) {
        this.clip = clip;
        this.textView.setText(clip.getVideoTitle());
    }
}
