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
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ChapterObj}.
 */
public class itm_ChapterRecyclerViewAdapter extends RecyclerView.Adapter<itm_ChapterRecyclerViewAdapter.ViewHolder> {

    private final List<ChapterObj> mValues;

    public itm_ChapterRecyclerViewAdapter(List<ChapterObj> items) {
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
        holder.mTitle.setText(chapter.getName());
        holder.mIView.setImageResource(chAssets[chapter.getId()%chAssets.length]);
        if(chapter.isUnlocked() || position == 0) {
            holder.mLock.setVisibility(View.INVISIBLE);
            holder.mTile.setAlpha(0.5f);
            holder.mTitle.setAlpha(1f);
            holder.mIView.setAlpha(1f);
            holder.mTile.setOnClickListener(
                    (View view) -> {
                        try {
                            ISessionManager sessionManager = Services.getSessionManager();
                            sessionManager.setActiveChapter(chapter.getId());
                            Intent intent = new Intent(view.getContext(), view_SlideShowWrapper.class);
                            view.getContext().startActivity(intent);
                        } catch (InputValidationException | AccountPermissionException e) {
                            e.printStackTrace();
                            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        } else {
            holder.mTile.setOnClickListener(null);
            holder.mTile.setAlpha(0.3f);
            holder.mTitle.setAlpha(0.3f);
            holder.mIView.setAlpha(0.3f);
            holder.mLock.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateChapterList(List<ChapterObj> items) {
        mValues.clear();
        mValues.addAll(items);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitle;
        public ChapterObj mItem;
        public ImageView mIView;
        public View mTile;
        public ImageView mLock;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mTitle = binding.title;
            mIView = binding.chptImage;
            mTile = binding.chapterTile;
            mLock = binding.chapterLock;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}