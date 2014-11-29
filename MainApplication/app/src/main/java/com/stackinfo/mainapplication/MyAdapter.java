package com.stackinfo.mainapplication;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MyAdapter extends ArrayAdapter<selfie> {

	private Context context;
	private List<selfie> selfieList;
    private int number;
	public MyAdapter(Context context, int resource, List<selfie> objects) {
		super(context, resource, objects);
		this.context = context;
		this.selfieList = objects;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		
		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if(position%3==0){
		 view = inflater.inflate(R.layout.item_flower2, parent, false);}
		else {
		 view = inflater.inflate(R.layout.item_flower, parent, false);
		}
		ImageView  image= (ImageView) view.findViewById(R.id.imageView1);
		//Display flower name in the TextView widget
		selfie selfie = selfieList.get(position);
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText(selfie.getName());
		image.setTag(position);
		//Display flower photo in ImageView widget
		if (selfie.getBitmap() != null) {
			 
			image.setImageBitmap(selfie.getBitmap());
			
		}
		else {
			FlowerAndView container = new FlowerAndView();
			container.selfie = selfie;
			container.view = view;			
			ImageLoader loader = new ImageLoader();
			loader.execute(container);			
		}
		image.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				number = Integer.parseInt(v.getTag().toString());
                  Intent intent=new Intent();
                  intent.setClass(context, Image.class);
                  intent.putExtra("picutre", selfieList.get(number).getInstructions());
                  context.startActivity(intent);
			}
		});
	


		return view;
	}

	class FlowerAndView {
		public selfie selfie;
		public View view;
		public Bitmap bitmap;
	}

	private class ImageLoader extends
			AsyncTask<FlowerAndView, Void, FlowerAndView> {

		@Override
		protected FlowerAndView doInBackground(FlowerAndView... params) {

			FlowerAndView container = params[0];
			selfie selfie = container.selfie;

			try {
				String imageUrl = selfie.getPhoto();
				// String imageUrl = MainActivity.PHOTOS_BASE_URL;
				InputStream in = (InputStream) new URL(imageUrl).getContent();
				Bitmap bitmap = BitmapFactory.decodeStream(in);
				selfie.setBitmap(bitmap);
				in.close();
				container.bitmap = bitmap;
				return container;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(FlowerAndView result) {
			ImageView image = (ImageView) result.view
					.findViewById(R.id.imageView1);
			
			image.setImageBitmap(result.bitmap);	
			result.selfie.setBitmap(result.bitmap);
		}

	}

}
