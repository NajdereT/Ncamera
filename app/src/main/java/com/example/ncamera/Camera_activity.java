package com.example.ncamera;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Gesture;
import com.otaliastudios.cameraview.GestureAction;

import java.io.File;

public class Camera_activity extends AppCompatActivity {
    private CameraView camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_activity);

        camera =(CameraView) findViewById(R.id.camera);


        camera.mapGesture(Gesture.PINCH, GestureAction.ZOOM); // Pinch to zoom!
        camera.mapGesture(Gesture.TAP, GestureAction.FOCUS_WITH_MARKER); // Tap to focus!
        camera.mapGesture(Gesture.LONG_TAP, GestureAction.CAPTURE); // Long tap to shoot!
        camera.setLifecycleOwner(this);



        camera.addCameraListener(new CameraListener() {


            /**
             * Notifies that the camera was opened.
             * The options object collects all supported options by the current camera.
             */
            @Override
            public void onCameraOpened(CameraOptions options) {}

            /**
             * Notifies that the camera session was closed.
             */
            @Override
            public void onCameraClosed() {}

            /**
             * Notifies about an error during the camera setup or configuration.
             * At the moment, errors that are passed here are unrecoverable. When this is called,
             * the camera has been released and is presumably showing a black preview.
             *
             * This is the right moment to show an error dialog to the user.
             */
            @Override
            public void onCameraError(CameraException error) {}

            /**
             * Notifies that a picture previously captured with capturePicture()
             * or captureSnapshot() is ready to be shown or saved.
             *
             * If planning to get a bitmap, you can use CameraUtils.decodeBitmap()
             * to decode the byte array taking care about orientation.
             */
            @Override
            public void onPictureTaken(byte[] picture) {}

            /**
             * Notifies that a video capture has just ended. The file parameter is the one that
             * was passed to startCapturingVideo(File), or a fallback video file.
             */
            @Override
            public void onVideoTaken(File video) {}

            /**
             * Notifies that the device was tilted or the window offset changed.
             * The orientation passed can be used to align views (e.g. buttons) to the current
             * camera viewport so they will appear correctly oriented to the user.
             */
            @Override
            public void onOrientationChanged(int orientation) {}

            /**
             * Notifies that user interacted with the screen and started focus with a gesture,
             * and the autofocus is trying to focus around that area.
             * This can be used to draw things on screen.
             */
            @Override
            public void onFocusStart(PointF point) {}

            /**
             * Notifies that a gesture focus event just ended, and the camera converged
             * to a new focus (and possibly exposure and white balance).
             */
            @Override
            public void onFocusEnd(boolean successful, PointF point) {}

            /**
             * Noitifies that a finger gesture just caused the camera zoom
             * to be changed. This can be used, for example, to draw a seek bar.
             */
            @Override
            public void onZoomChanged(float newValue, float[] bounds, PointF[] fingers) {}

            /**
             * Noitifies that a finger gesture just caused the camera exposure correction
             * to be changed. This can be used, for example, to draw a seek bar.
             */
            @Override
            public void onExposureCorrectionChanged(float newValue, float[] bounds, PointF[] fingers) {}

        });
    }


            public void capture (View view){
                camera.capturePicture();

            }
}