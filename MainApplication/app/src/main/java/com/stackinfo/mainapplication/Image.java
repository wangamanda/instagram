package com.stackinfo.mainapplication;


import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.hanselandpetal.catalog.R;

public class Image extends Activity{
	private String picture;
	private Bitmap mBitmap;
	private Button mButton;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.picture);
	Intent intent = this.getIntent();
    picture = (String) intent.getExtras().get("picutre");
    mButton=(Button)findViewById(R.id.button1);
    mButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
                finish();			
		}
	});
	ImageLoader loader = new ImageLoader();
	loader.execute();	
}

private class ImageLoader extends
AsyncTask<Void, Void, Bitmap> {

	@Override
protected void onPostExecute(Bitmap mBitmap) {
	ImageView  image= (ImageView)findViewById(R.id.p_imageView1);
image.setImageBitmap(mBitmap);	
}
	@Override
	protected Bitmap doInBackground(Void... params) {
		try {
			String imageUrl = picture;
			InputStream in = (InputStream) new URL(imageUrl).getContent();
			Bitmap bitmap = BitmapFactory.decodeStream(in);
			in.close();
			mBitmap = bitmap;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mBitmap;
	}



}

}
