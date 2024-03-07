package CodeLinguists.codelingo.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import CodeLinguists.codelingo.databinding.FragmentItemBinding;
import CodeLinguists.codelingo.dso.ChapterObj;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ChapterObj}.
 */
public class ChapterRecyclerViewAdapter extends RecyclerView.Adapter<ChapterRecyclerViewAdapter.ViewHolder> {

    private final List<ChapterObj> mValues;

    public ChapterRecyclerViewAdapter(List<ChapterObj> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).name());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitle;
        public ChapterObj mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mTitle = binding.title;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}