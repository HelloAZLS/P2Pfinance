package ysg.gdcp.cn.p2pfinance.activity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.Bind;
import butterknife.OnClick;
import ysg.gdcp.cn.p2pfinance.MainActivity;
import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.common.AppManager;
import ysg.gdcp.cn.p2pfinance.common.BaseActivity;
import ysg.gdcp.cn.p2pfinance.utils.BitmapUtils;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/16 10:40.
 *
 * @author ysg
 */
public class UserInfoActivity extends BaseActivity {

    private static final int YSG_CAMERA = 100;
    private static final int YSG_PICTURE = 101;

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.imageView1)
    ImageView imageView1;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.loginOut)
    Button loginOut;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleTv.setText("用户信息");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.title_left)
    public void back(View v) {
        closeCurrentActivity();
    }

    @OnClick(R.id.imageView1)
    public void changeToux(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择来源");
        builder.setItems(new String[]{"图库", "拍照"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //图库
                        Intent picture = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(picture, YSG_PICTURE);
                        break;
                    case 1:
                        //拍照
                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(camera, YSG_CAMERA);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @OnClick(R.id.loginOut)
    public void cliclOut(View view) {
        logout();
    }

    private void logout() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("退出登录");
        builder.setMessage("你确定吗？？？？？？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getSharedPreferences("user_info", MODE_PRIVATE).edit().clear().commit();
                AppManager.getInstance().reomveAll();
                gotoActivity(MainActivity.class, null);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = getCacheDir() + "/toux.png";
        if (requestCode == YSG_CAMERA && resultCode == RESULT_OK && data != null) {
//拍照
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            Bitmap circleImage = BitmapUtils.circleBitMap(bitmap);
            try {
                FileOutputStream fos = new FileOutputStream(path);
                circleImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                imageView1.setImageBitmap(circleImage);
                imageView1.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == YSG_PICTURE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String pathResult = getPath(selectedImage);
            if (!TextUtils.isEmpty(path)) {
                Bitmap decodeFile = BitmapFactory.decodeFile(pathResult);
                Bitmap zoomBitmap = BitmapUtils.zoom(decodeFile, UIutils.dp2px(62), UIutils.dp2px(62));
                Bitmap circleImage = BitmapUtils.circleBitMap(zoomBitmap);
                try {
                    FileOutputStream fos = new FileOutputStream(path);
                    circleImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    imageView1.setImageBitmap(circleImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    /**
     * 根据系统相册选择的文件获取路径
     *
     * @param uri
     */
    @SuppressLint("NewApi")
    private String getPath(Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) {
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(this, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(this, contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                return actualimagecursor.getString(actual_image_column_index);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(this, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * uri路径查询字段
     *
     * @param context
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isMedia(Uri uri) {
        return "media".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
