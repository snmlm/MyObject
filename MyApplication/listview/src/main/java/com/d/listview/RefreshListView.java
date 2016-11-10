package com.d.listview;

/**
 * Created by Administrator on 2016/9/22.
 */

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 自定义下拉刷新ListView
 * @author mac
 *
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    private int downY;//在屏幕按下时的坐标
    private int deltaY;//在屏幕上上下滑动的距离
    private int headerViewHeight;
    private int footerViewHeight;
    private View headerView;
    private View footerView;
    private final int PULL_REFRESH=1;//下拉刷新状态
    private final int RELEASE_REFRESH=2;//松开刷新状态
    private final int REFRESHING=3;//正在刷新状态
    private int currentState=PULL_REFRESH;//当前的状态
    private ImageView iv;
    private TextView tv;
    private ProgressBar pb;
    private RotateAnimation upAnimation,downAnimation;
    private boolean isLoadingMore = false;//是否处于加载更多的状态
    private OnRefreshListener listener;
    private Context context;


    /**
     * 在代码中new一个对象时会调用此方法
     * @param context
     */
    public RefreshListView(Context context) {
        this(context,null);
    }
    /**
     * 有设置属性（xml）的时候会调用次方法
     * @param context
     * @param attrs
     */
    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }
    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        initHeaderView();
        initFooterView();
        initAnimation();
        setOnScrollListener(this);//注册监听
    }


    /**
     * 在listview底部添加一个view
     */
    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.listview_foot, null);
        footerView.measure(0, 0);//主动通知系统去测量
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        addFooterView(footerView);

    }
    /**
     * 在	ListView的最上面添加一个view
     */
    private void initHeaderView() {
        headerView = View.inflate(getContext(), R.layout.listview_header, null);
        iv = (ImageView) headerView.findViewById(R.id.iv_iv);
        pb = (ProgressBar) headerView.findViewById(R.id.pb_pb);
        tv = (TextView) headerView.findViewById(R.id.tv_tv);
        headerView.measure(0, 0);//主动通知系统去测量
        headerViewHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerViewHeight, 0, 0);
        addHeaderView(headerView);
    }
    //初始化动画
    private void initAnimation(){
        upAnimation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(300);
        upAnimation.setFillAfter(true);//动画结束后保持当前的状态

        downAnimation = new RotateAnimation(-180, -360,RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(300);
        upAnimation.setFillAfter(true);//动画结束后保持当前的状态

    }

    /**
     * 设置滑动监听
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //如果当前的状态为正在刷新，不允许拖动了
                if(currentState==REFRESHING){
                    break;
                }
                deltaY = (int) (ev.getY() - downY);
                /**
                 * 1.当向上滑动时，在header恰好不可见时，继续向上滑动，deltaY的值继续增大，此时已经看不见headerview了，所以不用去设置padding了（否则在下拉时，拉很长才能显示）
                 * 2.在向上滑动时，ListView自己也处理滑动，所以更快的上去了，出现了相对滑动（例如开始点击的是第五条，然后向上滑，等到松开的时候，按住的地方成了第三条），所以要禁止ListView的处理
                 * 通过返回true来设置
                 * 3.当从开始位置向上滑动时，由于距离小于0，所以没有屏蔽，此时可见条目的第一条比如编程了6，此时在往下滑动的时候，又被屏蔽了，所以ListView不能处理了，不能显示上面的条目了，
                 * 所以设置只有在可见条目为0时，才监听
                 * getFirstVisiblePosition：获得当前可见条目的位置
                 */
                if(getFirstVisiblePosition() == 0 && getChildAt(0).getTop()>=0){
                    int paddingTop = -headerViewHeight+deltaY;
                    //当已经处于松开刷新的时候，在往下滑动，状态没有改变，但设置状态的代码还是会执行，所以只在第一次状态改变的时候去执行设置状态的代码
                    if(paddingTop>=0 && currentState==PULL_REFRESH){
                        //进入松开刷新
                        currentState = RELEASE_REFRESH;
                        refreshHeaderView(currentState);
                    }else if(paddingTop<0 && currentState==RELEASE_REFRESH ){
                        //进入下拉刷新
                        currentState = PULL_REFRESH;
                        refreshHeaderView(currentState);
                    }
                    if(paddingTop> -headerViewHeight && getFirstVisiblePosition()==0){
                        headerView.setPadding(0, paddingTop, 0, 0);
                        return true;//屏幕事件，不让ListView自己处理
                    }
                }


                break;
            case MotionEvent.ACTION_UP:
                if(currentState==PULL_REFRESH){
                    //消失
                    headerView.setPadding(0, -headerViewHeight, 0, 0);
                }else if(currentState==RELEASE_REFRESH){
                    //1.正常显示2.正在刷新
                    headerView.setPadding(0, 0, 0, 0);
                    currentState=REFRESHING;
                    refreshHeaderView(currentState);
                    listener.OnPullRefresh();//进入正在刷新后，要做的逻辑，由外界传入
                }
                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
    /**
     * 根据当前的状态来改变HeaderView的显示
     * @param currentState
     */
    private void refreshHeaderView(int currentState){
        switch (currentState) {
            case PULL_REFRESH://下拉刷新
                tv.setText("下拉刷新");
                iv.startAnimation(downAnimation);
                break;
            case RELEASE_REFRESH://松开刷新
                tv.setText("松开刷新");
                iv.startAnimation(upAnimation);
                break;
            case REFRESHING://正在刷新
                iv.clearAnimation();//松开的时候，动画可能还没执行完，所以强制停止
                iv.setVisibility(View.INVISIBLE);
                pb.setVisibility(View.VISIBLE);
                tv.setText("正在刷新");
                new Timer(false).schedule(new TimerTask() {
                    @Override
                    public void run(){
                        ((Activity)context).runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                completeRefresh(true);
                            }
                        });
                    }
                },500);
                break;
        }

    }
    /**
     * 完成刷新
     * isrefreshHeader;true:重置header ;false:重置footer；
     */
    public void completeRefresh(boolean isrefreshHeader){
        if(isrefreshHeader){
            //重置HeaderView
            headerView.setPadding(0, -headerViewHeight, 0, 0);//隐藏
            pb.setVisibility(View.INVISIBLE);//隐藏进度条
            iv.setVisibility(View.VISIBLE);//显示箭头
            currentState=PULL_REFRESH;//改为初始状态
        }else{
            //重置FooterView
            footerView.setPadding(0, -footerViewHeight, 0, 0);
            isLoadingMore = false;//状态复位
        }

    }

    /**
     * 监听状态接口
     * @author mac
     *
     */
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
            //isLoadingMore = true;//当滑到底部的时候，在滑动时不执行的，这样防止代码的重复执行
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
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

    }
}