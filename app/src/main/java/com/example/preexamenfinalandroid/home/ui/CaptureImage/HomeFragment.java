package com.example.preexamenfinalandroid.home.ui.CaptureImage;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.preexamenfinalandroid.databinding.FragmentHomeBinding;
import com.example.preexamenfinalandroid.home.database.database.BaseDeDatos;
import com.example.preexamenfinalandroid.home.mapper.UriMapper;
import com.example.preexamenfinalandroid.home.model.Imagen;
import com.example.preexamenfinalandroid.home.model.Intercambio;
import com.example.preexamenfinalandroid.home.ui.CaptureImage.foto.SacarFoto;
import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Uri imagePath;
    private SacarFoto sacarFoto;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sacarFoto = createSacarFoto();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button takePhoto = binding.pickPhoto;
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePath = sacarFoto.sacarFoto();
                //binding.setImagen.setImageURI(imagePath);
            }
        });

        final Button save = binding.savePhoto;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarImagen();
            }
        });

        return root;
    }

    private SacarFoto createSacarFoto(){
        return new SacarFoto(getActivity(), this,this);
    }

    private void guardarImagen(){
        if(imagePath!=null){
            Imagen foto = new Imagen();
            foto.setName(binding.setNombre.getText().toString());
            foto.setImage(UriMapper.getInstance().forUriToString(imagePath));
            foto.setOwner(Intercambio.getInstance().getUsuario());

            BaseDeDatos.getInstance(getActivity()).imageRepository().insert(foto);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==3333){
            sacarFoto.openCamera();
        }else{
            Toast.makeText(getActivity(), "Permiso denegado", Toast.LENGTH_SHORT).show();
        }
    }

    public ActivityResultLauncher setLauncher(){
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //result.getData().getExtras(MediaStore.)
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.setImagen.setImageURI(sacarFoto.getImageUri());
    }
}