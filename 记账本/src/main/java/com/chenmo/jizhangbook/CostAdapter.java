package com.chenmo.jizhangbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 作者：沉默
 * 日期：2017/3/9
 * QQ:823925783
 */

public class CostAdapter extends BaseAdapter {

    List<CostBean> list;
    Context context;
    LayoutInflater layoutInflater;

    public CostAdapter(List<CostBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_lv, null);
            viewHolder.tvCostTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvCostDate = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.tvCostMoney = (TextView) convertView.findViewById(R.id.tv_money);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CostBean costs = list.get(position);

        viewHolder.tvCostTitle.setText(costs.getCostTitle());
        viewHolder.tvCostDate.setText(costs.getCostDate());
        viewHolder.tvCostMoney.setText(costs.getCostMoney());
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvCostTitle;
        private TextView tvCostDate;
        private TextView tvCostMoney;
    }
}
