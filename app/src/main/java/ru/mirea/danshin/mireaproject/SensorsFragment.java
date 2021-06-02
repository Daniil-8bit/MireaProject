package ru.mirea.danshin.mireaproject;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorsFragment extends Fragment{

//    private SensorManager mSensorManager;
//    private Sensor mOrientation;
//
//    private float xy_angle;
//    private float xz_angle;
//    private float zy_angle;
//
//    private TextView xyView;
//    private TextView xzView;
//    private TextView zyView;
    int Image_Capture_Code = 24;
    int REQUEST_CODE=24;
    int REQUEST_TAKE_PHOTO=24;

    private static final int REQUEST_CODE_PERMISSION_CAMERA = 100;
    final String TAG = MainActivity.class.getSimpleName();
    private ImageView imageView;
    private static final int CAMERA_REQUEST = 0;
    private boolean isWork = false;
    private Uri imageUri;

    private Button startRecordButton;
    private Button stopRecordButton;
    private MediaRecorder mediaRecorder;
    private File audioFile;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SensorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorsFragment newInstance(String param1, String param2) {
        SensorsFragment fragment = new SensorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensors, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Button takePhoto = view.findViewById(R.id.takePhoto);
//        ImageView imageView = view.findViewById(R.id.photo);
//
//        takePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                if (cameraIntent.resolveActivity(getPackageManager()) != null && isWork == true)
//                {
//                    File photoFile = null;
//                    try {
//                        photoFile = createImageFile();
//                    }
//                    catch (
//                    IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                    String authorities = getApplicationContext().getPackageName() + ".fileprovider";
//
//                    imageUri = FileProvider.getUriForFile(this, authorities, photoFile);
//                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                    imageView.setImageURI(imageUri);
//                }
//            }
//            private File createImageFile() throws IOException {
//                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//                String imageFileName = "IMAGE_" + timeStamp + "_"; File storageDirectory =
//                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//                return File.createTempFile(imageFileName, ".jpg", storageDirectory);
//            }
//        });

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Button play = view.findViewById(R.id.play);
//
//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    startRecordButton.setEnabled(false);
//                    stopRecordButton.setEnabled(true);
//                    stopRecordButton.requestFocus();
//                    startRecording();
//                }
//                catch (Exception e) {
//                    Log.e(TAG, "Caught io exception " + e.getMessage());
//                }
//            }
//        });
//
//    }
//
//    private void startRecording() throws IOException {
//        // проверка доступности sd - карты
//        String state = Environment.getExternalStorageState();
//        if (Environment.MEDIA_MOUNTED.equals(state) ||
//                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
//            Log.d(TAG, "sd-card success");
//            // выбор источника звука
//            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//            // выбор формата данных
//            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            // выбор кодека
//            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//            if (audioFile == null) {
//                // создание файла
//                audioFile = new File(this.getExternalFilesDir(Environment.DIRECTORY_MUSIC), "mirea.3gp");
//            }
//            mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
//            mediaRecorder.prepare();
//            mediaRecorder.start();
//            Toast.makeText(this, "Recording started!", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//        @Override
//        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//            super.onViewCreated(view, savedInstanceState);
//            Button pause = view.findViewById(R.id.pause);
//
//            pause.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startRecordButton.setEnabled(true);
//                    stopRecordButton.setEnabled(false);
//                    startRecordButton.requestFocus();
//                    stopRecording();
//                }
//            });
//        }
//
//    private void stopRecording() {
//        if (mediaRecorder != null) {
//            Log.d(TAG, "stopRecording");
//            mediaRecorder.stop();
//            mediaRecorder.reset();
//            mediaRecorder.release();
//            Toast.makeText(this, "You are not recording right now!", Toast.LENGTH_SHORT).show();
//        }
//    }

//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) { //Изменение точности показаний датчика
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent event) { //Изменение показаний датчиков
//        xy_angle = event.values[0]; //Плоскость XY
//        xz_angle = event.values[1]; //Плоскость XZ
//        zy_angle = event.values[2]; //Плоскость ZY
//
//        xyView.setText(String.valueOf(xy_angle));
//        xzView.setText(String.valueOf(xz_angle));
//        zyView.setText(String.valueOf(zy_angle));
//    }
}