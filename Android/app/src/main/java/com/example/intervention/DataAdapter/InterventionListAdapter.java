package com.example.intervention.DataAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intervention.Model.Entity.Intervention;
import com.example.intervention.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

// notre classe dérivant de RecyclerView.Adapter<InterventionList.ViewHolder> car notre viewholder est intégré à cette classe
public class InterventionListAdapter extends RecyclerView.Adapter<InterventionListAdapter.ViewHolder> {
    private List<Intervention> mData; // notre liste de donnée
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener; //un listener pour gérer le clic

    public InterventionListAdapter(Context context, List<Intervention> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data; // on lie les données reçu en parametre à notre attribut
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // on crée notre viewHolder en liant celui ci à notre layout item : recycler_view_item
        View view = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // on récupére notre objet Intervention correspondant à la position
        Intervention lintervention = mData.get(position);
        // puis on lie chaque élément de la vue
        // (dont les identifiants sont définis dans la classe viewholder ci-dessous)
        // a chacun des attributs de contacts correspondants
        holder.lenom.setText(lintervention.getNomClient());
        holder.leprenom.setText(lintervention.getPrenomClient());
        holder.ladate.setText(lintervention.getDateIntervention());
    }

    // la méthode qui retourne le nombre total d'éléments dans la RV
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    public Intervention getItem(int id) {
        return mData.get(id);
    }

    public void setItem(int id, Intervention i) { mData.set(id,i); }

    // Lie l'interface d'écoute d'un item à notre écouteur
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // le parent (notre DataActivity devra implémenter cette interface pour pouvoir gerer les clicks sur un élément)
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // Définition du ViewHolder de notre Adapter (implémentant le OnClickListener)
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // On déclare les différents éléments de vue de l'item
        TextView lenom;
        TextView leprenom;
        TextView ladate;
        ImageView lavatar;

        ViewHolder(View itemView) {
            super(itemView);
            //on lie les identifiants déclaré dans la vue à nos éléments
            lenom = itemView.findViewById(R.id.tvValueName);
            leprenom = itemView.findViewById(R.id.tvValueSurname);
            ladate = itemView.findViewById(R.id.tvValueNum);
            lavatar = itemView.findViewById(R.id.tvAvatar);
            // on ajoute au passage le listener pour le clic sur l'éléments
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //sur un clic, on renvoie (par le biais de l'interface), la vue et surtout la position dans l'adapter
            // necessaire pour savoir quel item est concerné par le clic
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}