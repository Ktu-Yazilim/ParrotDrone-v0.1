package com.example.ouz.parrot.activity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ouz.parrot.R;
import com.example.ouz.parrot.drone.MiniDrone;
import com.parrot.arsdk.arcommands.ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_ANIMATION_STATE_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_ANIMATION_TYPE_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_MINIDRONE_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_ENUM;
import com.parrot.arsdk.arcontroller.ARCONTROLLER_DEVICE_STATE_ENUM;
import com.parrot.arsdk.arcontroller.ARControllerCodec;
import com.parrot.arsdk.arcontroller.ARFrame;
import com.parrot.arsdk.ardiscovery.ARDiscoveryDeviceService;

import java.io.Console;

import static com.example.ouz.parrot.activity.DeviceListActivity.EXTRA_DEVICE_SERVICE;


import static com.example.ouz.parrot.activity.DeviceListActivity.EXTRA_DEVICE_SERVICE;

public class DragNDrop extends AppCompatActivity {
    Thread thread;

    private MiniDrone mMiniDrone;
    Button btnAcil;
    TextView txtBatarya;
    private ProgressDialog mConnectionProgressDialog;
    LinearLayout kaynakAlan;
    public static int kontrol=0;
    ImageView yukari,uc,sol,sag,harita1,harita2,harita3,harita4,harita5,harita6,harita7,harita8,harita9;
    TextView basarili;
    TextView toplam;
    int deneme;
    int hatali = 0;
    public static ARDiscoveryDeviceService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        Log.d("Grid","Gride girdi ");
        Intent intent = getIntent();
        Log.d("Grid","intente gitti ");
        service = PuzzleActivity.service;
        mMiniDrone = PuzzleActivity.mMiniDrone;
        mMiniDrone.addListener(mMiniDroneListener);
        txtBatarya = (TextView) findViewById(R.id.txtBatarya);
        btnAcil=findViewById(R.id.btnAcil);
        uc=findViewById(R.id.iv_uc);

