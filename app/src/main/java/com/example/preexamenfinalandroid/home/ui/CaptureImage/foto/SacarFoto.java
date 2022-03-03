package com.example.preexamenfinalandroid.home.ui.CaptureImage.foto;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.example.preexamenfinalandroid.BuildConfig;
import com.example.preexamenfinalandroid.home.ui.CaptureImage.HomeFragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class SacarFoto {

    private final static int CODIGO = 3333;
    private final Context contexto;
    private final HomeFragment homeFragment;
    private Fragment fragmento = null;
    private String imagePath = "";
    private Uri imageUri = Uri.EMPTY;

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    private ActivityResultLauncher launcher;

    public SacarFoto(Context contexto, Fragment fragmento, HomeFragment homeFragment){
        this.contexto = contexto;
        this.fragmento = fragmento;
        this.homeFragment = homeFragment;
        setLauncher();
    }
    public Uri sacarFoto(){
        if(ContextCompat.checkSelfPermission(contexto, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            String[] permissions = new String[1];
            permissions[0] = Manifest.permission.CAMERA;
            fragmento.requestPermissions(permissions,CODIGO);
        }else{
            openCamera();
        }
        return imageUri;
    }

    public void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File foto = null;
        try{
            foto = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(foto!=null){
            imageUri = FileProvider.getUriForFile(contexto, BuildConfig.APPLICATION_ID + ".provider", foto);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
            launcher.launch(intent);
        }
    }

    private File createImageFile() throws IOException {
        String nombre = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File directorio = contexto.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File archivo = File.createTempFile(nombre,".jpg",directorio);

        imagePath = archivo.getAbsolutePath();
        return archivo;
    }

    private void setLauncher(){
        launcher = homeFragment.setLauncher();
    }
}
