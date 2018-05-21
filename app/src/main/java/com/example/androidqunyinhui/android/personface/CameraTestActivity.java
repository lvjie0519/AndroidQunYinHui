package com.example.androidqunyinhui.android.personface;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidqunyinhui.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.nio.ByteBuffer;
import java.util.Arrays;

import rx.functions.Action1;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CameraTestActivity extends AppCompatActivity {
    private static final String TAG = "CameraTestActivity";
    private SurfaceView mSurfaceView;
    private Button btnTakePhoto;
    private Button btnShowPhoto;
    private ImageView ivShowPhoto;

    private CameraManager mCameraManager;
    private SurfaceHolder mSurfaceViewHolder;
    private CameraDevice mCameraDevice;
    private String mCameraId;
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private CameraCaptureSession mSession;
    private ImageReader mImageReader;

    private Handler mainHandler;
    private Handler mHandler;

    private RxPermissions rxPermissions;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CameraTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);

        rxPermissions = RxPermissions.getInstance(this);
        initView();
    }

    private void initView() {
        initSurfaceView();
        this.btnShowPhoto = (Button) findViewById(R.id.btn_show_photo);
        this.btnTakePhoto = (Button) findViewById(R.id.btn_take_photo);
        this.ivShowPhoto = (ImageView) findViewById(R.id.iv_show_photo);

        this.btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        this.btnShowPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void initSurfaceView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.sv_surfaceview);
        mSurfaceViewHolder = mSurfaceView.getHolder();//通过SurfaceViewHolder可以对SurfaceView进行管理
        mSurfaceViewHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.i(TAG, "surfaceCreated()...");
                initCameraAndPreview();
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //释放camera
                if (mCameraDevice != null) {
                    mCameraDevice.close();
                    mCameraDevice = null;
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initCameraAndPreview() {
        Log.i(TAG, "initCameraAndPreview()...");
        HandlerThread handlerThread = new HandlerThread("My First Camera2");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
        mainHandler = new Handler(getMainLooper());//用来处理ui线程的handler，即ui线程


        mCameraId = "" + CameraCharacteristics.LENS_FACING_FRONT;
        mImageReader = ImageReader.newInstance(mSurfaceView.getWidth(), mSurfaceView.getHeight(), ImageFormat.JPEG,/*maxImages*/7);
        mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, mainHandler);//这里必须传入mainHandler，因为涉及到了Ui操作
        mCameraManager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return;//按理说这里应该有一个申请权限的过程，但为了使程序尽可能最简化，所以先不添加
        }

        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) {
                    if (ActivityCompat.checkSelfPermission(CameraTestActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        Log.i(TAG, "Manifest.permission...");
                        mCameraManager.openCamera(mCameraId, deviceStateCallback, mHandler);
                    } catch (CameraAccessException e) {
                        Toast.makeText(CameraTestActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private ImageReader.OnImageAvailableListener mOnImageAvailableListener = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader reader) {
            //进行相片存储
            mCameraDevice.close();
            Image image = reader.acquireNextImage();
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);//将image对象转化为byte，再转化为bitmap
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            if (bitmap != null) {
                ivShowPhoto.setImageBitmap(bitmap);
            }
        }
    };

    private CameraDevice.StateCallback deviceStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            Log.i(TAG, "onOpened...");
            mCameraDevice = camera;
            try {
                takePreview();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            if (mCameraDevice != null) {
                mCameraDevice.close();
                mCameraDevice = null;
            }
        }

        @Override
        public void onError(CameraDevice camera, int error) {
            Toast.makeText(CameraTestActivity.this, "打开摄像头失败", Toast.LENGTH_SHORT).show();
        }
    };

    public void takePreview() throws CameraAccessException {
        mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        mCaptureRequestBuilder.addTarget(mSurfaceViewHolder.getSurface());
        mCameraDevice.createCaptureSession(Arrays.asList(mSurfaceViewHolder.getSurface(), mImageReader.getSurface()), mSessionPreviewStateCallback, mHandler);
    }

    private CameraCaptureSession.StateCallback mSessionPreviewStateCallback = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(@NonNull CameraCaptureSession session) {
            Log.i(TAG, "onConfigured...");
            mSession = session;
            //配置完毕开始预览
            try {
                /**
                 * 设置你需要配置的参数
                 */
                //自动对焦
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                //打开闪光灯
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                //无限次的重复获取图像
                mSession.setRepeatingRequest(mCaptureRequestBuilder.build(), null, mHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
            Toast.makeText(CameraTestActivity.this, "配置失败", Toast.LENGTH_SHORT).show();
        }
    };

    public void takePicture() {
        try {
            CaptureRequest.Builder captureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);//用来设置拍照请求的request
            captureRequestBuilder.addTarget(mImageReader.getSurface());
            // 自动对焦
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            // 自动曝光
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            CameraCharacteristics cameraCharacteristics = mCameraManager.getCameraCharacteristics(mCameraId);
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION, getJpegOrientation(cameraCharacteristics, getOrientation(rotation)));//使图片做顺时针旋转
            CaptureRequest mCaptureRequest = captureRequestBuilder.build();
            mSession.capture(mCaptureRequest, null, mHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    //获取图片应该旋转的角度，使图片竖直
    public int getOrientation(int rotation) {
        switch (rotation) {
            case Surface.ROTATION_0:
                return 90;
            case Surface.ROTATION_90:
                return 0;
            case Surface.ROTATION_180:
                return 270;
            case Surface.ROTATION_270:
                return 180;
            default:
                return 0;
        }
    }

    //获取图片应该旋转的角度，使图片竖直
    private int getJpegOrientation(CameraCharacteristics c, int deviceOrientation) {
        if (deviceOrientation == android.view.OrientationEventListener.ORIENTATION_UNKNOWN)
            return 0;
        int sensorOrientation = c.get(CameraCharacteristics.SENSOR_ORIENTATION);

        // Round device orientation to a multiple of 90
        deviceOrientation = (deviceOrientation + 45) / 90 * 90;

        // LENS_FACING相对于设备屏幕的方向,LENS_FACING_FRONT相机设备面向与设备屏幕相同的方向
        boolean facingFront = c.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT;
        if (facingFront) deviceOrientation = -deviceOrientation;

        // Calculate desired JPEG orientation relative to camera orientation to make
        // the image upright relative to the device orientation
        int jpegOrientation = (sensorOrientation + deviceOrientation + 360) % 360;

        return jpegOrientation;
    }

}
