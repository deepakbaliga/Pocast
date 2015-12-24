package com.deepakbaliga.pocast.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deepakbaliga.pocast.PocastApp;
import com.deepakbaliga.pocast.R;
import com.deepakbaliga.pocast.callback.ListItemClick;
import com.deepakbaliga.pocast.model.CategoryModel;

import java.util.LinkedList;

/**
 * Created by deezdroid on 24/12/15.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private LinkedList<CategoryModel> categories;
    private ListItemClick listItemClick;

    public CategoryAdapter(LinkedList<CategoryModel> categories, ListItemClick listItemClick) {
        this.categories = categories;
        this.listItemClick = listItemClick;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        CategoryViewHolder viewHolder =  new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        CategoryModel model = categories.get(position);

        holder.getCategoryName().setText(model.getCategoryName());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

     class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView categoryName;
        public CategoryViewHolder(View itemView) {
            super(itemView);

            categoryName = (TextView) itemView.findViewById(R.id.textview_category_name);
            categoryName.setTypeface(PocastApp.robotoLight);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listItemClick.clicked(getLayoutPosition());
                }
            });

        }

        public TextView getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(TextView categoryName) {
            this.categoryName = categoryName;
        }
    }
}
