package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.MyBean;
import com.wonderfulme.libandroid.JokeActivity;

import java.io.IOException;

public class EndpointsAsyncTask  extends AsyncTask<Pair<Context, String>, Void, String>{

    private static MyApi mMyApi = null;
    @SuppressLint("StaticFieldLeak")
    private Context mContext;

    EndpointsAsyncTask(Context mContext) {
        this.mContext = mContext;
    }

    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, String>... pairs) {
        if (mMyApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(mContext.getString(R.string.api_url));
            mMyApi = builder.build();
        }

        try {
            return mMyApi.setJoke(new MyBean()).execute().getOneJoke();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(mContext, JokeActivity.class);
        intent.putExtra(JokeActivity.INTENT_JOKE, s);
        mContext.startActivity(intent);
    }
}
