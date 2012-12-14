package com.codegg;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class FileSystemGalleryActivity extends Activity {

	/**
	 * Inner class adapter usata per la gallery presente nella activity.
	 * 
	 * @author fabio.giordani
	 * 
	 */
	private class ImageAdapter extends BaseAdapter {

		private Context context;
		private Drawable[] images;

		public ImageAdapter(Context context, Drawable[] images) {
			this.context = context;
			this.images = images;
		}

		@Override
		public int getCount() {
			return images.length;
		}

		@Override
		public Object getItem(int position) {
			return images[position];
		}

		@Override
		public long getItemId(int position) {
			return images[position].hashCode();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(context);
				imageView.setScaleType(ScaleType.CENTER_INSIDE);
			} else {
				imageView = (ImageView) convertView;
			}
			imageView.setImageDrawable(images[position]);
			return imageView;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_system_gallery);
		Drawable[] pictures;

		if (canReadFromExternalStorage()) {
			pictures = loadPicturesFromExternalStorage();
		} else {
			pictures = new Drawable[0];
		}
		ImageAdapter adapter = new ImageAdapter(this, pictures);
		Gallery gallery = (Gallery) findViewById(R.id.fileSystemGallery_sdGallery);
		gallery.setAdapter(adapter);
	}

	private boolean canReadFromExternalStorage() {
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {

			return true;
		} else {
			return false;
		}
	}

	private boolean canWriteOnExternalStorage() {
		// boolean possoLeggereStorageEsterno = false;
		boolean possoScrivereStorageEsterno = false;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// Storage esterno disponibile in lettura e scrittura.
			// possoLeggereStorageEsterno = true;
			possoScrivereStorageEsterno = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// Storage esterno disponibile solo in lettura.
			// possoLeggereStorageEsterno = true;
			possoScrivereStorageEsterno = false;
		} else {
			// Storage esterno non disponibile.
		}
		return possoScrivereStorageEsterno;
	}

	private Drawable[] loadPicturesFromExternalStorage() {
		File extStorageDir = Environment.getExternalStorageDirectory();
		File picturesDir = new File(extStorageDir, "downloads");
		if (!picturesDir.exists() && !picturesDir.isDirectory()) {
			return new Drawable[0];
		}
		ArrayList<Drawable> pictures = new ArrayList<Drawable>();
		File[] files = picturesDir.listFiles();
		for (File f : files) {

			if (f.isFile()) {
				Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

				if (bitmap != null) {
					Drawable drawable = new BitmapDrawable(bitmap);
					pictures.add(drawable);
				}
			}
		}

		return pictures.toArray(new Drawable[pictures.size()]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_file_system_gallery, menu);
		return true;
	}

}
