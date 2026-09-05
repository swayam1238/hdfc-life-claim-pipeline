package com.hdfclife.exception;

public class QueueEmptyException extends PipelineException {
    public QueueEmptyException(String message) {
        super(message);
    }
}
