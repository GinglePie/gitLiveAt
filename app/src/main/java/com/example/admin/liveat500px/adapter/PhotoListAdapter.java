package com.example.admin.liveat500px.adapter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.liveat500px.R;
import com.example.admin.liveat500px.dao.PhotoItemCollectionDao;
import com.example.admin.liveat500px.dao.PhotoItemDao;
import com.example.admin.liveat500px.datatype.MutableInteger;
import com.example.admin.liveat500px.manager.PhotoListManager;
import com.example.admin.liveat500px.view.PhotoListItem;

public class PhotoListAdapter extends BaseAdapter {

    PhotoItemCollectionDao dao;
    MutableInteger lastPositionInteger;

    public PhotoListAdapter(MutableInteger lastPositionInteger) {
        this.lastPositionInteger = lastPositionInteger;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        // Plus One for ProgressBar Load More
        if (dao == null)
            return 1;
        if (dao == null)
            return 1;
        return dao.getData().size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return dao.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        // type 0 PhotoListItem + type 1 ProgressBar
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // type 0 PhotoListItem + type 1 ProgressBar
        return position == getCount() -1 ? 1 : 0 ;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (i == getCount() - 1) {
            ProgressBar item;
            if (view != null)
                item = (ProgressBar) view;
            else
                item = new ProgressBar(parent.getContext());
            return item;
        }
        PhotoListItem item;
        if (view != null)
            item = (PhotoListItem) view;
        else
            item = new PhotoListItem(parent.getContext());

        PhotoItemDao dao = (PhotoItemDao) getItem(i);
        item.setNameText(dao.getCaption());
        item.setDescriptionText(dao.getUsername() + "\n" + dao.getCamera());
        item.setImageUrl(dao.getImageUrl());

        if (i > lastPositionInteger.getValue()) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(),
                    R.anim.up_from_bottom);
            item.startAnimation(anim);
            lastPositionInteger.setValue(i);
        }

        return item;
    }

    public void increaseLastPosition(int amount) {
        lastPositionInteger.setValue(lastPositionInteger.getValue() + amount);
    }


}
