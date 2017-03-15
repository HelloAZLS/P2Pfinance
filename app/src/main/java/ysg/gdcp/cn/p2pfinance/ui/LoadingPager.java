package ysg.gdcp.cn.p2pfinance.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import ysg.gdcp.cn.p2pfinance.R;
import ysg.gdcp.cn.p2pfinance.utils.UIutils;

/**
 * Created by Administrator on 2017/3/13 22:45.
 *
 * @author ysg
 */

public abstract class LoadingPager extends FrameLayout {

    public static final int PAGE_LOADING_STATE = 1;
    public static final int PAGER_ERROR_STATE = 2;
    public static final int PAGER_EMPTY_STATE = 3;
    public static final int PAGER_SUCCESS_STATE = 4;
    private final Context mContent;
    public int current_state = 1;

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View sucessView;
    private LayoutParams lp;
    private ResultState resultState = null;
    private AsyncHttpClient client = new AsyncHttpClient();

    public LoadingPager(Context context) {
        this(context, null);
        init();
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContent = context;
        init();
    }

    private void init() {
        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = UIutils.getXMLView(R.layout.page_loading);
            addView(loadingView, lp);
        }
        if (emptyView == null) {
            emptyView = UIutils.getXMLView(R.layout.page_empty);
            addView(emptyView, lp);
        }
        if (errorView == null) {
            errorView = UIutils.getXMLView(R.layout.page_error);
            addView(errorView, lp);
        }
        showSafePage();
    }

    private void showSafePage() {
        UIutils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    private void showPage() {
        loadingView.setVisibility(current_state == PAGE_LOADING_STATE ? View.VISIBLE : View.GONE);
        errorView.setVisibility(current_state == PAGER_ERROR_STATE ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(current_state == PAGER_EMPTY_STATE ? View.VISIBLE : View.GONE);
        if (sucessView == null) {
            sucessView = View.inflate(mContent,layoutId(),null);
            addView(sucessView, lp);
        }
        sucessView.setVisibility(current_state == PAGER_SUCCESS_STATE ? View.VISIBLE : View.GONE);
    }

    public void show() {
        if (current_state != PAGE_LOADING_STATE) {
            current_state = PAGE_LOADING_STATE;
        }
        String url = url();
        if (TextUtils.isEmpty(url)) {
            resultState = ResultState.SUCCESS;
            resultState.setContent("");
            loadPage();
        }else {
            client.get(url, params(), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    if (TextUtils.isEmpty(content)) {
                        resultState = ResultState.EMPTY;
                        resultState.setContent("");
                    } else {
                        resultState = ResultState.SUCCESS;
                        resultState.setContent(content);
                    }
                    loadPage();
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    resultState = ResultState.ERROR;
                    resultState.setContent("");
                    loadPage();
                }
            });
        }
    }

    private void loadPage() {
        switch (resultState) {
            case ERROR:
                current_state = PAGER_ERROR_STATE;
                break;
            case EMPTY:
                current_state = PAGER_EMPTY_STATE;
                break;
            case SUCCESS:
                current_state = PAGER_SUCCESS_STATE;
                break;
        }
        showSafePage();
        if (current_state == PAGER_SUCCESS_STATE) {
            OnSucess(resultState,sucessView);
        }
    }

    protected abstract void OnSucess(ResultState resultState,View sucessView);

    public enum ResultState {
        ERROR(2), EMPTY(3), SUCCESS(4);

        private int state;
        private String content;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        ResultState(int state) {
            this.state = state;
        }
    }

    protected abstract RequestParams params();

    protected abstract String url();

    public abstract int layoutId();
}
