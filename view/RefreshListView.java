package com.ds.getfrominternet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ds.getfrominternet.R;

/**
 * Created by Administrator on 2016/10/27.
 */

public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    private static final String TAG = "RefreshListView";
    private int mYDown;//在屏幕按下时的坐标
    private int deltaY;//在屏幕上上下滑动的距离
    private int headerViewHeight;
    private int footerViewHeight;
    private View headerView;
    private View footerView;
    private OnRefreshListener listener;
    private final int PULL_REFRESH=1;//下拉刷新状态
    private final int RELEASE_REFRESH=2;//松开刷新状态
    private final int REFRESHING=3;//正在刷新状态
    private int currentState=PULL_REFRESH;//当前的状态
    private ImageView iv_arrow;
    private TextView tv_state;
    private TextView tv_time;
    private ProgressBar pb_rotate;
    private TextView mTvHeaderContent;
    private RotateAnimation upAnimation,downAnimation;
    private boolean isLoadingMore = false;//是否处于加载更多的状态
    private int mTouchSlop;
    private int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;

    public RefreshListView(Context context) {
        this(context,null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        init();
    }

    private void init() {

        initHeaderView();
        initFooterView();
        setOnScrollListener(this);//注册监听
    }

    /**
     * 在	ListView的最上面添加一个view
     */
    private void initHeaderView() {
        headerView = View.inflate(getContext(), R.layout.layout_listview_header, null);
        /*iv_arrow = (ImageView) headerView.findViewById(R.id.iv_arrow);
        pb_rotate = (ProgressBar) headerView.findViewById(R.id.pb_rotate);
        tv_state = (TextView) headerView.findViewById(R.id.tv_state);
        tv_time = (TextView) headerView.findViewById(R.id.tv_time);*/
        mTvHeaderContent = (TextView) headerView.findViewById(R.id.tv_header_content);
        headerView.measure(0, 0);//主动通知系统去测量
        headerViewHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerViewHeight, 0, 0);
        addHeaderView(headerView);
    }

    /**
     * 在listview底部添加一个view
     */
    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.layout_listview_footer, null);
        footerView.measure(0, 0);//主动通知系统去测量
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, footerViewHeight, 0, 0);
        //addFooterView(footerView);
    }

    /**
     * 设置滑动监听
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mYDown = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //如果当前的状态为正在刷新，不允许拖动了
                mLastY = (int) ev.getRawY();

                int a = mLastY - mYDown;
                Log.d(TAG, a+"");
                if( a <= mTouchSlop){
                    headerView.setPadding(0, -headerViewHeight+(mLastY-mYDown), 0, 0);
                }

                break;
            case MotionEvent.ACTION_UP:
                if (canLoad()) {
                    loadData();//上拉加载
                }


                    //下拉刷新

                /*if (currentState == PULL_REFRESH) {
                    //消失
                    headerView.setPadding(0, -headerViewHeight, 0, 0);
                } else if (currentState == RELEASE_REFRESH) {
                    //1.正常显示2.正在刷新
                    headerView.setPadding(0, 0, 0, 0);
                    currentState = REFRESHING;
                    //refreshHeaderView(currentState);
                    listener.OnPullRefresh();//进入正在刷新后，要做的逻辑，由外界传入
                }*/
                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private void loadData() {
        if (listener != null) {
            // 设置状态
            setLoading(true);
            //
            listener.OnLoadingMore();
        }
    }

    /**
     *
     * @param loading
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            addFooterView(footerView);
        } else {
            removeFooterView(footerView);
            mYDown = 0;
            mLastY = 0;
        }
    }

    /**
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
     *
     * @return
     */
    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }


    /**
     * 是否是上拉
     * @return
     */
    private boolean isPullUp() {

        return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
     * 是否在底部
     * @return
     */
    private boolean isBottom() {
        if (getAdapter() != null) {
            return getLastVisiblePosition() == (getAdapter().getCount() - 1);
        }
        return false;
    }

    /**
     * 根据当前的状态来改变HeaderView的显示
     * @param currentState
     */
    private void refreshHeaderView(int currentState){
        switch (currentState) {
            case PULL_REFRESH://下拉刷新
                /*tv_state.setText("下拉刷新");
                iv_arrow.startAnimation(downAnimation);*/
                break;
            case RELEASE_REFRESH://松开刷新
                /*tv_state.setText("松开刷新");
                iv_arrow.startAnimation(upAnimation);*/
                break;
            case REFRESHING://正在刷新
                /*iv_arrow.clearAnimation();//松开的时候，动画可能还没执行完，所以强制停止
                iv_arrow.setVisibility(View.INVISIBLE);
                pb_rotate.setVisibility(View.VISIBLE);
                tv_state.setText("正在刷新");*/
                break;
        }

    }

    public interface OnRefreshListener{
        void OnPullRefresh();
        void OnLoadingMore();
    }

    /*
     * 设置状态接口监听
     */
    public void setOnRefreshListener(OnRefreshListener listener){
        this.listener = listener;
    }

    /**
     * 滑动监听
     * SCROLL_STATE_IDLE = 0：闲置状态，就是手指松开
     * SCROLL_STATE_TOUCH_SCROLL：手指触摸，滑动状态
     * SCROLL_STATE_FLING = 2:惯性，快速滑动后松开
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //当显示最后一条并且手指松开时，显示
        if(scrollState==OnScrollListener.SCROLL_STATE_IDLE && getLastVisiblePosition()==(getCount()-1) && !isLoadingMore){
            isLoadingMore = true;//当滑到底部的时候，在滑动时不执行的，这样防止代码的重复执行
            //显示出footerview
            footerView.setPadding(0, 0, 0, 0);
            setSelection(getCount()-1);//将对应的条目放置屏幕的顶端，如果最后展示的条目数不足占据整个屏幕，就显示不出效果（例如：
            //设置最后一条放在屏幕的顶端，可目前展示的条目一共还有4条，就不会把最后一条放置顶端了）
            //请求加载数据
            if(listener!=null){
                listener.OnLoadingMore();
            }


        }
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


}
