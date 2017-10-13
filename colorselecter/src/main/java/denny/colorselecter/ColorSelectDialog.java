package denny.colorselecter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DONG on 2017/10/6.
 * 功能描述:
 */

public class ColorSelectDialog {
    private AlertDialog dialog;
    private int finalColor;

    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;
    View dialoglayout;
    private Context context;
    private OnColorSelectListener onColorSelectListener;

    public ColorSelectDialog(Context context) {
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        dialoglayout = inflater.inflate(R.layout.color_dialog, null);


        //builder.setTitle("--顏色選擇器--");
        builder.setView(dialoglayout);
        String[] imageAry = new String[]{
                "FF181818", "FFFFB756", "FFF89604", "FFD98302", "FFBA6F03", "FF9C5C03",
                "FF313131", "FFFF6B5E", "FFFF4122", "FFBA1B0B", "FFB91A0B", "FF9C1508",
                "FF4C4B4B", "FFFF5AAB", "FFF91185", "FFDE0E76", "FFB90B62", "FF9C0751",
                "FF7B7B7C", "FFB85DFF", "FF9721F9", "FF861CDE", "FF6F16B9", "FF5C119C",
                "FF919191", "FF6B64FF", "FF2A2CEC", "FF2022DE", "FF1A1AB9", "FF15149C",
                "FFA8A8A8", "FF55ABFF", "FF0E8EFF", "FF0278DD", "FF0263B9", "FF02529C",
                "FFBFBFBF", "FF53F0F9", "FF02E3F0", "FF03CAD6", "FF0CB3AB", "FF028E96",
                "FFD4D4D4", "FF51FEB6", "FF03F795", "FF02E68A", "FF02DB84", "FF36B470",
                "FFF4F4F4", "FFA8FD51", "FF82F603", "FF72DB03", "FF5FB702", "FF4E9A02",
                "FFFCFCFC", "FFEFF852", "FFE3EE04", "FFCAD403", "FFA9B102", "FF8D9503"

        };

        ArrayList<String> myDataset = new ArrayList<>();
        for (int i = 0; i < imageAry.length; i++) {
            myDataset.add(imageAry[i]);
        }
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView = (RecyclerView) dialoglayout.findViewById(R.id.recycleview);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 6);


        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();


        TextView title = new TextView(context);
// You Can Customise your Title here
        title.setText("--顏色選擇器--");
        title.setBackgroundColor(Color.parseColor("#99FFFF"));
        title.setTextColor(Color.BLACK);
        title.setPadding(10, 15, 10, 15);
        title.setGravity(Gravity.CENTER);

        title.setTextSize(20);

        builder.setCustomTitle(title);
        dialog = builder.create();

    }

    public void show() {
        dialog.show();

    }

    public void close() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static interface OnColorSelectListener {
        public void onSelectFinish(int color);
    }

    public OnColorSelectListener getOnColorSelectListener() {
        return onColorSelectListener;
    }

    public void setOnColorSelectListener(OnColorSelectListener onColorSelectListener) {
        this.onColorSelectListener = onColorSelectListener;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mData;


        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;

            public ViewHolder(View v) {
                super(v);
                imageView = (ImageView) v.findViewById(R.id.image);
            }
        }

        public MyAdapter(List<String> data) {
            mData = data;

        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.imageView.setBackgroundColor(Color.parseColor("#" + mData.get(position)));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorDrawable buttonColor = (ColorDrawable) holder.imageView.getBackground();
                    finalColor = buttonColor.getColor();
                    onColorSelectListener.onSelectFinish(finalColor);
                    close();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
