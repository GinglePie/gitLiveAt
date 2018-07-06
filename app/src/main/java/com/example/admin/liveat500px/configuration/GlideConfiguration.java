package com.example.admin.liveat500px.configuration;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;

import java.lang.annotation.Annotation;

public class GlideConfiguration implements GlideModule{
    @Override
    public String glideName() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
