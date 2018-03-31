package com.example.ouz.parrot.activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class GridActivity extends AppCompatActivity {

    TextView txtBatarya;
    Button yukari,asagi,sol,sag,btnAcil,uc;
    Dialog yukaripopup,asagipopup,solpopup,sagpopup,donmepopup;
    EditText EtYukari,EtYukari2,EtYukari3,EtAsagi,EtSol,EtSag,EtSag2;
    public static RelativeLayout gorev3bg;
    public static int kontrol =0;
    public static ARDiscoveryDeviceService service;

    Thread thread;

    public static MiniDrone mMiniDrone;

    private ProgressDialog mConnectionProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        Log.d("Grid","Gride girdi ");
        Intent intent = getIntent();
        Log.d("Grid","intente gitti ");
        service = PuzzleActivity.service;
        mMiniDrone = PuzzleActivity.mMiniDrone;
        mMiniDrone.addListener(mMiniDroneListener);

        txtBatarya = (TextView) findViewById(R.id.txtBatarya);
        yukari=findViewById(R.id.yukari);
        asagi=findViewById(R.id.asagi);
        sol=findViewById(R.id.sol);
        sag=findViewById(R.id.sag);
        btnAcil=findViewById(R.id.btnAcil);
        uc=findViewById(R.id.uc);


        EtYukari=findViewById(R.id.Etyukari);
        EtYukari2=findViewById(R.id.EtYukari2);
        EtYukari3=findViewById(R.id.EtYukari3);
        EtSol=findViewById(R.id.EtSol);
        EtSag=findViewById(R.id.Etsag);
        EtSag2=findViewById(R.id.EtSag2);

        yukaripopup=new Dialog(this);
        asagipopup=new Dialog(this);
        solpopup=new Dialog(this);
        sagpopup=new Dialog(this);
        donmepopup=new Dialog(this);

        gorev3bg= findViewById(R.id.gorev3bg);

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

            }

        });

        yukari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yukaripopup.setContentView(R.layout.yukaripopup);
                GridActivity.gorev3bg= findViewById(R.id.gorev3bg);
                Button btntamam;
                final EditText popupgiris;
                popupgiris=yukaripopup.findViewById(R.id.editText);
                btntamam=yukaripopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(popupgiris.getText().toString().equals("5") && kontrol==0)
                        {
                            GridActivity.gorev3bg.setBackgroundResource(R.drawable.bol31);
                            yukaripopup.dismiss();
                            kontrol=1;
                        }
                        else if(popupgiris.getText().toString().equals("4") && kontrol==2)
                        {
                            GridActivity.gorev3bg.setBackgroundResource(R.drawable.bol33);
                            yukaripopup.dismiss();
                            kontrol=3;
                        }
                        else if (popupgiris.getText().toString().equals("6") && kontrol==4)
                        {
                            GridActivity.gorev3bg.setBackgroundResource(R.drawable.bol35);
                            yukaripopup.dismiss();
                            kontrol=5;
                        }
                        else {
                            Toast.makeText(GridActivity.this, "Yanlış Değer Girdiniz.Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                yukaripopup.show();
            }
        });

        asagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asagipopup.setContentView(R.layout.asagipopup);
                GridActivity.gorev3bg= findViewById(R.id.gorev3bg);
                Button btntamam;
                final EditText popupgiris;
                popupgiris=asagipopup.findViewById(R.id.editText);
                btntamam=asagipopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        asagipopup.dismiss();
                    }
                });
                asagipopup.show();
            }
        });

        sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solpopup.setContentView(R.layout.solpopup);
                GridActivity.gorev3bg= findViewById(R.id.gorev3bg);
                Button btntamam;
                final EditText popupgiris;
                popupgiris=solpopup.findViewById(R.id.editText);
                btntamam=solpopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String log=popupgiris.getText().toString();
                        Log.w(log,"asdsd");
                        if(popupgiris.getText().toString().equals("2") && kontrol==3)
                        {
                            GridActivity.gorev3bg.setBackgroundResource(R.drawable.bol34);
                            solpopup.dismiss();
                            kontrol=4;
                        }
                        else {
                            Toast.makeText(GridActivity.this, "Yanlış Değer Girdiniz.Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                solpopup.show();
            }
        });

        sag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sagpopup.setContentView(R.layout.sagpopup);
                GridActivity.gorev3bg= findViewById(R.id.gorev3bg);
                Button btntamam;
                final EditText popupgiris;
                popupgiris=sagpopup.findViewById(R.id.editText);
                btntamam=sagpopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(popupgiris.getText().toString().equals("4") && kontrol==1)
                        {
                            GridActivity.gorev3bg.setBackgroundResource(R.drawable.bol32);
                            sagpopup.dismiss();
                            kontrol=2;
                        }
                        else if (popupgiris.getText().toString().equals("5") && kontrol==5)
                        {
                            GridActivity.gorev3bg.setBackgroundResource(R.drawable.bol36);
                            uc.setVisibility(View.VISIBLE);
                            sagpopup.dismiss();
                            kontrol=6;
                        }
                        else
                        {
                            Toast.makeText(GridActivity.this, "Yanlış Değer Girdiniz.Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                sagpopup.show();
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

                        mMiniDrone.setYaw((byte) 45); //sağ dön
                        sleep(3000);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //ileri
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) 45); //sağ dön
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //sağa doğru ilerle
                        sleep(2000);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setYaw((byte) -45); //sola dön
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); // ilerle
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) -45); //sola dönme
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //ilerle
                        sleep(750);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) 45); //sağa  dönme
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35);
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) 45); //sağa  dönme
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35);
                        sleep(1500);

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