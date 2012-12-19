package com.codegg;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ContentProviderMediaStoreActivity extends Activity {
	private static final int DELETE_DIALOG = 1;

	private Gallery gallery = null;

	private int selectedImageId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		gallery = new Gallery(this);
		Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		String[] projection = { MediaStore.Images.Media._ID };
		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = null;
		Cursor cursor = managedQuery(uri, projection, selection, selectionArgs, sortOrder);
		gallery.setAdapter(new CursorAdapter(this, cursor, true) {
			@Override
			public View newView(Context context, Cursor cursor, ViewGroup parent) {
				int id = cursor.getInt(0);
				ContentResolver cr = getContentResolver();
				Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
				Bitmap image = null;

				try {
					image = MediaStore.Images.Media.getBitmap(cr, uri);
				} catch (Exception e) {
					Log.e("Error", "Error", e);
				}
				ImageView imageView = new ImageView(context);
				imageView.setId(id);
				imageView.setScaleType(ScaleType.CENTER_INSIDE);
				imageView.setImageBitmap(image);
				return imageView;
			}

			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				int id = cursor.getInt(0);
				ContentResolver cr = getContentResolver();
				Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
				Bitmap image = null;
				try {
					image = MediaStore.Images.Media.getBitmap(cr, uri);
				} catch (Exception e) {
					Log.e("Error", "Error", e);
				}
				ImageView imageView = (ImageView) view;
				imageView.setId(id);

				imageView.setImageBitmap(image);
			}
		});

		gallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
				selectedImageId = v.getId();
				showDialog(DELETE_DIALOG);
			}
		});
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.addView(gallery);
		setContentView(linearLayout);

	}

	@Override
	public Dialog onCreateDialog(int id) {

		if (id == DELETE_DIALOG) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Cancella immagine");
			builder.setMessage("Confermi l'eliminazione dell'immagine?");
			builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					deleteSelectedImage();
				}
			});
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			return builder.create();
		}

		return null;
	}

	private void deleteSelectedImage() {
		/*
		 * ContentResolver cr = getContentResolver(); Uri uri =
		 * ContentUris.withAppendedId
		 * (MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selectedImageId);
		 * cr.delete(uri, null, null);
		 */
		Toast.makeText(this, "Cancello l'immagine selezionata, ma non lo faccio...", Toast.LENGTH_SHORT).show();
	}

}
