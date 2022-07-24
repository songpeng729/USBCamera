package com.finger.usbcamera.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.finger.usbcamera.R;

/**
 * 自定义加载布局
 */
public class CustomLoadListView extends ListView implements OnScrollListener{

    View footer;//底部布局
    int totalItemCount;//总数量
    int lastVisibleItem;//最后一个可见的Item
    boolean isLoading;//正在加载
    ILoadListener iLoadListener;
    private boolean enableLoad = true;

    public CustomLoadListView(Context context) {
        super(context);
        initView(context);
    }

    public CustomLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomLoadListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    /**
     * 添加底部加载提示布局到listview
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        footer=layoutInflater.inflate(R.layout.footer_layout, null);
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount==lastVisibleItem&&scrollState==SCROLL_STATE_IDLE) {
            if (!isLoading && enableLoad) {
                isLoading=true;
                footer.findViewById(R.id.load_layout).setVisibility(View.VISIBLE);
                iLoadListener.onLoad();//加载更多数据
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem=firstVisibleItem+visibleItemCount;
        this.totalItemCount=totalItemCount;
    }

    //加载完毕
    public void loadComplete() {
        isLoading=false;
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
    }


    public void setiLoadListener(ILoadListener iLoadListener) {
        this.iLoadListener = iLoadListener;
    }

    /**
     * 加载更多数据的回调接口
     */
    public interface ILoadListener
    {
        public void onLoad();
    }

    public boolean isEnableLoad() {
        return enableLoad;
    }

    public void setEnableLoad(boolean enableLoad) {
        this.enableLoad = enableLoad;
    }
}