        btnAcil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (mMiniDrone.getFlyingState()) {
                    case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_LANDED:
                        btnAcil.setEnabled(true);
                        mMiniDrone.takeOff();
                        break;
                    case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_FLYING:
                    case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_HOVERING:
                        btnAcil.setEnabled(true);
                        mMiniDrone.land();
                        break;
                    default:
                }
            }
        });

        uc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMiniDrone.takeOff();
                Uc();
                Intent intent1 = new Intent(DragNDrop.this,GridActivity.class);
                intent1.putExtra(EXTRA_DEVICE_SERVICE, service);
                startActivity(intent1);
            }

        });


        kaynakAlan = (LinearLayout) findViewById(R.id.ustlinear);
        yukari = (ImageView) findViewById(R.id.iv_yukari);
        uc = (ImageView) findViewById(R.id.iv_uc);
        sol = (ImageView) findViewById(R.id.iv_sol);
        sag = (ImageView) findViewById(R.id.iv_sag);

        harita1=(ImageView)findViewById(R.id.imageView1);
        harita2=(ImageView)findViewById(R.id.imageView2);
        harita3=(ImageView)findViewById(R.id.imageView3);
        harita4=(ImageView)findViewById(R.id.imageView4);
        harita5=(ImageView)findViewById(R.id.imageView5);
        harita6=(ImageView)findViewById(R.id.imageView6);
        harita7=(ImageView)findViewById(R.id.imageView7);
        harita8=(ImageView)findViewById(R.id.imageView8);
        harita9=(ImageView)findViewById(R.id.imageView9);


        kaynakAlan.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {
                // TODO Auto-generated method stub
                final int action = event.getAction();
                switch (action) {

                    case DragEvent.ACTION_DROP: {

                        break;
                    }

                    case DragEvent.ACTION_DRAG_ENDED: {
                        if(yukari.getVisibility()==View.VISIBLE){
                            if(kontrol==0){
                                harita1.setBackgroundResource(R.drawable.blue);
                                harita1.setImageResource(R.drawable.blue);
                                kontrol++;
                            }else if(kontrol==1){
                                harita2.setBackgroundResource(R.drawable.blue);
                                harita2.setImageResource(R.drawable.blue);
                                yukari.setVisibility(View.INVISIBLE);
                                sag.setVisibility(View.VISIBLE);
                                kontrol++;
                            }
                            else if(kontrol==3){
                                harita4.setBackgroundResource(R.drawable.blue);
                                harita4.setImageResource(R.drawable.blue);
                                kontrol++;
                            }
                            else if(kontrol==4){
                                harita5.setBackgroundResource(R.drawable.blue);
                                harita5.setImageResource(R.drawable.blue);
                                kontrol++;
                                yukari.setVisibility(View.INVISIBLE);
                                sol.setVisibility(View.VISIBLE);
                            }
                            else if(kontrol==7){
                                harita8.setBackgroundResource(R.drawable.blue);
                                harita8.setImageResource(R.drawable.blue);
                                kontrol++;
                            }
                            else if(kontrol==8){
                                harita9.setBackgroundResource(R.drawable.blue);
                                harita9.setImageResource(R.drawable.blue);
                                kontrol++;
                                yukari.setVisibility(View.INVISIBLE);
                                uc.setVisibility(View.VISIBLE);
                            }
                        }
                        else if(sag.getVisibility()==View.VISIBLE){
                            if(kontrol==2){
                                harita3.setBackgroundResource(R.drawable.blue);
                                harita3.setImageResource(R.drawable.blue);
                                kontrol++;
                                sag.setVisibility(View.INVISIBLE);
                                yukari.setVisibility(View.VISIBLE);
                            }
                        }
                        else if(sol.getVisibility()==View.VISIBLE){
                            if(kontrol==5){
                                harita6.setBackgroundResource(R.drawable.blue);
                                harita6.setImageResource(R.drawable.blue);
                                kontrol++;
                            }
                            else if(kontrol==6){
                                harita7.setBackgroundResource(R.drawable.blue);
                                harita7.setImageResource(R.drawable.blue);
                                kontrol++;
                                sol.setVisibility(View.INVISIBLE);
                                yukari.setVisibility(View.VISIBLE);
                            }
                        }

                        break;

                    }

                    case DragEvent.ACTION_DRAG_STARTED:
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
        yukari.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                // TODO Auto-generated method stub
                ClipData veri = ClipData.newPlainText("", "");
                View.DragShadowBuilder golge = new View.DragShadowBuilder(yukari);
                v.startDrag(veri, golge, null, 0);
                return false;
            }
        });


        sol.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                // TODO Auto-generated method stub
                ClipData veri = ClipData.newPlainText("", "");
                View.DragShadowBuilder golge = new View.DragShadowBuilder(sol);
                v.startDrag(veri, golge, null, 0);
                return false;
            }
        });
        sag.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                // TODO Auto-generated method stub
                ClipData veri = ClipData.newPlainText("", "");
                View.DragShadowBuilder golge = new View.DragShadowBuilder(sag);
                v.startDrag(veri, golge, null, 0);
                return false;
            }
        });


    }

    public void Uc(){
        thread = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized (this){
                        wait(1500);


                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //ileri
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        wait(1500);
                        mMiniDrone.flip(ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM.BACK);
                        wait(2000);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setYaw((byte) 45); //sağ dön
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //sağa doğru ilerle
                        sleep(1000);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setYaw((byte) -45); //sola dön
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //sola ilerle
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) -45); //sola dönme
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //ilerle
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) 45); //sağa  dönme
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35);
                        sleep(1000);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.flip(ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM.BACK);

                        wait(1500);

                        mMiniDrone.land();
                    }
                }
                catch (Exception e){
                    Log.d("hata","xd");
                }finally {
                    mMiniDrone.land();
                    thread.interrupt();
                }
            }
        };

        thread.start();
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.yukari);
        Drawable normalShape = getResources().getDrawable(R.drawable.gray);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                default:
                    break;
            }
            return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // show a loading view while the minidrone is connecting
        if ((mMiniDrone != null) && !(ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_RUNNING.equals(mMiniDrone.getConnectionState())))
        {
            mConnectionProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
            mConnectionProgressDialog.setIndeterminate(true);
            mConnectionProgressDialog.setMessage("Bağlanmaya çalışıyor ...");
            mConnectionProgressDialog.setCancelable(false);
            mConnectionProgressDialog.show();

            // if the connection to the MiniDrone fails, finish the activity
            if (!mMiniDrone.connect()) {
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mMiniDrone != null)
        {
            mConnectionProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
            mConnectionProgressDialog.setIndeterminate(true);
            mConnectionProgressDialog.setMessage("Bağlantı Kesiliyor ...");
            mConnectionProgressDialog.setCancelable(false);
            mConnectionProgressDialog.show();

            if (!mMiniDrone.disconnect()) {
                finish();
            }
        } else {
            finish();
        }
    }

    @Override
    public void onDestroy()
    {
        mMiniDrone.dispose();
        super.onDestroy();
    }

    private final MiniDrone.Listener mMiniDroneListener = new MiniDrone.Listener() {
        @Override
        public void onDroneConnectionChanged(ARCONTROLLER_DEVICE_STATE_ENUM state) {
            switch (state)
            {
                case ARCONTROLLER_DEVICE_STATE_RUNNING:
                    mConnectionProgressDialog.dismiss();
                    btnAcil.setEnabled(true);
                    break;

                case ARCONTROLLER_DEVICE_STATE_STOPPED:
                    // if the deviceController is stopped, go back to the previous activity
                    mConnectionProgressDialog.dismiss();
                    btnAcil.setEnabled(true);
                    finish();
                    break;

                default:
                    break;
            }
        }

        @Override
        public void onBatteryChargeChanged(int batteryPercentage) {
            txtBatarya.setText(String.format("%d%%", batteryPercentage));
        }

        @Override
        public void onPilotingStateChanged(ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_ENUM state) {
            switch (state) {
                case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_LANDED:
                    btnAcil.setText("Kalk");
                    btnAcil.setEnabled(true);
                    break;
                case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_FLYING:
                case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_HOVERING:
                    btnAcil.setEnabled(true);
                    btnAcil.setText("İn");
                    break;
                default:
                    btnAcil.setEnabled(false);
            }
        }

        @Override
        public void onPictureTaken(ARCOMMANDS_MINIDRONE_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM error) {

        }

        @Override
        public void configureDecoder(ARControllerCodec codec) {

        }

        @Override
        public void onFrameReceived(ARFrame frame) {

        }

        @Override
        public void onMatchingMediasFound(int nbMedias) {

        }

        @Override
        public void onDownloadProgressed(String mediaName, int progress) {

        }

        @Override
        public void onDownloadComplete(String mediaName) {

        }


        //ARAYÜZE KENDİ TAKLALARIMIZI KOYMAK İÇİN BURAYA YAZMAMIZ LAZIM
        @Override
        public void onAnimationTypeChanged(ARCOMMANDS_ANIMATION_TYPE_ENUM type, byte percent) {

        }

        @Override
        public void onAnimationStateChanged(ARCOMMANDS_ANIMATION_STATE_ENUM state) {

        }
    };
    //Geri Butonu
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

        }
        return super.onKeyDown(keyCode, event);
    }
}
