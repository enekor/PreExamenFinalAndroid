package com.example.preexamenfinalandroid.home.ui.Gallery.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.preexamenfinalandroid.R;
import com.example.preexamenfinalandroid.home.database.database.BaseDeDatos;
import com.example.preexamenfinalandroid.home.mapper.UriMapper;
import com.example.preexamenfinalandroid.home.model.Imagen;
import com.example.preexamenfinalandroid.home.model.Intercambio;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Imagen> imagenes;
    private Context contexto;

    public Adapter(Context contexto){
        this.imagenes = BaseDeDatos.getInstance(contexto).imageRepository().getAllByUser(Intercambio.getInstance().getUsuario());
        this.contexto = contexto;
    }

    @NonNull
    @NotNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter.ViewHolder holder, int position) {
        holder.imagen.setImageURI(UriMapper.getInstance().fromStringToUri(imagenes.get(position).getImage()));
        holder.nombre.setText(imagenes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private TextView nombre;
        private LinearLayout layout;

        public ViewHolder(@NonNull @NotNull View view) {
            super(view);

            imagen = view.findViewById(R.id.imagen);
            nombre = view.findViewById(R.id.nombre);
            layout = view.findViewById(R.id.layout);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(contexto,nombre.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
