package com.example.rxjavatest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavatest.R;

import java.util.List;

/**
 * Created by fan.zhengxi on 2020/9/24
 * Describe:
 */
public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {
    private List<String> stringList;
    public DisplayAdapter(List<String> list){
        stringList=list;
    }
    /**
     * 加载子布局
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    /**
     *给界面赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_text.setText(stringList.get(position));
    }


    @Override
    public int getItemCount() {
        if (stringList!=null) {
            return stringList.size();
        }
        else {return 0;}
    }

    /**
     * 引入子布局的控件
     */
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_text=itemView.findViewById(R.id.tv_text);
        }
    }

}
