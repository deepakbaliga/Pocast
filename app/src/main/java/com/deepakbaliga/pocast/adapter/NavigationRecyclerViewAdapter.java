package com.deepakbaliga.pocast.adapter;

import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepakbaliga.pocast.PocastApp;
import com.deepakbaliga.pocast.R;
import com.deepakbaliga.pocast.callback.ListItemClick;

import java.util.LinkedList;

/**
 * Created by deezdroid on 24/12/15.
 */
public class NavigationRecyclerViewAdapter extends RecyclerView.Adapter<NavigationRecyclerViewAdapter.NavigationViewHolder> {


    private Context context;
    private ListItemClick listItemClick;
    private LinkedList<ItemModel> items =  new LinkedList<>();


    public NavigationRecyclerViewAdapter(Context context, ListItemClick listItemClick) {
        this.context = context;
        this.listItemClick = listItemClick;

        items.add(new ItemModel(R.drawable.ic_home, "Home"));
        items.add(new ItemModel(R.drawable.ic_playlist, "Playlist"));
        items.add(new ItemModel(R.drawable.ic_save, "Offline"));
    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_list_item, parent, false);
        NavigationViewHolder viewHolder =  new NavigationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NavigationViewHolder holder, int position) {

        holder.getItemIcon().setBackgroundResource(items.get(position).getIcon());
        holder.getItemIcon().setColorFilter(R.color.pocastgrey);
        holder.getItemTitle().setText(items.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


     class NavigationViewHolder extends RecyclerView.ViewHolder{

        private TextView itemTitle;
        private ImageView itemIcon;

        public NavigationViewHolder(View itemView) {
            super(itemView);

            itemTitle = (TextView) itemView.findViewById(R.id.textview_nav_list);
            itemIcon = (ImageView) itemView.findViewById(R.id.imageview_nav_list);

            itemTitle.setTypeface(PocastApp.robotoMedium);
            itemIcon.setColorFilter(R.color.pocastgrey);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listItemClick.clicked(getAdapterPosition());
                }
            });
        }

        public TextView getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(TextView itemTitle) {
            this.itemTitle = itemTitle;
        }

        public ImageView getItemIcon() {
            return itemIcon;
        }

        public void setItemIcon(ImageView itemIcon) {
            this.itemIcon = itemIcon;
        }
    }

    class ItemModel{
        private int icon;
        private String text;

        public ItemModel(int icon, String text) {
            this.icon = icon;
            this.text = text;
        }

        public ItemModel() {
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
