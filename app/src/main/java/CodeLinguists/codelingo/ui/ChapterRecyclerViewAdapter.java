package CodeLinguists.codelingo.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.databinding.FragmentItemBinding;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;

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
        int[] chAssets = new int[]{R.drawable.course_tile_1, R.drawable.course_tile_2, R.drawable.course_tile_3, R.drawable.course_tile_4};
        ChapterObj chapter = mValues.get(position);
        holder.mItem = chapter;
        holder.mTitle.setText(chapter.name());
        holder.mIView.setImageResource(chAssets[chapter.id()%chAssets.length]);
        holder.mTile.setOnClickListener(
                (View view)->{
                    try {
                        ISessionManager sessionManager = Services.getSessionManager();
                        sessionManager.setActiveChapter(chapter.id());
                        Intent intent = new Intent(view.getContext(), view_SlideShowWrapper.class);
                        view.getContext().startActivity(intent);
                    } catch (InputValidationException | AccountPermissionException e) {
                        e.printStackTrace();
                        Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitle;
        public ChapterObj mItem;
        public ImageView mIView;
        public View mTile;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mTitle = binding.title;
            mIView = binding.chptImage;
            mTile = binding.chapterTile;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}