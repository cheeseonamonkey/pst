package com.smol.pst.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.smol.pst.R;
import com.smol.pst.misc.Requester;
import com.smol.pst.models.Paste;
import com.smol.pst.models.PasteListItem;

import java.util.List;

public class AdapterPasteList extends RecyclerView.Adapter<AdapterPasteList.ViewHolder>
{

    List<PasteListItem> items;
    Context context;
    Fragment parentFrag;

    public AdapterPasteList(Context context, List<PasteListItem> items, Fragment parentFrag)
    {
        this.context = context;
        this.items = items;
        this.parentFrag = parentFrag;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //inflate layout on view then uses to it make the ViewHolder object
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_paste_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(listItemView);




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        //a parameter is the ViewHolder, remember that when recycling the UI needs to be updated completely every time

        int pos = position;

        PasteListItem currentItem = items.get(pos);


        //do stuff to the ViewHolder:

        holder.pos = pos;

        if(currentItem.id != null)
            holder.id = currentItem.id;

        holder.txtPasteListName.setText(currentItem.id);
        holder.txtPasteListDate.setText(currentItem.createdAt);
        //holder.txtPasteListName.setText(currentItem.id);
        //holder.txtPasteListContent.setText(currentItem);


    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        //fields for adapter:

        //adapter position, set in onBind
        int pos;

        String id;

        //ui objects in the view:
        TextView txtPasteListName;
        TextView txtPasteListDate;
        TextView txtPasteListTime;
        TextView txtPasteListContent;

        ImageButton btnImgGoToPaste;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            txtPasteListName = itemView.findViewById(R.id.txtPasteListName);
            txtPasteListDate = itemView.findViewById(R.id.txtPasteListDate);
            txtPasteListTime = itemView.findViewById(R.id.txtPasteListTime);
            txtPasteListContent = itemView.findViewById(R.id.txtPasteListContent);

            btnImgGoToPaste = itemView.findViewById(R.id.btnImgGoToPaste);
            btnImgGoToPaste.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    try
                    {


                        if (id == null)
                            throw new Exception("Error 477 in AdapterPasteList ViewHolder - ID not yet been set ");

                        String getUrl = "https://api.paste.ee/v1/pastes/" + id;

                        Requester.getSet(itemView.getContext(), getUrl, new Paste());



                    }catch(Exception ex) {
                        Log.d("err", "error in adapter - " + ex.getMessage());
                    }
                    
                    
                }
            });

        }
    }

}
