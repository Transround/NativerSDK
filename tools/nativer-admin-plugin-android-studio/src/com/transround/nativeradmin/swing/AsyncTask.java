package com.transround.nativeradmin.swing;

import javax.swing.*;

/**
 * Created by szeibert on 2014.11.24..
 */
public abstract class AsyncTask<T> {
    protected abstract T performOperation() throws Exception;
    protected abstract void onComplete();
    protected abstract void onSuccess(T result);
    protected abstract void onFailure(Throwable e);

    public final void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final T result = performOperation();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            onComplete();
                            onSuccess(result);
                        }
                    });
                } catch (final Throwable e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            onComplete();
                            onFailure(e);
                        }
                    });
                }
            }
        }).start();
    }
}
