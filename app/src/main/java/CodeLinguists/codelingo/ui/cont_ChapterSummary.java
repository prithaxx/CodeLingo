package CodeLinguists.codelingo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.logic.IAccountHandler;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;


public class cont_ChapterSummary extends AppCompatActivity {

    private RecyclerView chapterListRecyclerView;
    private TextView chapterSummaryTextView;
    private IAccountHandler accountHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_summary);

        chapterListRecyclerView = findViewById(R.id.chapterList);
        chapterSummaryTextView = findViewById(R.id.chapterSummary);
        accountHandler = new AccountHandler();

        List<ChapterObj> chapters = accountHandler.getActiveCourseChapters();

        if (chapters == null || chapters.isEmpty()) {
            // show the toast
            Toast.makeText(this, "No chapters available", Toast.LENGTH_LONG).show();
        } else {
            // otherwise if contains data we will connect RecyclerView display the data
            ChapterListAdapter adapter = new ChapterListAdapter(chapters);
            chapterListRecyclerView.setAdapter(adapter);
            chapterListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ChapterViewHolder> {
        private final List<ChapterObj> chapterList;

        ChapterListAdapter(List<ChapterObj> chapterList) {
            this.chapterList = chapterList;
        }

        @NotNull
        @Override
        public ChapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chapter, parent, false);
            return new ChapterViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(ChapterViewHolder holder, int position) {
            final ChapterObj chapter = chapterList.get(position);
            holder.buttonChapter.setText(chapter.name());

            // set icon
            if (chapter.isCompleted()) {
                holder.imageChapterStatus.setImageResource(R.drawable.ic_completed);

                // icon listener
                holder.imageChapterStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chapterSummaryTextView.setText(chapter.description());
                    }
                });

                holder.buttonChapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chapterSummaryTextView.setText(chapter.description());
                    }
                });

            } else {
                holder.imageChapterStatus.setImageResource(R.drawable.ic_lock);

                holder.buttonChapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chapterSummaryTextView.setText("please complete the previous chapters");
                    }
                });


            }

        }


        @Override
        public int getItemCount() {
            return chapterList.size();
        }

        class ChapterViewHolder extends RecyclerView.ViewHolder {
            Button buttonChapter;
            ImageView imageChapterStatus;

            ChapterViewHolder(View itemView) {
                super(itemView);
                buttonChapter = itemView.findViewById(R.id.buttonChapter);
                imageChapterStatus = itemView.findViewById(R.id.imageChapterStatus);
            }
        }
    }
}
