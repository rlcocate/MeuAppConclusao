package nossafirma.com.br.meuapp.adapter;

import java.util.List;

import nossafirma.com.br.meuapp.model.Login;

public class LoginAdapter {

    private Login login;

    public LoginAdapter(Login login){
        this.login = login;
    }

    public void update(Login login){
        this.login = login;
//        notifyDataSetChanged();
    }

/*    @Override
    public AndroidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Infla o contexto.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Infla o layout android_row, no contexto parent, false = não é pra renderizar neste momento.
        View meuLayout = inflater.inflate(R.layout.android_row, parent, false);

        //
        return new AndroidViewHolder(meuLayout);
    }

    @Override
    public void onBindViewHolder(AndroidViewHolder holder, int position) {
        holder.tvTitulo.setText(androids.get(position).getNome());
        holder.tvSubTitulo.setText(androids.get(position).getVersao());
        Picasso.with(holder.itemView.getContext())
                .load(androids.get(position).getUrlImagem())
                .placeholder(R.mipmap.ic_launcher) // exibe pré carregamento
                .error(R.mipmap.ic_launcher) // em caso de erro
                .into(holder.ivTitulo);
    }

    @Override
    public int getItemCount() {
        return androids.size();
    }

    public class AndroidViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivTitulo;
        public TextView tvTitulo;
        public TextView tvSubTitulo;

        public AndroidViewHolder(View itemView) {
            // Chama o construtor do pai (o que herdou: RecyclerView.ViewHolder).
            super(itemView);

            ivTitulo = (ImageView) itemView.findViewById(R.id.ivTitulo);
            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvSubTitulo = (TextView) itemView.findViewById(R.id.tvSubTitulo);

        }
    }

    public void update(List<Android> androids){
        this.androids = androids;
        notifyDataSetChanged();
    }*/
}
