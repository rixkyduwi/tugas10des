package com.rizkydwisaputra.facemaskdetection
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;

import android.os.Bundle;
import android.view.View;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    void StartCameraPreview () {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

        ProcessCameraProvider cameraProvider = null;
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        try {
            cameraProvider = cameraProviderFuture.get();
        } catch (ExecutionException | InterruptedException e)  {}

        Preview preview = new Preview.Builder().build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK).build();

        PreviewView previewView = findViewById (R.id.preview_view);   //<<<<<<Returns null

        preview.setSurfaceProvider(previewView.createSurfaceProvider());

        Camera camera = cameraProvider . bindToLifecycle ((LifecycleOwner) this, cameraSelector, preview);
    }

    public void on_button_click(View clickedView) {
        StartCameraPreview();
    }
}
}