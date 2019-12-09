package com.abbaqus.vivekathreya_reddit.api.exceptions;

import com.abbaqus.vivekathreya_reddit.R;
import com.abbaqus.vivekathreya_reddit.view.RedditApplication;

import java.io.IOException;

public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return RedditApplication.getContext().getString(R.string.no_internet);
    }
}
